package pl.dsdev.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    public static boolean closed = false;

    public void makeConnection(String ipAdress, int port){
        try {
            socket = new Socket(ipAdress, port);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ServerConnection.closed=false;
            System.out.println("Connection established on port "+port);
        } catch (IOException e) {
            System.out.println("Wrong ipAdress or port");
            e.printStackTrace();
        }
    }

    public void sendMessage(String msg){
        out.println(msg);
        out.flush();
    }

    public void disconnect(){
        out.println("/quit");
        out.flush();
    }

    public void disposeResources(){
        try {
            ServerConnection.closed=true;

            out.close();
            socket.close();
            out = null;
            socket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendName(String name){
        if (name!=null) {
            out.println(name);
            out.flush();
        }
        else{
            System.out.println("Name is null");
        }
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getWelcomeMsg(){
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isConnected(){
        return socket!=null;
    }

    public PrintWriter getOut() {
        return out;
    }

    public BufferedReader getIn() {
        return in;
    }
}
