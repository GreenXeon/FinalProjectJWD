package by.epam.jwd.finalproj.command.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public enum LoginCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;

    LoginCommand() {
        userService = new UserService();
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final String login = String.valueOf(request.getAttribute("login")).trim();
        final String password = String.valueOf(request.getAttribute("password")).trim();
        logger.info(login + " " + password);
        final Optional<UserDto> user = userService.login(login, password);
        ResponseContext result;
        if (user.isPresent()){
            request.setAttribute("username", login);
            result = ShowMainPageCommand.INSTANCE.execute(request);
        }
        else{
            request.setAttribute("errorMessage", "User is not found!");
            result = ShowLoginPageCommand.INSTANCE.execute(request);
        }
        return result;
    }
}
