package by.epam.jwd.finalproj.dao;

import by.epam.jwd.finalproj.model.user.User;
import by.epam.jwd.finalproj.model.user.UserDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface for implementing by class,
 * representing {@link User}'s DAO pattern
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface UserDao {
    /**
     * Method for finding all {@link User}'s instances in database using DAO layer
     *
     * @return {@link Optional} of {@link List} of {@link User}
     */
    Optional<List<User>> findAll();

    /**
     * Saves specific {@link User}'s entity in database
     *
     * @param entity {@link User} instance
     * @return {@link Optional} of {@link User}
     */
    Optional<User> save(User entity);

    /**
     * Updates specific {@link User}'s entity in database
     *
     * @param entity {@link User} instance
     * @return {@link Optional} of {@link User}
     */
    Optional<User> update(User entity);

    /**
     * Finds concrete {@link User} in database by login
     *
     * @param login {@link User}'s login
     * @return {@link Optional} of {@link User}
     */
    Optional<User> findByLogin(String login);

    /**
     * Method for banning or unbanning specific {@link User},
     * identified by {@param userId}
     *
     * @param userId {@link User}'s identifier
     * @param status {@link by.epam.jwd.finalproj.model.UserBanStatus}'s integer representation
     * @return {@link Boolean} as result
     */
    boolean setUserBanStatus(int userId, int status);

    /**
     * Changes concrete {@link User}'s password hash, stored in database
     *
     * @param userId {@link User}'s identifier
     * @param passwordHash {@link String} password hash
     * @return {@link Boolean} as result
     */
    boolean changeUserPassword(int userId, String passwordHash);

    /**
     * Sets {@link User}'s role, represented by {@link by.epam.jwd.finalproj.model.Role}'s int value
     *
     * @param userId {@link User}'s identifier
     * @param userRole {@link by.epam.jwd.finalproj.model.Role}'s integer representation
     * @return {@link Boolean} as result
     */
    boolean setUserRole(int userId, int userRole);

    /**
     * Finds and returns concrete {@link User}'s balance
     *
     * @param id {@link User}'s identifier
     * @return {@link BigDecimal} as balance
     */
    BigDecimal findUserBalance(int id);

    /**
     * Checks if there is existing {@link User} with the same login
     *
     * @param login {@link User}'s login
     * @return {@link Boolean} as result
     */
    boolean userExists(String login);

    /**
     * Sets concrete {@link User}'s balance
     *
     * @param id {@link User}'s identifier
     * @param newBalance as new balance
     * @return {@link Boolean} as result
     */
    boolean setUserBalance(int id, BigDecimal newBalance);

    /**
     * Finds and returns {@link User} using identifier
     *
     * @param id {@link User}'s identifier
     * @return {@link Optional} of {@link User}
     */
    Optional<User> findById(int id);
}
