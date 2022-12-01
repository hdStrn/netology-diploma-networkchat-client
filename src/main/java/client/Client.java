package client;

import log.LogType;
import log.Logger;
import settings.Settings;

import java.io.*;
import java.net.Socket;

public class Client {

    private MessageListener messageListener;
    private MessageSender messageSender;
    private RegisterService registerService;
    private final Logger logger = Logger.getInstance();

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public void setMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public boolean connectToServer() {
        String host = Settings.getProperty("host");
        int port = Integer.parseInt(Settings.getProperty("port"));

        try {
            Socket clientSocket = new Socket(host, port);
            PrintWriter outcome = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader income = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            registerService = new RegisterService(income, outcome);
            registerService.register();

            messageListener = new MessageListener(income);
            Thread listener = new Thread(messageListener);
            listener.setDaemon(true);
            listener.start();

            messageSender = new MessageSender(outcome);
            Thread sender = new Thread(messageSender);
            sender.start();

        } catch (IOException e) {
            String troubles = "* Troubles with connection *";
            System.out.println(troubles);
            logger.log(troubles, LogType.ERROR, false);
            return false;
        }
        return true;
    }
}
