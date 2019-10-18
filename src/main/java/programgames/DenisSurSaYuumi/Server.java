package programgames.DenisSurSaYuumi;

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
    public static void main(String[] args) {

        if (args.length == 1) {

            try {
                initServer(args[0]);
            } catch (Exception e) {
                System.err.println("An error as occured while initializing the server");
                e.printStackTrace();
                System.exit(-1);
            }
        } else {
            System.out.println("Please provide the ip of the server as first argument");
            System.out.println("Please provide the port of the server as second argument");
            System.exit(-1);
        }

        readClient();
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

                if (inputLine.equals("R")) {

                        robot.keyPress(KeyEvent.VK_R);
                        robot.keyRelease(KeyEvent.VK_R);
                }
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

    private static void initServer(String port) throws IOException {
        serverSocket = new ServerSocket(Integer.parseInt(port));
        clientSocket = serverSocket.accept();
        bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
}

