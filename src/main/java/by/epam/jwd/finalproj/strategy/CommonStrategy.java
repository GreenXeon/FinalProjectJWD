package by.epam.jwd.finalproj.strategy;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Classes implementing this interface represent specific
 * strategies of {@link by.epam.jwd.finalproj.model.payment.Payment} processing
 * Main purpose is making application more convenient for scalability
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface CommonStrategy {
    /**
     * Method for creating and submitting {@link by.epam.jwd.finalproj.model.payment.Payment} in a specific way,
     * defining by concrete strategy, implementing this interface
     *
     *
     * @param paymentId {@link by.epam.jwd.finalproj.model.payment.Payment}'s identifier
     * @param userId identifier of the current {@link by.epam.jwd.finalproj.model.user.User}
     * @param paymentTime {@link Timestamp} representing timestamp of payment processing result
     * @param paymentCost total cost of the whole payment represented by {@link BigDecimal}
     * @return {@link Boolean} as a result of submitting
     */
    boolean submitPayment(String paymentId, int userId, Timestamp paymentTime, BigDecimal paymentCost);

    /**
     * Method for lowering concrete {@link by.epam.jwd.finalproj.model.user.User} balance
     * after successful submitting payment and it's processing
     *
     * @param userId {@link by.epam.jwd.finalproj.model.user.User}'s identifier
     * @param cost total money sum, that will be debited from balance
     */
    void lowerUserBalance(int userId, BigDecimal cost);
}
