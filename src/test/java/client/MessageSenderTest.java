package client;

import client.MessageSender;
import log.Logger;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

public class MessageSenderTest {

    private MessageSender messageSender;

    @Test
    public void givenMessage_whenSendMessage_thenReturnTrue() {
        PrintWriter outcome = mock(PrintWriter.class);
        Logger logger = mock(Logger.class);

        messageSender = new MessageSender(outcome);
        messageSender.setLogger(logger);

        boolean actual = messageSender.sendMessage("Hello");

        verify(outcome, only()).println(anyString());
        verify(logger, only()).log(any(), any(), anyBoolean());
        assertTrue(actual);
    }

    @Test
    public void givenExit_whenSendMessage_thenReturnFalse() {
        PrintWriter outcome = mock(PrintWriter.class);
        Logger logger = mock(Logger.class);

        messageSender = new MessageSender(outcome);
        messageSender.setLogger(logger);

        boolean actual = messageSender.sendMessage("/exit");

        verify(outcome, only()).println(anyString());
        verify(logger, only()).log(any(), any(), anyBoolean());
        assertFalse(actual);
    }
}
