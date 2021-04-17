package by.epam.jwd.finalproj.service;

import java.util.List;
import java.util.Optional;

/**
 * Interface for connecting DAO and control layers of the application
 * Must be parametrized with the specific object
 *
 * @param <T> object, reflecting specific DAO class assignment
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface CommonService<T> {
    Optional<List<T>> findAll();

    Optional<T> save(T dto);

    Optional<T> update(T dto);

    boolean delete(int id);
}
