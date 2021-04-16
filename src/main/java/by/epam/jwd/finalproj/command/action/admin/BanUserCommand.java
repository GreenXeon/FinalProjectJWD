package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.model.UserBanStatus;
import by.epam.jwd.finalproj.service.impl.UserService;

public enum BanUserCommand implements Command {
    INSTANCE;

    private final UserService userService;

    BanUserCommand(){
        this.userService = UserService.INSTANCE;
    }

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int userStatus = UserBanStatus.BANNED.getI();
        userService.setUserStatus(userId, userStatus);
        return ShowAllUsersCommand.INSTANCE.execute(request, response);
    }
}
