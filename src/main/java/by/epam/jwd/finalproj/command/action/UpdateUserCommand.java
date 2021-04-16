package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowProfilePageCommand;
import by.epam.jwd.finalproj.command.page.ShowUpdatePageCommand;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;

public enum UpdateUserCommand implements Command {
    INSTANCE;

    private final UserService userService;

    UpdateUserCommand(){
        this.userService = UserService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            int id = (int) request.getSessionAttribute("userId");
            String sessionLogin = (String) request.getSessionAttribute("login");

            if (!isValidLogin(login) || !isValidUserName(name) || !isValidUserSurname(surname) || !isValidEmail(email)) {
                request.setAttribute("errorMessage", "Check your data!");
                return ShowUpdatePageCommand.INSTANCE.execute(request, response);
            }

            if (userService.userExists(login) && !login.equalsIgnoreCase(sessionLogin)) {
                request.setAttribute("errorMessage", "User with this login exists!");
                return ShowUpdatePageCommand.INSTANCE.execute(request, response);
            }
            UserDto newUser = new UserDto(id, login, "", name, surname, email, BigDecimal.ZERO, null, false, null);
            Optional<UserDto> updatedUser = userService.update(newUser);
            if (!updatedUser.isPresent()) {
                logger.error("User is not updated");
                throw new Exception("User is not updated");
            }
            return ShowProfilePageCommand.INSTANCE.execute(request, response);
        } catch (NullPointerException e) {
            request.setAttribute("errorMessage", "Check your data!");
            return ShowUpdatePageCommand.INSTANCE.execute(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
    }
}
