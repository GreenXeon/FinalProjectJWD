package by.epam.jwd.finalproj.dao;

import by.epam.jwd.finalproj.model.payment.Payment;

import java.util.Optional;

/**
 * Interface for implementing by class,
 * representing {@link Payment}'s DAO pattern
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface PaymentDao {
    Optional<Payment> save(Payment entity);
}
