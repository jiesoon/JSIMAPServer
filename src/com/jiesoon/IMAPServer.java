package com.jiesoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IMAPServer {
    private final static int PORT = 143;
    private final static String HOST = "localhost";
    private Logger mLogger = Logger.getLogger("IMAPServer");
    private ServerSocket mServerSocket;

    public IMAPServer() {
        mLogger.setLevel(Level.ALL);
        try {
            mServerSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        mLogger.log(Level.INFO, "IMAP server started");

        if (mServerSocket != null) {
            while (true) {
                try {
                    final Socket clientSocket = mServerSocket.accept();
                    new Thread() {
                        public void run() {
                            try {
                                BufferedReader reader = new BufferedReader(new InputStreamReader(
                                        clientSocket.getInputStream()));
                                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                                        clientSocket.getOutputStream()));
                                while (true) {
                                    String line = reader.readLine();
                                    if (line == null) {
                                        mLogger.log(Level.WARNING, "EOL");
                                        break;
                                    }
                                    System.out.println("READ: " + line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                    }.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
