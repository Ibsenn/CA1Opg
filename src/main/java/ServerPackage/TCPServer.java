package ServerPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCPServer {

    private static boolean serverUp = true;
    static String ip = "localhost";
    static int portNum = 8080;
    Map<String, Client> clients = new HashMap();

    public static void stopServer()
    {
      serverUp = false;
    }
    
    public void AddUser(String username, Client c) {
        clients.put(username, c);
        PrintClientList();
    }

    public void RemoveUser(String username, Client c) {
        clients.remove(username, c);
        PrintClientList();
    }

    public void PrintClientList() {
        String msg = "CLIENTLIST:";

        for (String name : clients.keySet()) {
            msg += name + ",";
        }
        for (Client client : clients.values()) {
            client.send(msg);
        }
    }

    public void SendMessage(String recievers, String message, String sender) {
        String msgRes = "MSGRES:" + sender + ":" + message;
        if (recievers.isEmpty()) {
            for (Client client : clients.values()) {
                client.send(msgRes);
            }
        } else {
            String[] recieversParts = recievers.split(",");
            for (int i = 0; i < recieversParts.length; i++) {
                for (Client client : clients.values()) {
                    if (recieversParts[i].equals(client.username)) {
                        client.send(msgRes);
                    }
                }
            }
        }
    }
    

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            System.out.println("server started");
            ip = args[0];
            portNum = Integer.parseInt(args[1]);
        }
        new TCPServer().StartServer(portNum, ip);

    }

    // Erstattes med metoden ovenfor, hvis Digital Ocean skal køre koden
    // Variablerne "ip" og "portNum" skal instantieres på linje 24 og 25.
//    public static void main(String[] args) throws IOException {
//        System.out.println("server started");
//        if (args.length == 2) {
//            String ip = args[0];
//            int portNum = Integer.parseInt(args[1]);
//            new TCPServer().StartServer(portNum, ip);
//        }
//    }
    
    public void StartServer(int portNum, String ip) throws IOException {
        try {
            Log.setLogFile("logFile.txt", "ServerLog");
            ServerSocket ss;
            ss = new ServerSocket();

            ss.bind(new InetSocketAddress(ip, portNum));
            Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "Starting the Server");
            Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "Server started - listening on port: " + portNum + " bound to ip: " + ip);

            while (serverUp) {
                Socket link = ss.accept();
                Logger.getLogger(Log.LOG_NAME).log(Level.INFO, "New client connected");
                Client c = new Client(link, this);
                c.start();
            }
        ss.close();
        } finally {
            Log.closeLogger();
        }
    }
}
