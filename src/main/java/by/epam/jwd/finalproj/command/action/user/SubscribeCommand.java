package by.epam.jwd.finalproj.command.action.user;

import by.epam.jwd.finalproj.command.Command;
import by.epam.jwd.finalproj.command.RequestContext;
import by.epam.jwd.finalproj.command.ResponseContext;
import by.epam.jwd.finalproj.command.Route;
import by.epam.jwd.finalproj.command.page.ShowErrorPageCommand;
import by.epam.jwd.finalproj.command.page.ShowMainPageCommand;
import by.epam.jwd.finalproj.strategy.CommonStrategy;
import by.epam.jwd.finalproj.strategy.StrategyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public enum SubscribeCommand implements Command {
    INSTANCE;

    private CommonStrategy paymentStrategy;

    public void setPaymentStrategy(CommonStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    private final Logger logger = LogManager.getLogger(SubscribeCommand.class);

    @Override
    public Route execute(RequestContext request, ResponseContext response) {
        final String strategyName = request.getParameter("payment-type");
        //todo: validation from url when not enough money for online

        final CommonStrategy chosenStrategy = StrategyManager.findStrategyByName(strategyName);
        this.setPaymentStrategy(chosenStrategy);

        final String paymentId = createPaymentId();
        final int userId = (int) request.getSessionAttribute("userId");
        final Timestamp paymentTime = new Timestamp(System.currentTimeMillis());
        final BigDecimal paymentCost = (BigDecimal) request.getSessionAttribute("totalCost");
        final boolean payment = paymentStrategy.submitPayment(paymentId, userId, paymentTime, paymentCost);
        logger.info("Cost is " + paymentCost);
        if (!payment){
            return ShowErrorPageCommand.INSTANCE.execute(request, response);
        }

        return ShowMainPageCommand.INSTANCE.execute(request, response);
    }

    public String createPaymentId(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
