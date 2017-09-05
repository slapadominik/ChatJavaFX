import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static final int MAX_CLIENTS = 10;
    public static final ClientHandler[] clients = new ClientHandler[MAX_CLIENTS];

    public void go(int port){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Connection established on port "+port);
            System.out.println("Waiting for clients...");

            while (true){
                Socket clientSocket = serverSocket.accept();
                for (int i=0; i<clients.length;i++){
                    if (clients[i]==null){
                        ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                        clients[i] = clientHandler;
                        System.out.println("Added new client!");
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
