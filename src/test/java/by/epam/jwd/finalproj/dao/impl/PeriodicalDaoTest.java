package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.model.periodicals.Periodical;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PeriodicalDaoTest {

    @Test
    public void findByName() {
        PeriodicalDaoImpl periodicalDao = new PeriodicalDaoImpl();
        Optional<List<Periodical>> user = periodicalDao.findAll();
        assertTrue(user.isPresent());
    }
}