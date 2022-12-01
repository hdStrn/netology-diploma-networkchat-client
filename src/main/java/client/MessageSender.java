package client;

import log.LogType;
import log.Logger;

import java.io.PrintWriter;
import java.util.Scanner;

public class MessageSender implements Runnable {

    private PrintWriter outcome;
    private final Scanner scanner = new Scanner(System.in);
    private Logger logger = Logger.getInstance();

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public MessageSender(PrintWriter outcome) {
        this.outcome = outcome;
    }

    @Override
    public void run() {
        String message;
        while (true) {
            message = scanner.nextLine();
            if (!sendMessage(message)) break;
        }
        System.exit(0);
    }

    public boolean sendMessage(String message) {
        outcome.println(message);
        if ("/exit".equalsIgnoreCase(message)) {
            String leave = "* You are leaving chat *";
            logger.log(leave, LogType.INFO, false);
            return false;
        } else {
            String yourMsg = "You say: " + message;
            logger.log(yourMsg, LogType.MESSAGE, false);
        }
        return true;
    }
}
