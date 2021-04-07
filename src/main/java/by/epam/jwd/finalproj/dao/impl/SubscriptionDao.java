package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.subscription.Subscription;
import by.epam.jwd.finalproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SubscriptionDao implements CommonDao<Subscription> {

    private final String CREATE_SUBSCRIPTION = "INSERT INTO subscriptions (user_id, periodical_id, sub_date, payment_id, sub_cost) " +
            "VALUES (?, ?, ?, ?, ?)";

    private final Logger logger = LogManager.getLogger(SubscriptionDao.class);

    @Override
    public Optional<List<Subscription>> findAll() {
        return Optional.empty();
    }

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
    public Optional<Subscription> update(Subscription entity) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
