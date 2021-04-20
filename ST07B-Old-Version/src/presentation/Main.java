package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.*;

import java.util.ArrayList;

public class Main extends Application {
    private ArrayList<Parent> roots = new ArrayList<>();
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        roots.add(FXMLLoader.load(getClass().getResource("/presentation/mainmenu.fxml")));
        roots.add(FXMLLoader.load(getClass().getResource("/presentation/dashboard.fxml")));
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(roots.get(0),700, 1000));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void SwitchScene(int SceneId) {
        primaryStage.setScene(new Scene(roots.get(0),700, 1000));
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
