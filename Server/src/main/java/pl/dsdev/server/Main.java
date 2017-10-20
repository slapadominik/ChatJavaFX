package pl.dsdev.server;

import pl.dsdev.server.ChatServer;

public class
Main {
    public static void main(String[] args) {
        int port;
        String ipAdress = null;
        ChatServer chatServer = new ChatServer();
        if (args.length!=2){
            System.out.println("Usage: java pl.dsdev.server.Main [\"ipAdress\"][port]");
            ipAdress = "127.0.0.1";
            port = 8000;
            System.out.println("Default ip : "+ipAdress+", port: "+port);
        }
        else{
            port = Integer.parseInt(args[1]);
            ipAdress = args[0];
            System.out.println("IP Adress: "+ipAdress+", port: "+port);
        }
        chatServer.go(port, ipAdress);
    }
}
