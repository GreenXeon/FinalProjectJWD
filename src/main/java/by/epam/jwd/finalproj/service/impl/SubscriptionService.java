package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.SubscriptionDao;
import by.epam.jwd.finalproj.model.subscription.Subscription;
import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.service.CommonService;

import java.util.List;
import java.util.Optional;

public class SubscriptionService implements CommonService<SubscriptionDto> {

    private final SubscriptionDao subscriptionDao;

    public SubscriptionService(){
        this.subscriptionDao = new SubscriptionDao();
    }

    @Override
    public Optional<List<SubscriptionDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<SubscriptionDto> save(SubscriptionDto dto) {
        return Optional.of(convertToDto(subscriptionDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Optional<SubscriptionDto> update(SubscriptionDto dto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    private Subscription convertToEntity(SubscriptionDto dto){
        return new Subscription.Builder()
            .withUserId(dto.getUserId())
            .withSubscriptionCost(dto.getSubscriptionCost())
            .withSubscriptionDate(dto.getSubscriptionDate())
            .withPaymentId(dto.getPaymentId())
            .withPeriodicalId(dto.getPeriodicalId())
            .build();
    }

    private SubscriptionDto convertToDto(Subscription entity){
        return new SubscriptionDto.Builder()
                .withUserId(entity.getUserId())
                .withSubscriptionCost(entity.getSubscriptionCost())
                .withSubscriptionDate(entity.getSubscriptionDate())
                .withPaymentId(entity.getPaymentId())
                .withPeriodicalId(entity.getPeriodicalId())
                .build();
    }
}
