package pl.dsdev.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    public static final int MAX_CLIENTS = 10;
    public static final ClientHandler[] clients = new ClientHandler[MAX_CLIENTS];
    public static final List<String> names = new ArrayList<>();

    public void go(int port, String ipAdress){
        try{
            InetAddress ip = InetAddress.getByName(ipAdress);
            ServerSocket serverSocket = new ServerSocket(port, 50, ip);
            System.out.println("Connection established on port "+port);
            System.out.println("Waiting for clients...");

            while (true){
                Socket clientSocket = serverSocket.accept();
                for (int i=0; i<clients.length;i++){
                    if (clients[i]==null){
                        ClientHandler clientHandler = new ClientHandler(clientSocket, clients, names);
                        clients[i] = clientHandler;
                        clientHandler.start();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
