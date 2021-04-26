package by.epam.jwd.finalproj.filter;

import by.epam.jwd.finalproj.command.*;
import by.epam.jwd.finalproj.command.page.ShowGuestPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

@WebFilter(filterName = "UserValidCommandFilter")
public class UserValidCommandFilter implements Filter {

    private final Logger logger = LogManager.getLogger(UserValidCommandFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path= request.getRequestURI();
        if (path.endsWith(".css") || path.endsWith(".ico") || path.endsWith(".js")){
            filterChain.doFilter(request,response);
            return;
        }
        String role = request.getSession().getAttribute(SESSION_USER_ROLE).toString();
        List<String> permittedCommands;
        switch (role){
            case "GUEST":
                permittedCommands = guestCommands;
                break;
            case "USER":
                permittedCommands = userCommands;
                break;
            case "ADMIN":
                permittedCommands = adminCommands;
                break;
            default:
                permittedCommands = Collections.emptyList();
        }
        String commandName = request.getParameter(COMMAND);
        String command;
        if (commandName != null){
            if (!(permittedCommands.contains(commandName) || permittedCommands.contains(commandName.toUpperCase()))){
                logger.warn("Command " + commandName + " is not permitted for " + role);
                String route;
                switch (role.toUpperCase()){
                    case "ADMIN":
                        route = CommandManager.SHOW_PER_ADMIN.name();
                        break;
                    case "USER":
                        route = CommandManager.SHOW_USER_MAIN.name();
                        break;
                    default:
                        route = CommandManager.SHOWGUEST.name();
                }
                response.sendRedirect(request.getContextPath() + "/controller?command=" + route.toLowerCase());
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            switch (role.toUpperCase()){
                case "ADMIN":
                    command = CommandManager.SHOW_PER_ADMIN.name();
                    break;
                case "USER":
                    command = CommandManager.SHOW_USER_MAIN.name();
                    break;
                default:
                    command = CommandManager.SHOWGUEST.name();
            }
            response.sendRedirect(request.getContextPath() + "/controller?command=" + command.toLowerCase());
        }
    }

    private final List<String> guestCommands = Arrays.asList(
            CommandManager.LOGIN.name(),
            CommandManager.SIGNUP.name(),
            CommandManager.SHOWLOGIN.name(),
            CommandManager.SHOWSIGNUP.name(),
            CommandManager.SHOWGUEST.name(),
            CommandManager.SHOW_ERROR.name()
    );

    private final List<String> adminCommands = Arrays.asList(
            CommandManager.LOGOUT.name(),
            CommandManager.SHOW_ERROR.name(),
            CommandManager.SHOW_ADD_PER.name(),
            CommandManager.SHOW_PER_ADMIN.name(),
            CommandManager.SHOW_ALL_USERS.name(),
            CommandManager.SHOW_UPDATE_PERIODICAL.name(),
            CommandManager.DELETE_PERIODICAL.name(),
            CommandManager.UPDATE_PERIODICAL.name(),
            CommandManager.ADD_PERIODICAL.name(),
            CommandManager.BAN_USER.name(),
            CommandManager.UNBAN_USER.name(),
            CommandManager.MAKE_ADMIN.name(),
            CommandManager.MAKE_USER.name(),
            CommandManager.SHOW_CHANGE_PASSWORD.name(),
            CommandManager.CHANGE_PASSWORD.name(),
            CommandManager.SHOW_UPDATE_USER.name(),
            CommandManager.UPDATE_USER.name(),
            CommandManager.SHOW_PROFILE_USER.name(),
            CommandManager.TOP_UP_BALANCE.name(),
            CommandManager.CHANGE_LANGUAGE.name(),
            CommandManager.SHOW_SUCCESS_PAGE.name()
    );

    private final List<String> userCommands = Arrays.asList(
            CommandManager.LOGOUT.name(),
            CommandManager.SHOW_ERROR.name(),
            CommandManager.SHOW_USER_MAIN.name(),
            CommandManager.SHOW_SUBSCRIBE.name(),
            CommandManager.SHOW_PROFILE_USER.name(),
            CommandManager.SHOW_UPDATE_USER.name(),
            CommandManager.TOP_UP_BALANCE.name(),
            CommandManager.UPDATE_USER.name(),
            CommandManager.SUBSCRIBE.name(),
            CommandManager.SHOW_ORDERS.name(),
            CommandManager.SHOW_ERROR.name(),
            CommandManager.SHOW_CHANGE_PASSWORD.name(),
            CommandManager.CHANGE_PASSWORD.name(),
            CommandManager.CHANGE_LANGUAGE.name(),
            CommandManager.SHOW_SUCCESS_PAGE.name()
    );
}
