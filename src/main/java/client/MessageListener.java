package client;

import log.LogType;
import log.Logger;

import java.io.BufferedReader;
import java.io.IOException;

public class MessageListener implements Runnable {

    private BufferedReader income;
    private Logger logger = Logger.getInstance();

    public MessageListener(BufferedReader income) {
        this.income = income;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void run() {
        while (readMessage());
        try {
            income.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean readMessage() {
        try {
            String message = income.readLine();
            if (message != null) {
                System.out.println(message);
                logger.log(message, LogType.MESSAGE, true);
            }
        } catch (IOException e) {
            String troubles = "* Troubles with connection *";
            System.out.println(troubles);
            logger.log(troubles, LogType.ERROR, false);
            return false;
        }
        return true;
    }
}
