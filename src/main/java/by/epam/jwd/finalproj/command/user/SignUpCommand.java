package by.epam.jwd.finalproj.command.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.ShowSignUpPageCommand;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;
import java.util.Optional;

public enum SignUpCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(SignUpCommand.class);

    private final UserService userService;

    SignUpCommand(){
        this.userService = new UserService();
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        final String login = request.getParameter("login").trim();
        final String password = request.getParameter("password").trim();
        final String passwordSecond = request.getParameter("passwordSecond").trim();
        ResponseContext result = ShowSignUpPageCommand.INSTANCE.execute(request);
        if (login.isEmpty()) {
            request.setAttribute("errorMessage", "Login is empty!");
        } else if (password.isEmpty()) {
            request.setAttribute("errorMessage", "Password is empty!");
        } else if (passwordSecond.isEmpty()) {
            request.setAttribute("errorMessage", "Repeat your password!");
        } else if (!password.equals(passwordSecond)) {
            request.setAttribute("errorMessage", "Passwords are not equal!");
        } else {
            //todo: existing login check
            String salt = BCrypt.gensalt(15);
            String passwordHash = BCrypt.hashpw(password, salt);
            Optional<UserDto> user = userService.save(new UserDto(login, passwordHash, null, null, null, Roles.USER,
                    false, new Timestamp(System.currentTimeMillis())));
            if (user.isPresent()) {
                logger.info("saved");
                result = ShowLoginPageCommand.INSTANCE.execute(request);
            }
        }
        return result;
    }
}
