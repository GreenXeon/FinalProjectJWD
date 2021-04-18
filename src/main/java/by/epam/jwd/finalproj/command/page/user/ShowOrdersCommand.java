package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.exception.CommandException;
import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.service.impl.SubscriptionServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static by.epam.jwd.finalproj.util.ParameterNames.*;

import java.util.List;
import java.util.Optional;

public enum ShowOrdersCommand implements Command {
    INSTANCE;

    private final SubscriptionServiceImpl subscriptionService;

    ShowOrdersCommand(){
        this.subscriptionService = SubscriptionServiceImpl.INSTANCE;
    }

    private final Logger logger = LogManager.getLogger(ShowOrdersCommand.class);

    private static final Route SHOW_ORDERS_RESPONSE = new Route() {
        @Override
        public String getPage() {
            return "/WEB-INF/jsp/user/showOrders.jsp";
        }

        @Override
        public boolean isRedirect() {
            return false;
        }
    };

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        int userId = (int) request.getSessionAttribute(SESSION_USER_ID);
        String phraseToFind = request.getParameter(FINDER);
        if (phraseToFind == null || phraseToFind.isEmpty()){
            Optional<List<SubscriptionDto>> subscriptionsOfUser = subscriptionService.findByUserId(userId);
            try {
                if (!subscriptionsOfUser.isPresent()){
                    throw new CommandException("Subscriptions are not found");
                }
            } catch (CommandException e) {
                logger.error(e.getMessage());
                request.setAttribute(ERROR, e.getMessage());
                return ShowErrorPageCommand.INSTANCE.execute(request, response);
            }
            request.setAttribute(USER_SUBSCRIPTIONS, subscriptionsOfUser.get());
            return SHOW_ORDERS_RESPONSE;
        }
        final List<SubscriptionDto> subscriptions = subscriptionService.findByPhrase(userId, phraseToFind);
        request.setAttribute(USER_SUBSCRIPTIONS, subscriptions);
        return SHOW_ORDERS_RESPONSE;
    }
}
