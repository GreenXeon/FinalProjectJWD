package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.periodicals.Periodical;

import java.util.List;
import java.util.Optional;

public class PeriodicalDao implements CommonDao<Periodical> {
    @Override
    public Optional<List<Periodical>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Periodical> save(Periodical entity) {
        return Optional.empty();
    }
}
