package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class PeriodicalDaoTest {

    @Test
    public void findByName() {
        PeriodicalDao periodicalDao = new PeriodicalDao();
        Optional<List<Periodical>> user = periodicalDao.findAll();
        assertTrue(user.isPresent());
    }
}