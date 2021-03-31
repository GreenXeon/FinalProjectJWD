package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements CommonDao<User> {

    private final Logger logger = LogManager.getLogger(UserDao.class);

    private final String GET_ALL_USERS = "SELECT * FROM p_users";
    private final String GET_USER_BY_LOGIN = "SELECT * FROM p_users WHERE u_login = (?)";
    private final String REGISTER_USER = "INSERT INTO p_users (u_login, u_password, u_name, u_surname, " +
            "u_email, u_cash, u_registration, u_role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_USER = "UPDATE p_users SET u_login = ?, u_name = ?, u_surname = ?," +
            "u_email = ? WHERE id = ?";
    private final String GET_USER_BY_ID = "SELECT * FROM p_users WHERE id = (?)";
    private final String GET_USER_BALANCE = "SELECT u_cash FROM p_users WHERE id = (?)";
    private final String SET_USER_BALANCE = "UPDATE p_users SET u_cash = ? WHERE id = ?";

    @Override
    public Optional<List<User>> findAll() {
        List<User> users = new ArrayList<>();
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getBigDecimal(7),
                        resultSet.getTimestamp(8),
                        resultSet.getBoolean(10),
                        Roles.findRoleById(resultSet.getInt(9))
                );
                logger.info("User " + resultSet.getString(2) + " is read");
                users.add(user);
            }
            return Optional.of(users);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("No users are read");
        return Optional.empty();
    }

    @Override
    public Optional<User> save(User entity) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final PreparedStatement preparedStatement = conn.prepareStatement(REGISTER_USER);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getSurname());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setBigDecimal(6, entity.getCash());
            preparedStatement.setTimestamp(7, entity.getRegistrationDate());
            preparedStatement.setInt(8, entity.getRole().getI());
            int rows = preparedStatement.executeUpdate();
            logger.info(rows + " rows were updated");
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User entity) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_USER);
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getSurname());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setInt(5, entity.getId());
            int updatedRows = preparedStatement.executeUpdate();
            logger.info(updatedRows + " row(-s) were updated");
            if (updatedRows == 0){
                logger.error("User " + entity.getId() + " is not updated");
                throw new SQLException("User is not updated");
            }
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    public Optional<User> findByLogin(String login) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getBigDecimal(7),
                        resultSet.getTimestamp(8),
                        resultSet.getBoolean(10),
                        Roles.findRoleById(resultSet.getInt(9))
                );
                logger.info("User " + resultSet.getString(2) + " is read");
                return Optional.of(user);
            }
        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public BigDecimal findUserBalance(int id){
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BALANCE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return BigDecimal.valueOf(resultSet.getDouble(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean setUserBalance(int id, BigDecimal newBalance) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(SET_USER_BALANCE);
            preparedStatement.setBigDecimal(1, newBalance);
            preparedStatement.setInt(2, id);
            int updatedRows = preparedStatement.executeUpdate();
            logger.info(updatedRows + " row(-s) were updated");
            if (updatedRows == 0){
                throw new SQLException("0 rows were updated");
            }
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

        public Optional<User> findById(int id) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final PreparedStatement preparedStatement = conn.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getBigDecimal(7),
                        resultSet.getTimestamp(8),
                        resultSet.getBoolean(10),
                        Roles.findRoleById(resultSet.getInt(9))
                );
                logger.info("User " + resultSet.getString(2) + " is read");
                return Optional.of(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
