package client;

import log.Logger;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RegisterServiceTest {

    private RegisterService registerService;

    @Test
    public void testRegister() throws IOException {
        BufferedReader income = mock(BufferedReader.class);
        when(income.readLine()).thenReturn("What is your name?", "Welcome");
        PrintWriter outcome = mock(PrintWriter.class);
        Logger logger = mock(Logger.class);

        // имитируем ввод для сканера
        String input = "Oleg";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        registerService = new RegisterService(income, outcome);
        registerService.setLogger(logger);
        boolean actual = registerService.register();

        verify(income, times(2)).readLine();
        verify(outcome, only()).println(anyString());
        verify(logger, only()).log(anyString(), any(), anyBoolean());
        assertTrue(actual);
    }
}
