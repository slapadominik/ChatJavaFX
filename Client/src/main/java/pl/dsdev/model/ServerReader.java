package pl.dsdev.model;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerReader implements Runnable{

    private BufferedReader in;
    private TextArea chatArea;
    private String userName;
    private ServerConnection serverConnection;


    public ServerReader(ServerConnection serverConnection, TextArea chatAream, String userName){
        this.serverConnection=serverConnection;
        this.in=serverConnection.getIn();
        this.chatArea=chatAream;
        this.userName=userName;
    }

    public void run() {
        String line = null;
        try {
            while ((line=in.readLine())!=null && !ServerConnection.closed){
                if (userName!=null && line.startsWith("Bye "+userName+"!")){
                    ServerConnection.closed=true;
                    break;
                }
                if (line.contains("left the chat room.")){
                    removeName(line);
                }
                if (line.startsWith("***SERVER: ")){
                    addName(line);
                }
                if (chatArea!=null){
                    chatArea.appendText(line+" \n");
                }

            }
            serverConnection.getIn().close();
            serverConnection.getOut().close();
            serverConnection.getSocket().close();
            serverConnection.setIn(null);
            serverConnection.setOut(null);
            serverConnection.setSocket(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeName(final String line){
        Platform.runLater(new Runnable() {
            public void run() {
                String[] splits = line.split(" ");
                String name = splits[0];
                serverConnection.getNames().remove(name);
            }
        });
    }

    public void addName(final String line){
        Platform.runLater(new Runnable() {
            public void run() {
                String[] splits = line.split(" ");
                String name = splits[1];
                serverConnection.getNames().add(name);
            }
        });
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public TextArea getChatArea() {
        return chatArea;
    }

    public void setChatArea(TextArea chatArea) {
        this.chatArea = chatArea;
    }

}
