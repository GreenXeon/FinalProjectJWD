package by.epam.jwd.finalproj.command.action.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowProfilePageCommand;
import by.epam.jwd.finalproj.service.impl.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public enum TopUpBalanceCommand implements Command {
    INSTANCE;

    private final UserService userService;

    TopUpBalanceCommand(){
        this.userService = UserService.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(TopUpBalanceCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        if(request.getSessionAttribute("userId") == null){
            request.setAttribute("errorMessage", "Enter balance to top up!");
            return ShowProfilePageCommand.INSTANCE.execute(request, response);
        }
        final int userId = (int) request.getSessionAttribute("userId");
        try {
            BigDecimal sumToTopUp = new BigDecimal(request.getParameter("balancer"));
            boolean result = userService.topUpUserBalance(userId, sumToTopUp);
            if (!result){
                logger.error("Balance is not updated");
                return ShowErrorPageCommand.INSTANCE.execute(request, response);
            }
            return ShowProfilePageCommand.INSTANCE.execute(request, response);
        } catch (NumberFormatException e){
            request.setAttribute("errorMessage", "Enter correct value!");
            return ShowProfilePageCommand.INSTANCE.execute(request, response);
        }
    }
}
