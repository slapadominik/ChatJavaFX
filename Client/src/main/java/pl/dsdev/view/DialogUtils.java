package pl.dsdev.view;

import javafx.scene.control.Alert;

public class DialogUtils {

    public static void dialogAboutApplication(){
        Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
        informationAlert.setTitle("About the application");
        informationAlert.setHeaderText("Chat client application based on TCP/IP protocol, version 1.0");
        informationAlert.setContentText("Author: Dominik Slapa | Released : 09.2017");
        informationAlert.showAndWait();
    }
}
