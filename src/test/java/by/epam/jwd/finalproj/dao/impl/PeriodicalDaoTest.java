package by.epam.jwd.finalproj.dao.impl;

import by.epam.jwd.finalproj.model.User;
import by.epam.jwd.finalproj.model.periodicals.Periodical;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class PeriodicalDaoTest {

    @Test
    public void findByName() {
        PeriodicalDao periodicalDao = new PeriodicalDao();
        Optional<Periodical> user = periodicalDao.findByName("Iron Man #7");
        assertTrue(user.isPresent());
    }
}