package lk.ijse.chat_Application.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public AnchorPane loginFormContext;

    public void btnLoginOnAction() {
        ChatWindowController.userName = txtUserName.getText();
        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/Chat_Window.fxml"))));
            stage.setResizable(false);
            stage.setTitle("Chat Room");
            stage.show();
            stage.setMaximized(false);
            txtUserName.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void HereWeGo(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            btnLoginOnAction();
        }
    }
}
