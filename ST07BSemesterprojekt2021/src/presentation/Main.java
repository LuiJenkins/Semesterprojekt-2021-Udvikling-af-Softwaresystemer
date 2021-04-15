package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/presentation/mainmenu.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root,700, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
