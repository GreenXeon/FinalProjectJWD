package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.SubscriptionDao;
import by.epam.jwd.finalproj.model.subscription.Subscription;
import by.epam.jwd.finalproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private final String CREATE_SUBSCRIPTION = "INSERT INTO subscriptions (user_id, periodical_id, sub_date, payment_id, sub_cost) " +
            "VALUES (?, ?, ?, ?, ?)";

    private final String GET_SUBSCRIPTIONS_BY_ID = "SELECT s_id, p_name, payment_id, sub_date, sub_cost FROM subscriptions" +
            " INNER JOIN periodicals p ON subscriptions.periodical_id = p.id WHERE user_id = ?";

    private final Logger logger = LogManager.getLogger(SubscriptionDaoImpl.class);

    @Override
    public Optional<Subscription> save(Subscription entity) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(CREATE_SUBSCRIPTION);
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getPeriodicalId());
            preparedStatement.setTimestamp(3, entity.getSubscriptionDate());
            preparedStatement.setString(4, entity.getPaymentId());
            preparedStatement.setBigDecimal(5, entity.getSubscriptionCost());
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows == 0){
                throw new SQLException("Subscription is not inserted");
            }
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Subscription>> findByUserId(int userId) {
        List<Subscription> subscriptions = new ArrayList<>();
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(GET_SUBSCRIPTIONS_BY_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Subscription subscription;
            while (resultSet.next()){
                subscription = new Subscription.Builder()
                        .withId(resultSet.getInt(1))
                        .withPeriodicalName(resultSet.getString(2))
                        .withPaymentId(resultSet.getString(3))
                        .withSubscriptionDate(resultSet.getTimestamp(4))
                        .withSubscriptionCost(resultSet.getBigDecimal(5))
                        .build();
                subscriptions.add(subscription);
            }
            return Optional.of(subscriptions);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
            return Optional.empty();
        }
    }
}
