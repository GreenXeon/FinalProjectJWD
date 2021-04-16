package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.model.payment.PaymentDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.Assert.*;

public class PaymentServiceTest {

    @Test
    public void save() {
        PaymentDto dto = new PaymentDto.Builder()
                .withId("35635ewfg34t324")
                .withPaymentCost(BigDecimal.TEN)
                .withPaymentTime(new Timestamp(System.currentTimeMillis()))
                .withUserId(6)
                .build();
        Optional<PaymentDto> result = PaymentService.INSTANCE.save(dto);
        assertNotEquals(result, Optional.empty());
    }
}