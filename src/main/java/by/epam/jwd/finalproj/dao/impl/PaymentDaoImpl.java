package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.PaymentDao;
import by.epam.jwd.finalproj.model.payment.Payment;
import by.epam.jwd.finalproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentDaoImpl implements PaymentDao {

    private final String CREATE_PAYMENT = "INSERT INTO payments (p_id, user_id, payment_time, payment_cost)" +
            "VALUES (?, ?, ?, ?)";

    private final Logger logger = LogManager.getLogger(PaymentDaoImpl.class);

    @Override
    public Optional<Payment> save(Payment entity) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(CREATE_PAYMENT);
            preparedStatement.setString(1, entity.getPaymentId());
            preparedStatement.setInt(2, entity.getUserId());
            preparedStatement.setTimestamp(3, entity.getPaymentTime());
            preparedStatement.setBigDecimal(4, entity.getPaymentCost());
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows == 0){
                throw new SQLException("Payment " + entity.getPaymentId() + " was not inserted");
            }
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
            return Optional.empty();
        }
    }
}
