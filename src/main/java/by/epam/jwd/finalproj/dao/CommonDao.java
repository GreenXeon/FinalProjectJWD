package by.epam.jwd.finalproj.dao;

import java.util.List;
import java.util.Optional;

/**
 * Interface with basic DAO realization
 * Classes that implement this interface have to parametrize it with specific object
 *
 * @param <T> object, reflecting specific DAO class assignment
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface CommonDao<T> {

    /**
     * Finds and returns all {@link T} instances in database
     *
     * @return {@link Optional} of {@link List}, parametrized by {@link T}
     */
    Optional<List<T>> findAll();

    /**
     *Saves instance of {@link T} in database
     *
     * @param entity {@link T}
     * @return {@link Optional} of {@link T}
     */
    Optional<T> save(T entity);

    /**
     * Updates entity of {@link T}, already existing in database
     *
     * @param entity instance of {@link T}
     * @return {@link Optional} of {@link T}
     */
    Optional<T> update(T entity);

    /**
     * Method for deleting entity of {@link T}, stored in database by passing it's id
     * for identification
     *
     * @param id {@link T}'s identifier for searching in database
     * @return method's result by {@link Boolean}
     */
    boolean delete(int id);
}
