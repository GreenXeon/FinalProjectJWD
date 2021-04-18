package by.epam.jwd.finalproj.strategy.impl;

import by.epam.jwd.finalproj.model.payment.PaymentDto;
import by.epam.jwd.finalproj.service.impl.PaymentServiceImpl;
import by.epam.jwd.finalproj.service.impl.UserServiceImpl;
import by.epam.jwd.finalproj.strategy.CommonStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public class OnlinePayStrategy implements CommonStrategy {

    private final PaymentServiceImpl paymentService;

    private final UserServiceImpl userService;

    public OnlinePayStrategy(){
        this.paymentService = PaymentServiceImpl.INSTANCE;
        this.userService = UserServiceImpl.INSTANCE;
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

    @Override
    public void lowerUserBalance(int userId, BigDecimal paymentCost) {
        userService.lowerUserBalance(userId, paymentCost);
    }
}
