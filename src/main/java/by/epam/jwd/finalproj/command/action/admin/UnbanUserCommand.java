package by.epam.jwd.finalproj.command.action.admin;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.admin.ShowAllUsersCommand;
import by.epam.jwd.finalproj.model.UserBanStatus;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum UnbanUserCommand implements Command {
    INSTANCE;

    private final UserServiceImpl userService;

    UnbanUserCommand(){
        this.userService = UserServiceImpl.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(UnbanUserCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID));
            int userStatus = UserBanStatus.UNBANNED.getI();
            userService.setUserStatus(userId, userStatus);
            return ShowAllUsersCommand.INSTANCE.execute(request, response);
        } catch (NumberFormatException e){
            logger.error(e.getMessage());
            request.setAttribute(ERROR, "Check user id!");
            return ShowAllUsersCommand.INSTANCE.execute(request, response);
        }
    }
}
