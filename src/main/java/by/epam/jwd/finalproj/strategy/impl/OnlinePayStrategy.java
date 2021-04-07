package by.epam.jwd.finalproj.strategy.impl;

import by.epam.jwd.finalproj.dao.impl.PaymentDao;
import by.epam.jwd.finalproj.model.payment.Payment;
import by.epam.jwd.finalproj.model.payment.PaymentDto;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalDto;
import by.epam.jwd.finalproj.service.impl.PaymentService;
import by.epam.jwd.finalproj.strategy.CommonStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public class OnlinePayStrategy implements CommonStrategy {

    private final PaymentService paymentService;

    public OnlinePayStrategy(){
        this.paymentService = new PaymentService();
    }

    private final Logger logger = LogManager.getLogger(OnlinePayStrategy.class);

    @Override
    public boolean submitPayment(String paymentId, int userId, Timestamp paymentTime, BigDecimal paymentCost) {
        PaymentDto paymentToSubmit = new PaymentDto.Builder()
                .withId(paymentId)
                .withUserId(userId)
                .withPaymentTime(paymentTime)
                .withPaymentCost(paymentCost)
                .build();
        Optional<PaymentDto> insertedPayment = paymentService.save(paymentToSubmit);
        if (!insertedPayment.isPresent()) {
            logger.error("Payment was not inserted");
            return false;
        }
        return true;
    }
}
