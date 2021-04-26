package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowChangePasswordCommand;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.mindrot.jbcrypt.BCrypt;

import static by.epam.jwd.finalproj.validator.Validator.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum ChangePasswordCommand implements Command {
    INSTANCE;

    private final int SALT_ROUNDS = 15;

    private final UserServiceImpl userService;

    ChangePasswordCommand(){
        this.userService = UserServiceImpl.INSTANCE;
    }

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        Route result = ShowChangePasswordCommand.INSTANCE.execute(request, response);
        if (request.getParameter(PASSWORD) == null || request.getParameter(REPEAT_PASSWORD) == null){
            request.setAttribute(ERROR, "Check your data!");
            return result;
        }
        String password = request.getParameter(PASSWORD);
        String repeatPassword = request.getParameter(REPEAT_PASSWORD);
        if (!isValidPassword(password) || !isValidPassword(repeatPassword)){
            request.setAttribute(ERROR, "Check your data!");
            return result;
        }
        int userId = (int) request.getSessionAttribute(SESSION_USER_ID);
        if (!password.equals(repeatPassword)){
            request.setAttribute(ERROR, "Passwords are not equal!");
            return result;
        }
        String salt = BCrypt.gensalt(SALT_ROUNDS);
        String passwordHash = BCrypt.hashpw(password, salt);
        userService.changeUserPassword(userId, passwordHash);
        return LogoutCommand.INSTANCE.execute(request, response);
    }
}
