package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.*;

import java.util.ArrayList;

public class Main extends Application {
    private static ArrayList<Scene> scenes = new ArrayList<>();
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // creating a list of references to controllers and scenes, this allows the switching of scenes with method SwitchScene()
        scenes.add(new Scene((Parent) FXMLLoader.load(getClass().getClassLoader().getResource("mainmenu.fxml")), 700,1000));
        scenes.add(new Scene((Parent) FXMLLoader.load(getClass().getClassLoader().getResource("dashboard.fxml")), 700,1000));
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Krediteringssystem");
        primaryStage.setScene(scenes.get(0));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void SwitchScene(int SceneId) {
        System.out.println("Switching Scene to: "+SceneId);
        primaryStage.setScene(scenes.get(SceneId));
        primaryStage.show();
    }
    public void makeNewProgram(String programName) {
        throw new UnsupportedOperationException();
    }
    public void editProgram(int programId) {
        throw new UnsupportedOperationException();
    }
    public void acceptProgram(int programId) {
        throw new UnsupportedOperationException();
    }
    public void deleteProgram(int programId) {
        throw new UnsupportedOperationException();
    }
    public void sendProgramToReview(int programId) {
        throw new UnsupportedOperationException();
    }
    public void acceptProgramReview(int programId) {
        throw new UnsupportedOperationException();
    }
    public void denyProgramReview(int programId) {
        throw new UnsupportedOperationException();
    }
    public void createCategory(String categoryName) {
        throw new UnsupportedOperationException();
    }
    public void editCategory(int categoryId) {
        throw new UnsupportedOperationException();
    }
    public void deleteCategory(int categoryId) {
        throw new UnsupportedOperationException();
    }
    public void createPerson(String name,String desc) {
        throw new UnsupportedOperationException();
    }
    public void deletePerson(int personId) {
        throw new UnsupportedOperationException();
    }
    public void updatePerson(int personId,String name,String desc) {
        throw new UnsupportedOperationException();
    }
    public void getCurrentRelevantPrograms() {
        throw new UnsupportedOperationException();
    }
}
