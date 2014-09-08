package com.jiesoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jiesoon.message.store.MemoryStore;

public class IMAPServer {
    private final static int PORT = 143;
    private final static String HOST = "localhost";
    private Logger mLogger = Logger.getLogger("IMAPServer");
    private ServerSocket mServerSocket;
    private int mState = State.NOT_AUTHENTICATED;
    private User mUser;
    private MemoryStore mStore;

    public IMAPServer() {
        mLogger.setLevel(Level.ALL);
        try {
            mServerSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initUserDB() {
        mLogger.log(Level.INFO, "init user db");
        mUser = new User();
        mUser.mName = "jiesoon";
        mUser.mPassword = "www.jiesoon.com";
    }
    
    private void initMessageStore(){
        mStore = new MemoryStore();
        mStore.init();
    }

    private void greeting(Socket socket) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("* OK IMAP4rev1 server ready");
                writer.newLine();
                writer.flush();

                mState = State.NOT_AUTHENTICATED;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    /**
     * Login this IMAP server C: a001 LOGIN SMITH SESAME S: a001 OK LOGIN
     * completed
     * 
     * @param socket
     */
    private void handleLogin(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                mLogger.log(Level.INFO, ">> " + request);
                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 4) {
                        String tag = fields[0];
                        String command = fields[1];
                        String name = fields[2];
                        String password = fields[3];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkLogin(command)) {
                            // BAD
                        }

                        if (mUser.mName.equals(name) && mUser.mPassword.equals(password)) {
                            // OK
                            mState = State.AUTHENTICATED;
                            mLogger.log(Level.INFO, "mState: " + mState);

                            resp = tag + " " + "OK " + "LOGIN complete";
                        } else {
                            // NO
                            resp = tag + " " + "NO " + "login failure: user name or password rejected";

                        }

                        mLogger.log(Level.INFO, "<< " + resp);
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleLogout(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 2) {
                        String tag = fields[0];
                        String command = fields[1];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkLogin(command)) {
                            // BAD
                        }

                        mState = State.NOT_AUTHENTICATED;

                        resp = "* BYE IMAP4rev1 Server logging out";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                        resp = tag + " OK LOGOUT completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                        socket.close();
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    
    private void handleCapability(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 2) {
                        String tag = fields[0];
                        String command = fields[1];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkCapability(command)) {
                            // BAD
                        }

                        resp = "* CAPABILITY IMAP4rev1 STARTTLS AUTH=GSSAPI LOGINDISABLED";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                        resp = tag + " OK CAPABILITY completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private void handleNoop(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 2) {
                        String tag = fields[0];
                        String command = fields[1];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkNoop(command)) {
                            // BAD
                        }

                        // If no new status
                        resp = tag + " OK NOOP completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                        // If a new updated status.
                        writer.write("* 22 EXPUNGE");
                        writer.newLine();
                        writer.write("* 23 EXISTS");
                        writer.newLine();
                        writer.write("* 3 RECENT");
                        writer.newLine();
                        writer.write("* 14 FETCH (FLAGS (\\Seen \\Delete))");
                        writer.newLine();
                        resp = tag + " OK NOOP completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    private void handleStartTls(Socket socket, String request) {
        // TODO add TLS supported feature.
        // for test, we only implement plain authentication
    }

    private void handleAuthenticate(Socket socket, String request) {
        // TODO add Authentication feature
        // for test, we only implement plain authetication.
    }

    private void handleSelect(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 3) {
                        String tag = fields[0];
                        String command = fields[1];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkSelect(command)) {
                            // BAD
                        }

                        writer.write("* 172 EXISTS");
                        writer.newLine();
                        writer.write("* 1 RECENT");
                        writer.newLine();
                        writer.write("* OK [UNSEEN 12] Message 12 is first unseen");
                        writer.newLine();
                        writer.write("* OK [UIDVALIDITY 3857529045] UIDs valid");
                        writer.newLine();
                        writer.write("* FLAGS (\\Answered \\Flagged \\Deleted \\Seen \\Draft)");
                        writer.newLine();
                        writer.write("* OK [PERMANENTFLAGS (\\Deleted \\Seen \\*)] Limited");
                        writer.newLine();
                        resp = tag + " OK [READ-WRITE] SELECT completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleExamine(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 3) {
                        String tag = fields[0];
                        String command = fields[1];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkSelect(command)) {
                            // BAD
                        }

                        // TODO No change to FLAGS, No change of \Recent flag
                        writer.write("* 172 EXISTS");
                        writer.newLine();
                        writer.write("* 1 RECENT");
                        writer.newLine();
                        writer.write("* OK [UNSEEN 12] Message 12 is first unseen");
                        writer.newLine();
                        writer.write("* OK [UIDVALIDITY 3857529045] UIDs valid");
                        writer.newLine();
                        writer.write("* FLAGS (\\Answered \\Flagged \\Deleted \\Seen \\Draft)");
                        writer.newLine();
                        writer.write("* OK [PERMANENTFLAGS (\\Deleted \\Seen \\*)] Limited");
                        writer.newLine();
                        resp = tag + " OK [READ-ONLY] SELECT completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCreate(Socket socket, String request) {
        if (socket != null) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                String line = request;
                if (line != null) {
                    String[] fields = line.split(" ");
                    if (fields != null && fields.length == 3) {
                        String tag = fields[0];
                        String command = fields[1];
                        String mailbox = fields[2];

                        String resp = "";
                        if (!checkTag(tag)) {
                            // BAD
                            resp = tag + " " + "BAD " + "command unknown or arguments invalid";
                        }

                        if (!checkCreate(command)) {
                            // BAD
                        }

                        if (mailbox.equals("INBOX")) {
                            // TODO
                        }

                        // hierarchy separator
                        if (mailbox.contains("/")) {
                            // TODO
                        }

                        // TODO Create a mailbox

                        resp = tag + " OK CREATE completed";
                        writer.write(resp);
                        writer.newLine();
                        writer.flush();

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private boolean checkLogin(String command) {
        return "LOGIN".equalsIgnoreCase(command);
    }

    private boolean checkCapability(String command){
       return "CAPABILITY".equalsIgnoreCase(command);
    }
    
    private boolean checkNoop(String command){
        return "NOOP".equalsIgnoreCase(command);
    }
    
    private boolean checkSelect(String command){
        return "SELECT".equalsIgnoreCase(command);
    }
    
    private boolean checkCreate(String command){
        return "CREATE".equalsIgnoreCase(command);
    }
    /**
     * Check if tag's validity
     * 
     * @param tag
     * @return
     */
    private boolean checkTag(String tag) {
        boolean b = false;
        b = tag != null && !tag.isEmpty();
        return b;
    }

    public void start() {
        mLogger.log(Level.INFO, "IMAP server started");

        initUserDB();

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
                                greeting(clientSocket);
                                while (true) {
                                    String line = reader.readLine();
                                    if (line == null) {
                                        mLogger.log(Level.WARNING, "EOL");
                                        break;
                                    }

                                    String[] fields = line.split(" ");
                                    if (fields != null && fields.length >= 2) {
                                        String tag = fields[0];
                                        String command = fields[1];

                                        if ("LOGIN".equalsIgnoreCase(command)) {
                                            handleLogin(clientSocket, line);
                                        } else if ("LOGOUT".equalsIgnoreCase(command)) {
                                            handleLogout(clientSocket, line);
                                        }
                                    }
                                    System.out.println("READ: " + line);
                                }
                            } catch (SocketException e){
                                e.printStackTrace();
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
