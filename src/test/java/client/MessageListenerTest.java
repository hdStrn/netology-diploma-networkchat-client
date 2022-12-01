package client;

import client.MessageListener;
import log.Logger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageListenerTest {

    private MessageListener messageListener;

    @Test
    public void testReadingMessage() throws IOException {
        BufferedReader income = mock(BufferedReader.class);
        when(income.readLine()).thenReturn("Hello");

        Logger logger = mock(Logger.class);

        messageListener = new MessageListener(income);
        messageListener.setLogger(logger);

        boolean actual = messageListener.readMessage();

        verify(income, only()).readLine();
        verify(logger, only()).log(any(), any(), anyBoolean());
        assertTrue(actual);
    }
}
