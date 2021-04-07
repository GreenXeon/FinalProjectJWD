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

@WebFilter(filterName = "UserValidCommandFilter")
public class UserValidCommandFilter implements Filter {

    private final Logger logger = LogManager.getLogger(UserValidCommandFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path= request.getRequestURI();
        if (path.endsWith(".css")){
            filterChain.doFilter(request,response);
            return;
        }

        String role = request.getSession().getAttribute("role").toString();
        logger.info(role);
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
        String commandName = request.getParameter("command");
        //logger.info("command is " + commandName);
        String command;

        if (commandName != null){
            if (!(permittedCommands.contains(commandName) || permittedCommands.contains(commandName.toUpperCase()))){
                logger.warn("Command " + commandName + " is not permitted for " + role);
                String route;
                if (role.equalsIgnoreCase("ADMIN")){
                    route = CommandManager.SHOW_PER_ADMIN.name();
                } else if (role.equalsIgnoreCase("USER")){
                    route = CommandManager.SHOW_USER_MAIN.name();
                } else {
                    route = CommandManager.SHOWGUEST.name();
                }
                response.sendRedirect(request.getContextPath() + "/controller?command=" + route.toLowerCase());
                return;
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else {
            if (role.equalsIgnoreCase("ADMIN")){
                command = CommandManager.SHOW_PER_ADMIN.name();
                //logger.info("show admin main");
            } else if (role.equalsIgnoreCase("USER")){
                command = CommandManager.SHOW_USER_MAIN.name();
                //logger.info("show user main");
            }
            else {
                command = CommandManager.SHOWGUEST.name();
                //logger.info("show guest main");
            }
            response.sendRedirect(request.getContextPath() + "/controller?command=" + command.toLowerCase());
            return;
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
            CommandManager.ADD_PERIODICAL.name()
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
            CommandManager.SUBSCRIBE.name()
    );
}
