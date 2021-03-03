package by.epam.jwd.finalproj.service.impl;

import by.epam.jwd.finalproj.dao.impl.PeriodicalDao;
import by.epam.jwd.finalproj.dao.impl.UserDao;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.UserDto;
import by.epam.jwd.finalproj.service.CommonService;

import java.util.List;
import java.util.Optional;

public class UserService implements CommonService<UserDto> {

    private final UserDao userDao;

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
        final Optional<User> user = userDao.findByLogin(login);
        return null;
    }
}
