package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.model.periodicals.Periodical;
import by.epam.jwd.finalproj.model.periodicals.PeriodicalType;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class PeriodicalDaoTest {

    @Test
    public void findByName() {
        PeriodicalDao periodicalDao = new PeriodicalDao();
        Optional<Periodical> user = periodicalDao.findByName("Iron Man #7");
        assertTrue(user.isPresent());
    }

    @Test
    public void update() {
        PeriodicalDao periodicalDao = new PeriodicalDao();
        Periodical periodical = new Periodical(3,
                "Iron Man №8",
                "Christopher Cantwell",
                LocalDate.parse("2029-06-18"),
                PeriodicalType.COMICS,
                new BigDecimal("23.34"),
                "NY Publishment");
        Optional<Periodical> updatedPeriodical = periodicalDao.update(periodical);
        assertNotEquals(updatedPeriodical, Optional.empty());
    }
}