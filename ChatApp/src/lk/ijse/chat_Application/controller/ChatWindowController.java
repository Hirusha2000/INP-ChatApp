package lk.ijse.chat_Application.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatWindowController {
    public static String userName;
    public Label txtClientName;
    public TextField textMessage;
    public TextArea textArea;

    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public void initialize(){
        try {
            socket = new Socket("localhost", 6005);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            sendName();
            listenForMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendName() {
        try {
            while (socket.isConnected()) {
                String name= userName;
                txtClientName.setText(name);
                dataOutputStream.writeUTF(name);
                dataOutputStream.flush();
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOnAction() {
        try {
            while (socket.isConnected()) {
                String messageToSend = textMessage.getText();
                dataOutputStream.writeUTF(txtClientName.getText() + " : " + messageToSend);
                textArea.appendText("\nme : " + messageToSend);
                textMessage.clear();
                dataOutputStream.flush();
                break;
            }
        } catch (IOException e) {
            closeEverything(socket, dataOutputStream, dataInputStream);
        }
    }
    public void HereWeGoo(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendOnAction();
        }
    }


    public void listenForMessage() {
        new Thread(() -> {
            String message;
            while (socket.isConnected()) {
                try {
                    message = dataInputStream.readUTF();
                    textArea.appendText("\n" + message);
                } catch (IOException e) {
                    closeEverything(socket, dataOutputStream,dataInputStream);
                }
            }
        }).start();
    }

    private void closeEverything(Socket socket, DataOutputStream dataOutputStream, DataInputStream dataInputStream) {
        try {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (dataInputStream != null) {
                dataInputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
