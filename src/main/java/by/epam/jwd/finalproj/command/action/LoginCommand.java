package by.epam.jwd.finalproj.command.action;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.page.ShowLoginPageCommand;
import by.epam.jwd.finalproj.command.page.admin.ShowMainAdminPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public enum LoginCommand implements Command {
    INSTANCE;

    private final Logger logger = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;

    LoginCommand() {
        userService = new UserService();
    }

    @Override
    public ResponseContext execute(RequestContext request) {
        ResponseContext result = null;
        final String login = String.valueOf(request.getParameter("login")).trim();
        final String password = String.valueOf(request.getParameter("password")).trim();

        if (login.isEmpty()){
            request.setAttribute("errorMessage", "Login is empty!");
            return ShowLoginPageCommand.INSTANCE.execute(request);
        }
        else if (password.isEmpty()){
            request.setAttribute("errorMessage", "Password is empty!");
            return ShowLoginPageCommand.INSTANCE.execute(request);
        }

        final Optional<UserDto> user = userService.login(login, password);
        if (user.isPresent()){
            request.setSessionAttribute("role", user.get().getRole().name());
            request.setSessionAttribute("login", user.get().getLogin());
            if (user.get().getRole().name().equalsIgnoreCase("user")){
                result = ShowMainPageCommand.INSTANCE.execute(request);
            }
            else {
                result = ShowMainAdminPageCommand.INSTANCE.execute(request);
            }
        }
        else{
            request.setAttribute("errorMessage", "Wrong login or password!");
            result = ShowLoginPageCommand.INSTANCE.execute(request);
        }
        return result;
    }
}
