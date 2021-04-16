package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.ShowSignUpPageCommand;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;

public enum SignUpCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(SignUpCommand.class);

    private final UserService userService;

    SignUpCommand(){
        this.userService = UserService.INSTANCE;
    }

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            final String login = request.getParameter("login").trim();
            final String password = request.getParameter("password").trim();
            final String passwordSecond = request.getParameter("passwordSecond").trim();
            final String userEmail = request.getParameter("email").trim();

            Route errorResult = ShowSignUpPageCommand.INSTANCE.execute(request, response);
            if(!isValidLogin(login) || !isValidPassword(password) || !isValidPassword(passwordSecond) || !isValidEmail(userEmail)) {
                request.setAttribute("errorMessage", "Check your data!");
                return errorResult;
            }
            if(!password.equals(passwordSecond)){
                request.setAttribute("errorMessage", "Passwords differ!");
                return errorResult;
            }
            if (userService.userExists(login)){
                request.setAttribute("errorMessage", "User with this login exists!");
                return errorResult;
            }
            String salt = BCrypt.gensalt(15);
            String passwordHash = BCrypt.hashpw(password, salt);
            Optional<UserDto> user = userService.save(new UserDto.Builder()
                    .withLogin(login)
                    .withPassword(passwordHash)
                    .withEmail(userEmail)
                    .withCash(BigDecimal.ZERO)
                    .withRegistrationDate(new Timestamp(System.currentTimeMillis()))
                    .withRole(Roles.USER)
                    .build());
            if (user.isPresent()) {
                logger.info("saved");
                return ShowLoginPageCommand.INSTANCE.execute(request, response);
            } else {
                logger.error("User is not signed up");
                request.setAttribute("errorMessage", "User is not signed up");
                return errorResult;
            }
        } catch (NullPointerException e) {
            logger.error("Incorrect data for sign up");
            request.setAttribute("errorMessage", "Check your data!");
            return ShowSignUpPageCommand.INSTANCE.execute(request, response);
        } catch (Exception e){
            logger.error(e.getMessage());
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
    }
}
