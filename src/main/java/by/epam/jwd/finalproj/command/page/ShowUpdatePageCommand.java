package by.epam.jwd.finalproj.command.page;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ShowUpdatePageCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowProfilePageCommand.class);

    private final UserServiceImpl userService;

    ShowUpdatePageCommand(){
        this.userService = UserServiceImpl.INSTANCE;
    }

    private static final Route SHOW_UPDATE_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/showUpdateProfile.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        int userId = (int) request.getSessionAttribute(SESSION_USER_ID);
        UserDto user = userService.findById(userId).orElse(null);
        try {
            if (user == null) {
                throw new CommandException("User is not found");
            }
            request.setAttribute(USER, user);
            return SHOW_UPDATE_RESPONSE;
        } catch (CommandException e){
            logger.error("User with id " + userId + " is not found");
            request.setAttribute(ERROR, e.getMessage());
            return ShowMainPageCommand.INSTANCE.execute(request, response);
        }
    }
}
