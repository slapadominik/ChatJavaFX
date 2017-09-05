import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private ClientHandler[] clients;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket, ClientHandler[] clientsThreads){
        socket = clientSocket;
        clients = clientsThreads;
    }

    @Override
    public void run() {
        ClientHandler[] threads = this.clients;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); //PrintWriter is autoflushing every time the println() method is invoked

            String name = in.readLine();
            System.out.println(name);
            out.println("Welcome "+name+" to the best chat room ever!\n");
            synchronized (this) {
                for (int i = 0; i < threads.length; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].out.println("***SERVER: " + name + " entered the chat room!***\n");
                    }
                }
            }

            while (true){
                String inputLine = in.readLine();
                System.out.println(inputLine);
                if (inputLine.startsWith("/quit")){
                    break;
                }
                synchronized (this) {
                    for (int i = 0; i < threads.length; i++) {
                        if (threads[i] != null) {
                            threads[i].out.println(name+": "+inputLine);
                        }
                    }
                }
            }
            System.out.println(name+" is leaving the chat room.");

            out.println("Bye "+name+"!");
            synchronized (this) {
                for (int i = 0; i < threads.length; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
