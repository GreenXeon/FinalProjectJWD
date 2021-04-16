package by.epam.jwd.finalproj.strategy;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface CommonStrategy {
    boolean submitPayment(String paymentId, int userId, Timestamp paymentTime, BigDecimal paymentCost);
    void lowerUserBalance(int userId, BigDecimal cost);
}
