package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.subscription.Subscription;

import java.util.List;
import java.util.Optional;

public class SubscriptionDao implements CommonDao<Subscription> {
    @Override
    public Optional<List<Subscription>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Subscription> save(Subscription entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Subscription> update(Subscription entity) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
