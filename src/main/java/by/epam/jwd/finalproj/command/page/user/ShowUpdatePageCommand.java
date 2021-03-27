package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum ShowUpdatePageCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ShowProfilePageCommand.class);

    private final UserService userService;

    ShowUpdatePageCommand(){
        this.userService = new UserService();
    }

    private static final Route SHOW_UPDATE_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/user/showUpdateProfile.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };


    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        int userId = (int) request.getSessionAttribute("userId");
        UserDto user = userService.findById(userId).orElse(null);
        if (user == null){
            logger.error("User with id " + userId + " is not found");
            return ShowMainPageCommand.INSTANCE.execute(request, response);
        }
        request.setAttribute("user", user);
        return SHOW_UPDATE_RESPONSE;
    }
}
