package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.command.user.LoginCommand;
import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements CommonDao<User> {

    private final Logger logger = LogManager.getLogger(UserDao.class);

    @Override
    public Optional<List<User>> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_model?serverTimezone=Europe/Minsk&useSSL=false",
                "root", "root")) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM p_users");
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        null, null, null,
                        resultSet.getTimestamp(7),
                        resultSet.getBoolean(9),
                        Roles.findRoleById(resultSet.getInt(8))
                );
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.empty();
    }

    public Optional<User> findByLogin(String login) {
        return null;
    }
}
