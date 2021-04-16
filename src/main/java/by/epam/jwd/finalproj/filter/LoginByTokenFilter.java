package by.epam.jwd.finalproj.filter;

import by.epam.jwd.finalproj.command.CommandManager;
import by.epam.jwd.finalproj.command.WrappingRequestContext;
import by.epam.jwd.finalproj.command.WrappingResponseContext;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
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

@WebFilter
public class LoginByTokenFilter implements Filter {

    private final UserService userService = UserService.INSTANCE;

    private final Logger logger = LogManager.getLogger(LoginByTokenFilter.class);

    private final int COOKIE_FOR_MONTH = 60 * 60 * 24 * 30;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userRole;
        if (request.getSession().getAttribute("role") == null){
            userRole = Roles.GUEST.name();
            request.getSession().setAttribute("role", Roles.GUEST.name());
        } else {
            userRole = request.getSession().getAttribute("role").toString();
        }
        checkCookieForLocale(request, response);
        if (userRole.equalsIgnoreCase(Roles.GUEST.name())) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userToken")) {
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
                                logger.info("banned user");
                                request.getSession().invalidate();
                                cookie.setMaxAge(0);
                                cookie.setValue("");
                                response.addCookie(cookie);
                                request.setAttribute("errorMessage", "User is banned");
                                String route = CommandManager.SHOWLOGIN.getCommand().execute(
                                        WrappingRequestContext.of(request), WrappingResponseContext.of(response)
                                ).getPage();
                                final RequestDispatcher dispatcher = request.getRequestDispatcher(route);
                                dispatcher.forward(request, response);
                                return;
                            }
                            request.getSession().setAttribute("login", user.get().getLogin());
                            request.getSession().setAttribute("userId", user.get().getId());
                            request.getSession().setAttribute("role", user.get().getRole().name());
                            filterChain.doFilter(servletRequest, servletResponse);
                            return;
                        } catch (Exception e) {
                            request.getSession().setAttribute("role", Roles.GUEST.name());
                            logger.error(e.getMessage());
                            cookie.setMaxAge(0);
                            cookie.setValue("");
                            response.addCookie(cookie);
                        }
                    }
                }
            }
        } else {
            int userId = (int) request.getSession().getAttribute("userId");
            Optional<UserDto> currentUser = userService.findById(userId);
            if (currentUser.get().isBlocked()){
                request.getSession().invalidate();
                request.setAttribute("errorMessage", "User is banned");
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
                    if (cookie.getName().equals("userToken")) {
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
        Cookie localeCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("locale")) {
                    localeCookie = cookie;
                    break;
                }
            }
        }
        if(localeCookie == null){
            localeCookie = new Cookie("locale", "en");
            localeCookie.setMaxAge(COOKIE_FOR_MONTH);
            response.addCookie(localeCookie);
        }
    }
}
