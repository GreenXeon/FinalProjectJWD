package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowProfilePageCommand;
import by.epam.jwd.finalproj.command.page.ShowUpdatePageCommand;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum UpdateUserCommand implements Command {
    INSTANCE;

    private final UserServiceImpl userService;

    UpdateUserCommand(){
        this.userService = UserServiceImpl.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            String login = request.getParameter(USER_LOGIN);
            String name = request.getParameter(USER_NAME);
            String surname = request.getParameter(USER_SURNAME);
            String email = request.getParameter(USER_EMAIL);
            int id = (int) request.getSessionAttribute(SESSION_USER_ID);
            String sessionLogin = (String) request.getSessionAttribute(SESSION_USER_LOGIN);
            if (!isValidLogin(login) || !isValidUserName(name) || !isValidUserSurname(surname) || !isValidEmail(email)) {
                request.setAttribute(ERROR, "Check your data!");
                return ShowUpdatePageCommand.INSTANCE.execute(request, response);
            }
            if (userService.userExists(login) && !login.equalsIgnoreCase(sessionLogin)) {
                request.setAttribute(ERROR, "User with this login exists!");
                return ShowUpdatePageCommand.INSTANCE.execute(request, response);
            }
            UserDto newUser = new UserDto.Builder()
                    .withId(id)
                    .withLogin(login)
                    .withName(name)
                    .withSurname(surname)
                    .withEmail(email)
                    .withCash(BigDecimal.ZERO)
                    .build();
            Optional<UserDto> updatedUser = userService.update(newUser);
            if (!updatedUser.isPresent()) {
                logger.error("User is not updated");
                throw new CommandException("User is not updated");
            }
            request.setSessionAttribute(SESSION_USER_LOGIN, login);
        } catch (NullPointerException e) {
            request.setAttribute(ERROR, "Check your data!");
            return ShowUpdatePageCommand.INSTANCE.execute(request, response);
        } catch (CommandException e) {
            request.setAttribute(ERROR, e.getMessage());
            logger.error(e.getMessage());
        }
        return ShowProfilePageCommand.INSTANCE.execute(request, response);
    }
}
