package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.model.subscription.SubscriptionDto;
import by.epam.jwd.finalproj.service.CommonService;

import java.util.List;
import java.util.Optional;

public class SubscriptionService implements CommonService<SubscriptionDto> {
    @Override
    public Optional<List<SubscriptionDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<SubscriptionDto> save(SubscriptionDto dto) {
        return Optional.empty();
    }

    @Override
    public Optional<SubscriptionDto> update(SubscriptionDto dto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
