import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {

    private Socket socket;
    private ClientHandler[] clients;
    private List<String> names;
    private PrintWriter out;

    public ClientHandler(Socket clientSocket, ClientHandler[] clientsThreads, List<String> names){
        socket = clientSocket;
        clients = clientsThreads;
        this.names=names;
    }

    @Override
    public void run() {
        ClientHandler[] threads = this.clients;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); //PrintWriter is autoflushing every time the println() method is invoked

            String myName = in.readLine();
            names.add(myName);
            for (String person: names){
                out.println(person);
            }
            out.println("/endgetusers");
            System.out.println(myName+" joined the chat room.");

            out.println("Welcome "+myName+" to the best chat room ever!\n");
            synchronized (this) {
                for (int i = 0; i < threads.length; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].out.println("***SERVER: " + myName + " entered the chat room!***\n");
                    }
                }
            }

            while (true){
                String inputLine = in.readLine();
                if (inputLine.startsWith("/quit")){
                    synchronized (this) {
                        for (int i = 0; i < threads.length; i++) {
                            if (threads[i] != null && threads[i] != this) {
                                threads[i].out.println(myName+" left the chat room.");
                            }
                        }
                    }
                    names.remove(myName);
                    break;
                }
                synchronized (this) {
                    for (int i = 0; i < threads.length; i++) {
                        if (threads[i] != null) {
                            threads[i].out.println(myName+": "+inputLine);
                        }
                    }
                }
            }
            System.out.println(myName+" is leaving the chat room.");

            out.println("Bye "+myName+"!");
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
