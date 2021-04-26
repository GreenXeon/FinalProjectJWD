package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.ShowSignUpPageCommand;
import by.epam.jwd.finalproj.model.Role;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum SignUpCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(SignUpCommand.class);

    private final UserServiceImpl userService;

    SignUpCommand(){
        this.userService = UserServiceImpl.INSTANCE;
    }

    private final int SALT_ROUNDS = 15;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            String login = request.getParameter(USER_LOGIN).trim();
            String password = request.getParameter(USER_PASSWORD).trim();
            String passwordSecond = request.getParameter(SECOND_PASSWORD).trim();
            String userEmail = request.getParameter(USER_EMAIL).trim();
            Route signUpRoute = ShowSignUpPageCommand.INSTANCE.execute(request, response);
            if (!isValidLogin(login) || !isValidPassword(password) || !isValidPassword(passwordSecond) || !isValidEmail(userEmail)) {
                request.setAttribute(ERROR, "Check your data!");
                return signUpRoute;
            }
            if (!password.equals(passwordSecond)){
                request.setAttribute(ERROR, "Passwords differ!");
                return signUpRoute;
            }
            if (userService.userExists(login)){
                request.setAttribute(ERROR, "User with this login exists!");
                return signUpRoute;
            }
            String salt = BCrypt.gensalt(SALT_ROUNDS);
            String passwordHash = BCrypt.hashpw(password, salt);
            Optional<UserDto> user = userService.save(
                    new UserDto.Builder()
                        .withLogin(login)
                        .withPassword(passwordHash)
                        .withEmail(userEmail)
                        .withCash(BigDecimal.ZERO)
                        .withRegistrationDate(new Timestamp(System.currentTimeMillis()))
                        .withRole(Role.USER)
                        .build()
            );
            if (user.isPresent()) {
                return ShowLoginPageCommand.INSTANCE.execute(request, response);
            } else {
                request.setAttribute(ERROR, "User is not signed up");
                return signUpRoute;
            }
        } catch (NullPointerException e) {
            logger.error("Incorrect data for sign up");
            request.setAttribute(ERROR, "Check your data!");
            return ShowSignUpPageCommand.INSTANCE.execute(request, response);
        }
    }
}
