package by.epam.jwd.finalproj.command.action.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubscribeCommandTest {

    private final Logger logger = LogManager.getLogger(SubscribeCommandTest.class);

    @Test
    public void createPaymentId() {
        String testId = SubscribeCommand.INSTANCE.createPaymentId();
        assertNotEquals(testId, null);
        assertNotEquals(testId, "");
    }
}