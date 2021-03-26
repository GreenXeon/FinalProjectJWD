package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.dao.CommonDao;
import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import by.epam.jwd.finalproj.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PeriodicalDao implements CommonDao<Periodical> {

    private final Logger logger = LogManager.getLogger(PeriodicalDao.class);

    private final String GET_ALL_PERIODICALS = "SELECT * FROM periodicals";
    private final String GET_PERIODICAL_BY_NAME = "SELECT * FROM periodicals WHERE p_name = (?)";
    private final String GET_PERIODICAL_BY_ID = "SELECT * FROM periodicals WHERE id = (?)";
    private final String ADD_PERIODICAL = "INSERT INTO periodicals (p_name, p_author, publish_date, type_id, " +
            "p_cost, p_publisher) VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_PERIODICAL = "UPDATE periodicals SET p_name = ?, p_author = ?," +
            "publish_date = ?, type_id = ?, p_cost = ?, p_publisher = ? WHERE id = ?";
    private final String DELETE_PERIODICAL_BY_ID = "DELETE FROM periodicals WHERE id = ?";

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
                        resultSet.getDate(4).toLocalDate(),
                        PeriodicalType.findById(resultSet.getInt(5)),
                        resultSet.getBigDecimal(6),
                        resultSet.getString(7)
                );
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
            preparedStatement.setObject(3, entity.getPublishDate());
            preparedStatement.setInt(4, entity.getType().getI());
            preparedStatement.setBigDecimal(5, entity.getSubCost());
            preparedStatement.setString(6, entity.getPublisher());
            int addedRows = preparedStatement.executeUpdate();
            logger.info(addedRows + " rows were added");
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Periodical> update(Periodical entity) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PERIODICAL);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setObject(3, entity.getPublishDate());
            preparedStatement.setInt(4, entity.getType().getI());
            preparedStatement.setBigDecimal(5, entity.getSubCost());
            preparedStatement.setString(6, entity.getPublisher());
            preparedStatement.setInt(7, entity.getId());
            int updatedRows = preparedStatement.executeUpdate();
            logger.info(updatedRows + " rows were updated");
            return Optional.of(entity);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(int id) {
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()) {
            final PreparedStatement preparedStatement = conn.prepareStatement(DELETE_PERIODICAL_BY_ID);
            preparedStatement.setInt(1, id);
            int deletedRows = preparedStatement.executeUpdate();
            if (deletedRows > 0) {
                logger.info("Successful deleting of " + deletedRows + " row(-s) - Periodical with id " + id);
                return true;
            } else {
                logger.info("Unsuccessful deleting of periodical with id " + id);
                return false;
            }
        } catch (SQLException throwables) {
            logger.error("Deleting periodical with " + id + " id is failed");
            throwables.printStackTrace();
            return false;
        }
    }

    public Optional<Periodical> findById(int id){
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final PreparedStatement preparedStatement = conn.prepareStatement(GET_PERIODICAL_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Periodical periodical = new Periodical(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        PeriodicalType.findById(resultSet.getInt(5)),
                        resultSet.getBigDecimal(6),
                        resultSet.getString(7)
                );
                return Optional.of(periodical);
            }
            return Optional.empty();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<Periodical> findByName(String name){
        try (final Connection conn = ConnectionPool.getInstance().retrieveConnection()){
            final PreparedStatement preparedStatement = conn.prepareStatement(GET_PERIODICAL_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Periodical periodical = new Periodical(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4).toLocalDate(),
                        PeriodicalType.findById(resultSet.getInt(5)),
                        resultSet.getBigDecimal(6),
                        resultSet.getString(7)
                );
                return Optional.of(periodical);
            }
            return Optional.empty();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return Optional.empty();
        }
    }
}