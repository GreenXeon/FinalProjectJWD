package by.epam.jwd.finalproj.service;

import by.epam.jwd.finalproj.model.subscription.Subscription;
import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.model.user.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Interface for implementing by class, representing {@link SubscriptionDto}'s service
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface SubscriptionService {
    Optional<List<SubscriptionDto>> findByUserId(int userId);

    List<SubscriptionDto> findByPhrase(int userId, String phrase);

    Optional<SubscriptionDto> save(SubscriptionDto dto);

    Subscription convertToEntity(SubscriptionDto dto);

    SubscriptionDto convertToDto(Subscription entity);
}
