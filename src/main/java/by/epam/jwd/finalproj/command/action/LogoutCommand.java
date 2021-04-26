package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowGuestPageCommand;
import by.epam.jwd.finalproj.model.Language;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;

import javax.servlet.http.Cookie;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum LogoutCommand implements Command {
    INSTANCE;

    private final UserServiceImpl userService;

    LogoutCommand(){
        this.userService = UserServiceImpl.INSTANCE;
    }

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        userService.logOut(request, response);
        return ShowGuestPageCommand.INSTANCE.execute(request, response);
    }
}
