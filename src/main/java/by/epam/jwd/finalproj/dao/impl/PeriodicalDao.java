package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.Roles;
import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import by.epam.jwd.finalproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PeriodicalDao implements CommonDao<Periodical> {

    private final Logger logger = LogManager.getLogger(PeriodicalDao.class);

    private final String GET_ALL_PERIODICALS = "SELECT * FROM periodicals";
    private final String GET_PERIODICAL_BY_NAME = "SELECT * FROM periodicals WHERE p_name = (?)";
    private final String ADD_PERIODICAL = "INSERT INTO periodicals (p_name, p_author, publish_year, type_id, " +
            "p_cost, p_publisher)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public Optional<List<Periodical>> findAll() {
        List<Periodical> periodicals = new ArrayList<>();
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_PERIODICALS);
            while (resultSet.next()) {
                Periodical periodical = new Periodical(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        PeriodicalType.findById(resultSet.getInt(5)),
                        resultSet.getBigDecimal(6),
                        resultSet.getString(7)
                );
                logger.info("Periodical " + resultSet.getString(1) + " is read");
                periodicals.add(periodical);
            }
            return Optional.of(periodicals);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        logger.info("No periodicals are read");
        return Optional.empty();
    }

    @Override
    public Optional<Periodical> save(Periodical entity) {
        logger.info("saving started");
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final PreparedStatement preparedStatement = conn.prepareStatement(ADD_PERIODICAL);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setInt(3, entity.getPublishYear());
            preparedStatement.setInt(4, entity.getType().getI());
            preparedStatement.setBigDecimal(5, entity.getSubCost());
            preparedStatement.setString(6, entity.getPublisher());
            int rows = preparedStatement.executeUpdate();
            logger.info(rows + " rows were updated");
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }
}
