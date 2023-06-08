import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass()
                .getResource("lk/ijse/chat_Application/views/loginForm.fxml"))));
        primaryStage.setTitle("PLAY TECH CHATTER");
        primaryStage.setResizable(false);
        primaryStage.show();











    }
}
