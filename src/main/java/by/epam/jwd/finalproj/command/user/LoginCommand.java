package by.epam.jwd.finalproj.command.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public enum LoginCommand implements Command {
    INSTANCE;

    private final UserService userService;

    LoginCommand() {
        userService = new UserService();
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final String login = String.valueOf(request.getAttribute("login")).trim();
        final String password = String.valueOf(request.getAttribute("password")).trim();
        final Optional<UserDto> user = userService.login(login, password);
        ResponseContext result = null;
        return result;
    }
}
