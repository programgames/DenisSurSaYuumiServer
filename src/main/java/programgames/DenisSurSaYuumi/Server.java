package programgames.DenisSurSaYuumi;

import org.apache.commons.cli.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static BufferedReader bufferedReader;
    private static Robot robot;
    private static CommandLine cmd;

    public static void main(String[] args) {

        System.out.println("Starting server");
        initArguments(args);

        try {
            initServer();
        } catch (Exception e) {
            System.err.println("An error as occured while initializing the server");
            e.printStackTrace();
            System.exit(-1);
        }

        readClient();
    }

    private static void initArguments(String[] args) {

        Options options = new Options();

        Option output = new Option("p", "port", true, "port of the server");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
    }

    private static void readClient() {

        String inputLine = "";
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("An error as occured while initalizing the client reader");
            e.printStackTrace();
        }

        try {

            while ((inputLine = bufferedReader.readLine()) != null) {
                executeKey(inputLine.charAt(0));
            }


        } catch (Exception e) {
            System.err.println("An error as occured while reading the client data");
            System.err.println(e.getMessage());
        }

        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("close");
    }

    private static void executeKey(char inputLine) {
        if(inputLine == 'A')
        {
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
        }

    }

    private static void initServer() throws IOException {
        serverSocket = new ServerSocket(Integer.parseInt(cmd.getOptionValue("port")));
        clientSocket = serverSocket.accept();
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}

