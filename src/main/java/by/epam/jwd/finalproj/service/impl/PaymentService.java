package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PaymentDao;
import by.epam.jwd.finalproj.model.payment.Payment;
import by.epam.jwd.finalproj.model.payment.PaymentDto;
import by.epam.jwd.finalproj.service.CommonService;

import java.util.List;
import java.util.Optional;

public class PaymentService implements CommonService<PaymentDto> {

    private final PaymentDao paymentDao;

    PaymentService(){
        this.paymentDao = new PaymentDao();
    }

    @Override
    public Optional<List<PaymentDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentDto> save(PaymentDto dto) {
        return Optional.of(convertToDto(paymentDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Optional<PaymentDto> update(PaymentDto dto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    private Payment convertToEntity(PaymentDto dto){
        return new Payment.Builder()
                .withId(dto.getPaymentId())
                .withPaymentCost(dto.getPaymentCost())
                .withPaymentTime(dto.getPaymentTime())
                .withUserId(dto.getUserId())
                .build();
    }

    private PaymentDto convertToDto(Payment payment){
        return new PaymentDto.Builder()
                .withId(payment.getPaymentId())
                .withPaymentCost(payment.getPaymentCost())
                .withPaymentTime(payment.getPaymentTime())
                .withUserId(payment.getUserId())
                .build();
    }
}
