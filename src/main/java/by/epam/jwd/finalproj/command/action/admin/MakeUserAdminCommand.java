package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.model.Role;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.jwd.finalproj.util.ParameterNames.*;


public enum MakeUserAdminCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(MakeUserAdminCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        UserService userService = UserService.INSTANCE;
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID));
            int userRole = Role.ADMIN.getI();
            userService.setUserRole(userId, userRole);
            return ShowAllUsersCommand.INSTANCE.execute(request, response);
        } catch (NumberFormatException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, "Check user id!");
            return ShowAllUsersCommand.INSTANCE.execute(request, response);
        }
    }
}
