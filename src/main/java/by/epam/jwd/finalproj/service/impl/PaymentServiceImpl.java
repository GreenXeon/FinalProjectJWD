package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PaymentDaoImpl;
import by.epam.jwd.finalproj.model.payment.Payment;
import by.epam.jwd.finalproj.model.payment.PaymentDto;
import by.epam.jwd.finalproj.service.PaymentService;

import java.util.Optional;

public enum PaymentServiceImpl implements PaymentService {
    INSTANCE;

    private final PaymentDaoImpl paymentDao;

    PaymentServiceImpl(){
        this.paymentDao = new PaymentDaoImpl();
    }

    @Override
    public Optional<PaymentDto> save(PaymentDto dto) {
        return Optional.of(convertToDto(paymentDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Payment convertToEntity(PaymentDto dto){
        return new Payment.Builder()
                .withId(dto.getPaymentId())
                .withPaymentCost(dto.getPaymentCost())
                .withPaymentTime(dto.getPaymentTime())
                .withUserId(dto.getUserId())
                .build();
    }

    @Override
    public PaymentDto convertToDto(Payment payment){
        return new PaymentDto.Builder()
                .withId(payment.getPaymentId())
                .withPaymentCost(payment.getPaymentCost())
                .withPaymentTime(payment.getPaymentTime())
                .withUserId(payment.getUserId())
                .build();
    }
}
