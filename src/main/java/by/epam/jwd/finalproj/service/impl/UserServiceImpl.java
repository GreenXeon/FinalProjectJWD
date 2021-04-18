package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.UserDaoImpl;
import by.epam.jwd.finalproj.model.user.User;
import by.epam.jwd.finalproj.model.user.UserDto;
import by.epam.jwd.finalproj.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum UserServiceImpl implements UserService {
    INSTANCE;

    private static final String SILLY_PASSWORD = "HelloWorld123";

    private final UserDaoImpl userDao;

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        Optional<List<User>> allUsers = userDao.findAll();
        return allUsers.map(users -> users
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public List<UserDto> findByPhraseLogin(String phrase){
        List<UserDto> users = this.findAll().orElse(Collections.emptyList());
        if(users.isEmpty()){
            logger.error("Users are not read");
        }
        List<UserDto> foundUsers = new ArrayList<>();
        for(UserDto user : users){
            if(user.getLogin().toLowerCase().contains(phrase.toLowerCase())){
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    @Override
    public Optional<UserDto> findByLogin(String login){
        Optional<User> foundUser = userDao.findByLogin(login);
        return foundUser.map(this::convertToDto);
    }

    @Override
    public void setUserRole(int userId, int userRole){
        userDao.setUserRole(userId, userRole);
    }

    @Override
    public void setUserStatus(int userId, int status){
        boolean result = userDao.setUserBanStatus(userId, status);
        if (!result){
            logger.warn("User's status was not set");
        } else {
            logger.info("Status of user " + userId + " is changed");
        }
    }

    @Override
    public boolean userExists(String login){
        return userDao.userExists(login);
    }

    @Override
    public void changeUserPassword(int userId, String passwordHash){
        userDao.changeUserPassword(userId, passwordHash);
    }

    @Override
    public Optional<UserDto> findById(int id){
        Optional<User> foundUser = userDao.findById(id);
        return foundUser.map(this::convertToDto);
    }

    @Override
    public Optional<UserDto> save(UserDto dto) {
        return Optional.of(convertToDto(userDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Optional<UserDto> update(UserDto dto) {
        return Optional.of(convertToDto(userDao.update(convertToEntity(dto)).get()));
    }

    @Override
    public Optional<UserDto> login(String login, String password){
        final Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent()){
            String passwordHash = user.get().getPassword();
            boolean equalPass = BCrypt.checkpw(password, passwordHash);
            if (equalPass){
                return user.map(this::convertToDto);
            }
            else {
                return Optional.empty();
            }
        }
        else {
            boolean result = BCrypt.checkpw(password, BCrypt.hashpw(SILLY_PASSWORD, BCrypt.gensalt(15)));
            return Optional.empty();
        }
    }

    @Override
    public boolean topUpUserBalance(int userId, BigDecimal sumOfMoney){
        BigDecimal userBalance = userDao.findUserBalance(userId);
        if (userBalance == null){
            logger.error("Error in getting user " + userId + " balance");
            return false;
        }
        BigDecimal newBalance = userBalance.add(sumOfMoney);
        if (newBalance.compareTo(BigDecimal.ZERO) == -1){
            return userDao.setUserBalance(userId, BigDecimal.ZERO);
        } else if (newBalance.compareTo(BigDecimal.valueOf(9999.99)) == 1){
            return userDao.setUserBalance(userId, BigDecimal.valueOf(9999.99));
        }
        return userDao.setUserBalance(userId, newBalance);
    }

    @Override
    public boolean lowerUserBalance(int userId, BigDecimal sumToLower){
        BigDecimal userBalance = userDao.findUserBalance(userId);
        if (userBalance == null){
            logger.error("Error in getting user " + userId + " balance");
            return false;
        }
        BigDecimal newBalance = userBalance.subtract(sumToLower);
        try {
            if (newBalance.compareTo(BigDecimal.ZERO) == -1) {
                throw new Exception("User " + userId + " has not enough funds on balance");
            }
            return userDao.setUserBalance(userId, newBalance);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public UserDto convertToDto(User user){
        return new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(), user.getCash(), user.getRole(), user.isBlocked(), user.getRegistrationDate());
    }

    @Override
    public User convertToEntity(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getCash(),
                userDto.getRegistrationDate(),
                userDto.isBlocked(),
                userDto.getRole()
        );
    }
}
