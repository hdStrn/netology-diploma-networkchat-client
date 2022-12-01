package client;

import log.LogType;
import log.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RegisterService {

    private BufferedReader income;
    private PrintWriter outcome;
    private Logger logger = Logger.getInstance();

    public RegisterService(BufferedReader income, PrintWriter outcome) {
        this.income = income;
        this.outcome = outcome;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public boolean register() {
        try {
            String msgFromServer = income.readLine();
            System.out.println(msgFromServer);
            Scanner scanner = new Scanner(System.in);
            String name;
            do {
                name = scanner.nextLine();
                outcome.println(name);
                msgFromServer = income.readLine();
                System.out.println(msgFromServer);
            } while (msgFromServer.contains("please use another one"));
            String join = "* You have joined chat *";
            logger.log(join, LogType.INFO, false);
            return true;
        } catch (IOException e) {
            String trouble = "* Cannot connect to server *";
            System.out.println(trouble);
            logger.log(trouble, LogType.ERROR, false);
            return false;
        }
    }
}
