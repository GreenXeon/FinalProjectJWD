package by.epam.jwd.finalproj.service;

import by.epam.jwd.finalproj.dao.UserDao;
import by.epam.jwd.finalproj.model.user.User;
import by.epam.jwd.finalproj.model.user.UserDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Interface for implementing by class, representing {@link UserDto}'s service
 *
 *
 * @author Zakhar Shishkin
 * @since 1.0
 */

public interface UserService {
    /**
     * Method for finding all {@link UserDto}'s instances in database
     * using {@link UserDao#findAll()}
     *
     * @return {@link Optional} of {@link List} of {@link UserDto}
     */
    Optional<List<UserDto>> findAll();

    /**
     * Finds and returns {@link UserDto} if {@link UserDto#getLogin()} contains specific phrase
     *
     * @param phrase {@link String} phrase to search
     * @return {@link List} of {@link UserDto}
     */
    List<UserDto> findByPhraseLogin(String phrase);

    /**
     * Finds concrete {@link UserDto} in database by {@link UserDto#getLogin()}
     *
     * @param login {@link UserDto}'s login
     * @return {@link Optional} of {@link UserDto}
     */
    Optional<UserDto> findByLogin(String login);

    /**
     * Sets {@link UserDto}'s role, represented by {@link by.epam.jwd.finalproj.model.Role}'s int value
     *
     * @param userId {@link UserDto}'s identifier
     * @param userRole {@link by.epam.jwd.finalproj.model.Role}'s integer representation
     */
    void setUserRole(int userId, int userRole);

    /**
     * Method for banning or unbanning specific {@link UserDto},
     * identified by {@param userId}
     * @param userId {@link UserDto}'s identifier
     * @param status {@link by.epam.jwd.finalproj.model.UserBanStatus}'s integer representation
     */
    void setUserStatus(int userId, int status);

    /**
     * Checks if there is existing {@link UserDto} with the same login
     *
     * @param login {@link UserDto}'s login
     * @return {@link Boolean} as result
     */
    boolean userExists(String login);

    /**
     * Changes concrete {@link UserDto}'s password hash, stored in database
     *
     * @param userId {@link UserDto}'s identifier
     * @param passwordHash {@link String} password hash
     */
    void changeUserPassword(int userId, String passwordHash);

    /**
     * Finds and returns {@link UserDto} using identifier
     *
     * @param id {@link UserDto}'s identifier
     * @return {@link Optional} of {@link UserDto}
     */
    Optional<UserDto> findById(int id);

    /**
     * Saves specific {@link UserDto}'s entity in database
     *
     * @param dto {@link UserDto} instance
     * @return {@link Optional} of {@link User}
     */
    Optional<UserDto> save(UserDto dto);

    /**
     * Updates specific {@link UserDto}'s entity
     *
     * @param dto {@link UserDto} instance
     * @return {@link Optional} of {@link UserDto}
     */
    Optional<UserDto> update(UserDto dto);

    /**
     * Sings in user checking existence with login and password hash
     *
     * @param login {@link UserDto}'s login
     * @param password {@link UserDto}'s password
     * @return {@link Optional} of {@link UserDto}
     */
    Optional<UserDto> login(String login, String password);

    /**
     *  Tops up {@link UserDto#getCash()}
     *  Abstraction for real money debiting by credit/debit card
     *
     * @param userId {@link UserDto}'s identifier
     * @param sumOfMoney {@link BigDecimal} sum to top up
     * @return {@link Boolean}
     */
    boolean topUpUserBalance(int userId, BigDecimal sumOfMoney);

    /**
     * Lowers {@link UserDto#getCash()} after buying subscription
     *
     * @param userId {@link UserDto}'s identifier
     * @param sumToLower {@link BigDecimal} cost of payment
     * @return {@link Boolean}
     */
    boolean lowerUserBalance(int userId, BigDecimal sumToLower);

    /**
     * Converts {@link User} to {@link UserDto}
     *
     * @param user {@link User} instance to convert
     * @return converted instance as {@link UserDto}
     */
    UserDto convertToDto(User user);

    /**
     * Converts {@link UserDto} to {@link User}
     *
     * @param userDto {@link UserDto} instance to convert
     * @return converted instance as {@link User}
     */
    User convertToEntity(UserDto userDto);
}
