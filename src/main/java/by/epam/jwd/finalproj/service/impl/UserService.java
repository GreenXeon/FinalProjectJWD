package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PeriodicalDao;
import by.epam.jwd.finalproj.dao.impl.UserDao;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.CommonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService implements CommonService<UserDto> {

    private final UserDao userDao;

    private final Logger logger = LogManager.getLogger(UserService.class);

    public UserService() {
        this.userDao = new UserDao();
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> save(UserDto dto) {
        return Optional.empty();
    }

    public Optional<UserDto> login(String login, String password){
        final Optional<List<User>> allUsers = userDao.findAll();
        if (allUsers.isPresent()){
            logger.info("Users are present");
            logger.info(login + " - " + allUsers.get().get(0).getLogin());
            List<User> users = new ArrayList<>();
            for (User user : allUsers.get()){
                if (user.getLogin().equalsIgnoreCase(login)){
                    logger.info("User is found");
                    return Optional.of(convertToDto(user));
                }
            }
        }
        logger.info("User is not found");
        return Optional.empty();
    }

    private UserDto convertToDto(User user){
        return new UserDto(user.getLogin(), user.getName(), user.getSurname(), user.getEmail() , user.getRegistrationDate());
    }
}
