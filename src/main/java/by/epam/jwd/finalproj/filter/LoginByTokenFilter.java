package by.epam.jwd.finalproj.filter;

import by.epam.jwd.finalproj.command.CommandManager;
import by.epam.jwd.finalproj.command.WrappingRequestContext;
import by.epam.jwd.finalproj.command.WrappingResponseContext;
import by.epam.jwd.finalproj.model.Role;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

@WebFilter
public class LoginByTokenFilter implements Filter {

    private final UserServiceImpl userService = UserServiceImpl.INSTANCE;

    private final Logger logger = LogManager.getLogger(LoginByTokenFilter.class);

    private final int COOKIE_FOR_MONTH = 60 * 60 * 24 * 30;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userRole;
        if (request.getSession().getAttribute(SESSION_USER_ROLE) == null){
            userRole = Role.GUEST.name();
            request.getSession().setAttribute(SESSION_USER_ROLE, Role.GUEST.name());
        } else {
            userRole = request.getSession().getAttribute(SESSION_USER_ROLE).toString();
        }
        checkCookieForLocale(request, response);
        if (userRole.equalsIgnoreCase(Role.GUEST.name())) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(USER_TOKEN_COOKIE)) {
                        String rawToken = cookie.getValue();
                        try {
                            String[] splittedToken = rawToken.split(":");
                            String token = splittedToken[0];
                            int userId = Integer.parseInt(splittedToken[1]);
                            Optional<UserDto> user = userService.findById(userId);
                            if (!user.isPresent()){
                                throw new Exception("User is not found");
                            }
                            final String tokenToCompare = user.get().getLogin() + ":" + user.get().getRegistrationDate().toString();
                            boolean compareCookie = BCrypt.checkpw(tokenToCompare, token);
                            if (!compareCookie){
                                throw new Exception("Cookie is not validated");
                            }
                            if (user.get().isBlocked()){
                                request.getSession().invalidate();
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                response.addCookie(cookie);
                                request.setAttribute(ERROR, "User is banned");
                                String route = CommandManager.SHOWLOGIN.getCommand().execute(
                                        WrappingRequestContext.of(request), WrappingResponseContext.of(response)
                                ).getPage();
                                final RequestDispatcher dispatcher = request.getRequestDispatcher(route);
                                dispatcher.forward(request, response);
                                return;
                            }
                            request.getSession().setAttribute(SESSION_USER_LOGIN, user.get().getLogin());
                            request.getSession().setAttribute(SESSION_USER_ID, user.get().getId());
                            request.getSession().setAttribute(SESSION_USER_ROLE, user.get().getRole().name());
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        } catch (Exception e) {
                            request.getSession().setAttribute(SESSION_USER_ROLE, Role.GUEST.name());
                            logger.error(e.getMessage());
                            cookie.setMaxAge(0);
                            cookie.setValue("");
                            response.addCookie(cookie);
                        }
                    }
                }
            }
        } else {
            int userId = (int) request.getSession().getAttribute(SESSION_USER_ID);
            Optional<UserDto> currentUser = userService.findById(userId);
            if (currentUser.get().isBlocked()){
                request.getSession().invalidate();
                request.setAttribute(ERROR, "User is banned");
                String route = CommandManager.SHOWLOGIN.getCommand().execute(
                        WrappingRequestContext.of(request), WrappingResponseContext.of(response))
                        .getPage();
                final RequestDispatcher dispatcher = request.getRequestDispatcher(route);
                dispatcher.forward(request, response);
                return;
            }
            String userDtoRole = currentUser.get().getRole().name();
            if (!userDtoRole.equalsIgnoreCase(userRole)){
                request.getSession().invalidate();
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(USER_TOKEN_COOKIE)) {
                        cookie.setMaxAge(0);
                        cookie.setValue("");
                        response.addCookie(cookie);
                    }
                }
                String route = CommandManager.SHOWLOGIN.getCommand().execute(
                        WrappingRequestContext.of(request), WrappingResponseContext.of(response))
                        .getPage();
                final RequestDispatcher dispatcher = request.getRequestDispatcher(route);
                dispatcher.forward(request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void checkCookieForLocale(HttpServletRequest request, HttpServletResponse response){
        boolean cookieExists = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LOCALE_COOKIE_NAME)) {
                    cookieExists = true;
                    break;
                }
            }
            if(!cookieExists) {
                Cookie localeCookie = new Cookie(LOCALE_COOKIE_NAME, "en");
                localeCookie.setMaxAge(COOKIE_FOR_MONTH);
                response.addCookie(localeCookie);
            }
        }
    }
}
