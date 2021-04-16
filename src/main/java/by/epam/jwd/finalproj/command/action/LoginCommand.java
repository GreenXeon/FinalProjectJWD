package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;

public enum LoginCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;

    LoginCommand() {
        userService = UserService.INSTANCE;
    }

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            final String login = String.valueOf(request.getParameter("login")).trim();
            final String password = String.valueOf(request.getParameter("password")).trim();
            final String rememberMe = request.getParameter("remember");

            if (!isValidLogin(login) || !isValidPassword(password)) {
                request.setAttribute("errorMessage", "Check your data!");
                return ShowLoginPageCommand.INSTANCE.execute(request, response);
            }

            final Optional<UserDto> user = userService.login(login, password);
            if (!user.isPresent()) {
                request.setSessionAttribute("role", Roles.GUEST);
                request.setAttribute("errorMessage", "Wrong login or password!");
                return ShowLoginPageCommand.INSTANCE.execute(request, response);
            }

            if (user.get().isBlocked()) {
                request.setAttribute("errorMessage", "User is banned");
                return ShowLoginPageCommand.INSTANCE.execute(request, response);
            }

            request.setSessionAttribute("login", user.get().getLogin());
            request.setSessionAttribute("userId", user.get().getId());
            request.setSessionAttribute("role", user.get().getRole().name());
            if (rememberMe != null) {
                final String userData = user.get().getLogin() + ":" + user.get().getRegistrationDate().toString();
                final String salt = BCrypt.gensalt(10);
                final String hashedToken = BCrypt.hashpw(userData, salt) + ":" + user.get().getId();
                Cookie userToken = new Cookie("userToken", hashedToken);
                userToken.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(userToken);
            }

            String userRoleName = user.get().getRole().name();
            if (userRoleName.equalsIgnoreCase("user")) {
                return ShowMainPageCommand.INSTANCE.execute(request, response);
            } else {
                return ShowMainAdminPageCommand.INSTANCE.execute(request, response);
            }
        } catch (NullPointerException e){
            logger.error("Incorrect data for login");
            request.setAttribute("errorMessage", "Check your data!");
            return ShowLoginPageCommand.INSTANCE.execute(request, response);
        } catch (Exception e){
            logger.error(e.getMessage());
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }
    }
}
