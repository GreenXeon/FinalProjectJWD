package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PeriodicalDao;
import by.epam.jwd.finalproj.dao.impl.UserDao;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService implements CommonService<UserDto> {

    private static final String SILLY_PASSWORD = "HelloWorld123";

    private final UserDao userDao;

    private final Logger logger = LogManager.getLogger(UserService.class);

    public UserService() {
        this.userDao = new UserDao();
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        Optional<List<User>> allUsers = userDao.findAll();
        if (!allUsers.isPresent()){
            return Optional.empty();
        }
        return Optional.of(
                allUsers.get()
                        .stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<UserDto> save(UserDto dto) {
        return Optional.of(convertToDto(userDao.save(convertToEntity(dto)).get()));
    }

    @Override
    public Optional<UserDto> update(UserDto dto) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }

    public Optional<UserDto> login(String login, String password){
        final Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent()){
            String passwordHash = user.get().getPassword();
            boolean equalPass = BCrypt.checkpw(password, passwordHash);
            if (equalPass){
                logger.info("User " + login + " is valid");
                return user.map(this::convertToDto);
            }
            else {
                logger.info("Password is wrong");
                return Optional.empty();
            }
        }
        else {
            boolean result = BCrypt.checkpw(password, BCrypt.hashpw(SILLY_PASSWORD, BCrypt.gensalt(15)));            logger.info("User is not found");
            return Optional.empty();
        }
    }

    private UserDto convertToDto(User user){
        return new UserDto(user.getId(), user.getLogin(), user.getPassword(), user.getName(), user.getSurname(), user.getEmail(),  user.getRole(), user.isBlocked(), user.getRegistrationDate());
    }

    private User convertToEntity(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getPassword(),
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getRegistrationDate(),
                userDto.isBlocked(),
                userDto.getRole()
        );
    }
}
