package by.epam.jwd.finalproj.command.page.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.service.impl.SubscriptionService;
import jdk.nashorn.internal.ir.Optimistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum ShowOrdersCommand implements Command {
    INSTANCE;

    private final SubscriptionService subscriptionService;

    ShowOrdersCommand(){
        this.subscriptionService = SubscriptionService.INSTANCE;
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
        int userId = (int) request.getSessionAttribute("userId");
        String phraseToFind = request.getParameter("finder");
        if (phraseToFind == null || phraseToFind.isEmpty()){
            Optional<List<SubscriptionDto>> subscriptionsOfUser = subscriptionService.findByUserId(userId);
            try {
                if (!subscriptionsOfUser.isPresent()){
                    throw new Exception("Subscriptions are not found");
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ShowErrorPageCommand.INSTANCE.execute(request, response);
            }
            request.setAttribute("userSubscriptions", subscriptionsOfUser.get());
            return SHOW_ORDERS_RESPONSE;
        }
        final List<SubscriptionDto> subscriptions = subscriptionService.findByPhrase(userId, phraseToFind);
        request.setAttribute("userSubscriptions", subscriptions);
        return SHOW_ORDERS_RESPONSE;
    }
}
