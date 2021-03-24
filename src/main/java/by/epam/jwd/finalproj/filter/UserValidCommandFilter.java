package by.epam.jwd.finalproj.filter;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.CommandManager;
import by.epam.jwd.finalproj.command.WrappingRequestContext;
import by.epam.jwd.finalproj.model.Roles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@WebFilter
public class UserValidCommandFilter implements Filter {

    private final Logger logger = LogManager.getLogger(UserValidCommandFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        /*Roles role = Roles.findRoleByName((String) request.getSession().getAttribute("role"));
        Set<CommandManager> permittedCommands;
        switch (role){
            case GUEST:
                permittedCommands = guestCommands;
                break;
            case USER:
                permittedCommands = userCommands;
                break;
            case ADMIN:
                permittedCommands = adminCommands;
                break;
            default:
                permittedCommands = Collections.emptySet();
        }
        logger.info(request.getContextPath());
        String commandName = request.getParameter("command");
        logger.info("command is " + commandName);
        Command command;
        if (commandName != null){
            command = CommandManager.retrieveCommand(commandName);
        } else {
            if (role.name().equalsIgnoreCase("ADMIN")){
                command = CommandManager.SHOW_PER_ADMIN.getCommand();
            } else if (role.name().equalsIgnoreCase("USER")){
                command = CommandManager.SHOW_USER_MAIN.getCommand();
            }
            else {
                command = CommandManager.SHOWGUEST.getCommand();
            }
        }*/

        filterChain.doFilter(servletRequest, servletResponse);

    }

    private final Set<CommandManager> guestCommands = EnumSet.of(
            CommandManager.LOGIN,
            CommandManager.SIGNUP,
            CommandManager.SHOWLOGIN,
            CommandManager.SHOWSIGNUP,
            CommandManager.SHOWGUEST,
            CommandManager.SHOW_ERROR
    );

    private final Set<CommandManager> adminCommands = EnumSet.of(
            CommandManager.LOGOUT,
            CommandManager.SHOW_ERROR,
            CommandManager.SHOW_ADD_PER,
            CommandManager.SHOW_PER_ADMIN,
            CommandManager.SHOW_ALL_USERS,
            CommandManager.SHOW_UPDATE_PERIODICAL,
            CommandManager.DELETE_PERIODICAL,
            CommandManager.UPDATE_PERIODICAL,
            CommandManager.ADD_PERIODICAL
    );

    private final Set<CommandManager> userCommands = EnumSet.of(
            CommandManager.LOGOUT,
            CommandManager.SHOW_ERROR,
            CommandManager.SHOW_USER_MAIN
    );
}
