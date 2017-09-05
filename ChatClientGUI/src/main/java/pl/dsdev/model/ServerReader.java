package pl.dsdev.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
