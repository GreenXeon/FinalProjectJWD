package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.service.impl.UserService;

public enum MakeAdminUserCommand implements Command {
    INSTANCE;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        UserService userService = UserService.INSTANCE;
        int userId = Integer.parseInt(request.getParameter("userId"));
        //todo validation
        int userRole = Roles.USER.getI();
        userService.setUserRole(userId, userRole);
        return ShowAllUsersCommand.INSTANCE.execute(request, response);
    }
}