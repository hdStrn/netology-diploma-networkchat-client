package log;

import log.LogType;
import log.Logger;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class LoggerTest {

    private Logger logger;

    @Test
    public void givenMessage_whenLog_thenReturnTrue() {
        String msg = "Hello all";

        logger = Logger.getInstance();

        boolean actual = logger.log(msg, LogType.MESSAGE, false);

        assertTrue(actual);
    }
}
