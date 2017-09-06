package pl.dsdev.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pl.dsdev.model.ServerConnection;
import pl.dsdev.model.ServerReader;


public class ChatWindowController {

    @FXML
    private TextField userInputTextField;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private ListView<String> listView;

    private ServerConnection serverConnection;

    public ChatWindowController(){
        serverConnection = new ServerConnection();
    }

    @FXML
    public void initialize(){
        chatTextArea.setEditable(false);
    }


    @FXML
    public void sendAction(){
        if (serverConnection.isConnected()){
            serverConnection.sendMessage(userInputTextField.getText().trim());
            userInputTextField.clear();
        }
        else{
            chatTextArea.appendText("You are not connected to the server!\n");
        }
    }

    @FXML
    public void connectAction(){
        if (!serverConnection.isConnected()) {
            String ipAdress = ipTextField.getText();
            String port = portTextField.getText();
            String username = usernameTextField.getText();
            int portInt = Integer.parseInt(port);
            serverConnection.makeConnection(ipAdress, portInt);
            serverConnection.sendName(username);
            chatTextArea.clear();

            serverConnection.getOnlineUsers();
            listView.setItems(serverConnection.getNames());

            chatTextArea.appendText(serverConnection.getWelcomeMsg());

            ServerReader serverReader = new ServerReader(serverConnection, chatTextArea, listView, username);
            Thread readingThread = new Thread(serverReader);
            readingThread.start();

        }
    }

    @FXML
    public void disconnectAction(){
        if (serverConnection.isConnected()) {
            serverConnection.disconnect();
            chatTextArea.appendText("***CHAT: DISCONNECTED FROM THE SERVER!***");
        }
    }

    @FXML
    public void sendMsgEnterPressed(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            sendAction();
        }

    }

    @FXML
    public void sendButtonEnterPressed(KeyEvent keyEvent){
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            sendAction();
        }
    }
}
