package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowChangePasswordCommand;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.mindrot.jbcrypt.BCrypt;

import static by.epam.jwd.finalproj.validator.Validator.*;

public enum ChangePasswordCommand implements Command {
    INSTANCE;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        if(request.getParameter("pass") == null || request.getParameter("rep_pass") == null){
            request.setAttribute("errorMessage", "Check your data!");
            return ShowChangePasswordCommand.INSTANCE.execute(request, response);
        }
        String password = request.getParameter("pass");
        String repeatPassword = request.getParameter("rep_pass");
        if(!isValidPassword(password) || !isValidPassword(repeatPassword)){
            request.setAttribute("errorMessage", "Check your data!");
            return ShowChangePasswordCommand.INSTANCE.execute(request, response);
        }
        int userId = (int) request.getSessionAttribute("userId");
        UserService userService = UserService.INSTANCE;
        if (!password.equals(repeatPassword)){
            request.setAttribute("errorMessage", "Passwords are not equal!");
            return ShowChangePasswordCommand.INSTANCE.execute(request, response);
        }
        String salt = BCrypt.gensalt(15);
        String passwordHash = BCrypt.hashpw(password, salt);
        userService.changeUserPassword(userId, passwordHash);
        return LogoutCommand.INSTANCE.execute(request, response);
    }
}
