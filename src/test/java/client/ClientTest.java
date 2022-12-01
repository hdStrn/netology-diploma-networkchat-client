package client;

import client.Client;
import client.RegisterService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientTest {

    private Client client;

    @Test
    public void givenNotAvailableServer_whenConnectToServer_thenReturnFalse() throws IOException {
        RegisterService registerService = mock(RegisterService.class);

        client = new Client();
        client.setRegisterService(registerService);

        verify(registerService, never()).register();
        assertFalse(client.connectToServer());
    }
}
