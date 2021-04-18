package by.epam.jwd.finalproj.dao;

import by.epam.jwd.finalproj.model.subscription.Subscription;

import java.util.List;
import java.util.Optional;

/**
 * Interface for implementing by class,
 * representing {@link Subscription}'s DAO pattern
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface SubscriptionDao {
    Optional<Subscription> save(Subscription entity);

    Optional<List<Subscription>> findByUserId(int userId);
}
