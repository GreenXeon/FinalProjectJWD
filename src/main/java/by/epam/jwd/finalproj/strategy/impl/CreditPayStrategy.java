package by.epam.jwd.finalproj.strategy.impl;

import by.epam.jwd.finalproj.strategy.CommonStrategy;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CreditPayStrategy implements CommonStrategy {

    @Override
    public boolean submitPayment(String paymentId, int userId, Timestamp paymentTime, BigDecimal paymentCost) {
        return false;
    }
}
