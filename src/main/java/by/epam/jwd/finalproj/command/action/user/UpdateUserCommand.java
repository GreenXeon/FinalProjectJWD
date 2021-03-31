package by.epam.jwd.finalproj.command.action.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.user.ShowProfilePageCommand;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

public enum UpdateUserCommand implements Command {
    INSTANCE;

    private final UserService userService;

    UpdateUserCommand(){
        this.userService = new UserService();
    }

    private final Logger logger = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            //todo: validation
            String login = request.getParameter("login");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            int id = (int) request.getSessionAttribute("userId");
            //todo: builder
            UserDto newUser = new UserDto(id, login, "", name, surname, email, BigDecimal.ZERO, null, false, null);
            Optional<UserDto> updatedUser = userService.update(newUser);
            if (!updatedUser.isPresent()){
                logger.error("User is not updated");
                throw new Exception("User is not updated");
            }
            return ShowProfilePageCommand.INSTANCE.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
    }
}
