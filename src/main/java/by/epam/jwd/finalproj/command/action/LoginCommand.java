package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.Role;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import java.util.Optional;

import static by.epam.jwd.finalproj.validator.Validator.*;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

public enum LoginCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(LoginCommand.class);

    private final UserServiceImpl userService;

    LoginCommand() {
        userService = UserServiceImpl.INSTANCE;
    }

    private final int SALT_ROUNDS = 15;
    private final int COOKIE_FOR_MONTH = 60 * 60 * 24 * 30;

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        try {
            Route loginPage = ShowLoginPageCommand.INSTANCE.execute(request, response);
            String login = String.valueOf(request.getParameter(USER_LOGIN)).trim();
            String password = String.valueOf(request.getParameter(USER_PASSWORD)).trim();
            String rememberMe = request.getParameter(REMEMBER_ME);
            if (!isValidLogin(login) || !isValidPassword(password)) {
                request.setAttribute(ERROR, "Check your data!");
                return loginPage;
            }
            Optional<UserDto> user = userService.login(login, password);
            if (!user.isPresent()) {
                request.setSessionAttribute(SESSION_USER_ROLE, Role.GUEST);
                request.setAttribute(ERROR, "Wrong login or password!");
                return loginPage;
            }
            if (user.get().isBlocked()) {
                request.setAttribute(ERROR, "User is banned");
                return loginPage;
            }
            request.setSessionAttribute(SESSION_USER_LOGIN, user.get().getLogin());
            request.setSessionAttribute(SESSION_USER_ID, user.get().getId());
            request.setSessionAttribute(SESSION_USER_ROLE, user.get().getRole().name());
            if (rememberMe != null) {
                String userData = user.get().getLogin() + ":" + user.get().getRegistrationDate().toString();
                String salt = BCrypt.gensalt(SALT_ROUNDS);
                String hashedToken = BCrypt.hashpw(userData, salt) + ":" + user.get().getId();
                Cookie userToken = new Cookie(USER_TOKEN_COOKIE, hashedToken);
                userToken.setMaxAge(COOKIE_FOR_MONTH);
                response.addCookie(userToken);
            }
            String userRoleName = user.get().getRole().name();
            return userRoleName.equalsIgnoreCase("user") ? ShowMainPageCommand.INSTANCE.execute(request, response)
                    : ShowMainAdminPageCommand.INSTANCE.execute(request, response);
        } catch (NullPointerException e){
            logger.error("Incorrect data for login");
            request.setAttribute(ERROR, "Check your data!");
            return ShowLoginPageCommand.INSTANCE.execute(request, response);
        }
    }
}
