package by.epam.jwd.finalproj.service;

import by.epam.jwd.finalproj.model.payment.Payment;
import by.epam.jwd.finalproj.model.payment.PaymentDto;
import by.epam.jwd.finalproj.model.user.UserDto;

import java.util.Optional;

/**
 * Interface for implementing by class, representing {@link PaymentDto}'s service
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface PaymentService {
    Optional<PaymentDto> save(PaymentDto dto);

    Payment convertToEntity(PaymentDto dto);

    PaymentDto convertToDto(Payment payment);
}
