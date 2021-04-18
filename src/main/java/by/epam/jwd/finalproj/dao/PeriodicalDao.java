package by.epam.jwd.finalproj.dao;

import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.user.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface for implementing by class,
 * representing {@link Periodical}'s DAO pattern
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface PeriodicalDao {
    Optional<List<Periodical>> findAll();

    Optional<List<Periodical>> findForCurrentUser(int userId);

    Optional<Periodical> save(Periodical entity);

    Optional<Periodical> update(Periodical entity);

    boolean delete(int id);

    Optional<Periodical> findById(int id);

    Optional<Periodical> findByName(String name);
}
