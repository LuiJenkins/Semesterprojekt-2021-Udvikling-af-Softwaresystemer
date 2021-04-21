package presentation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.ApplicationFacade;
import logic.Person;
import logic.Program;
import logic.*;

import java.util.ArrayList;

public class Main extends Application {
    private static ArrayList<FXMLLoader> roots = new ArrayList<>();
    private static ArrayList<Scene> scenes = new ArrayList<>();
    private static Stage primaryStage;
    private static DashboardController dashboardController;
    private static MainmenuController mainmenuController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // creating a list of references to controllers and scenes, this allows the switching of scenes with method SwitchScene()
        roots.add((FXMLLoader) new FXMLLoader(getClass().getClassLoader().getResource("mainmenu.fxml")));
        scenes.add(new Scene((Parent) roots.get(0).load(),700,1000));
        roots.add((FXMLLoader) new FXMLLoader(getClass().getClassLoader().getResource("dashboard.fxml")));
        scenes.add(new Scene((Parent) roots.get(1).load(),700,1000));


        mainmenuController = roots.get(0).getController();
        dashboardController = roots.get(1).getController();

        this.primaryStage = primaryStage;

        primaryStage.setTitle("Krediteringssystem");
        primaryStage.setScene(scenes.get(0));
        primaryStage.show();


        ///// shows demo credit
        ApplicationFacade.makeNewPerson("John Smith","");
        ApplicationFacade.makeNewPerson("Adam Sandal","");
        ApplicationFacade.makeNewPerson("Din Mor","");
        ApplicationFacade.makeNewPerson("Din Far","");
        ApplicationFacade.makeNewPerson("Din Søster","");
        ApplicationFacade.makeNewPerson("Din Bror","");
        ApplicationFacade.makeNewPerson("John Doe","");
        ApplicationFacade.makeNewPerson("John Wick","");
        ApplicationFacade.makeNewPerson("Egon Olsen","");
        ApplicationFacade.makeNewPerson("Onkel Anders","");


        ApplicationFacade.makeNewProgram(1,"Vores Test Program");
        Program pc = ApplicationFacade.getCurrentProgram(1);
        pc.createCategory("Vært");
        pc.createCategory("Skuespiller");

        pc.getCategory(1).addPersonToCategory(new Person(1,"John Smith"));
        pc.getCategory(1).addPersonToCategory(new Person(2,"Adam Sandal"));
        pc.getCategory(2).addPersonToCategory(new Person(1,"Din Mor"));
        pc.getCategory(2).addPersonToCategory(new Person(2,"Din Far"));
        pc.getCategory(2).addPersonToCategory(new Person(3,"Din Søster"));
        pc.getCategory(2).addPersonToCategory(new Person(4,"Din Bror"));

        ApplicationFacade.makeNewProgram(2,"Et lorte program");
        Program pc2 = ApplicationFacade.getCurrentProgram(2);
        pc2.createCategory("Vært");
        pc2.createCategory("Kamera");
        pc2.createCategory("Hvem der spurgte");

        pc2.getCategory(1).addPersonToCategory(new Person(1,"John Doe"));
        pc2.getCategory(2).addPersonToCategory(new Person(2,"John Wick"));
        pc2.getCategory(2).addPersonToCategory(new Person(1,"Egon Olsen"));
        pc2.getCategory(2).addPersonToCategory(new Person(2,"Skipper Skræk"));

        /////
        mainmenuController.suggestions.setItems(FXCollections.observableArrayList(ApplicationFacade.getCurrentPrograms()));
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void SwitchScene(int SceneId) {
        System.out.println("Switching Scene to: "+SceneId);
        primaryStage.setScene(scenes.get(SceneId));
        primaryStage.show();
    }
    public static void displayCredits(Program program) {
        mainmenuController.credits.setText(program.toText());
    }

    public void makeNewProgram(int producerid, String programName) {
        ApplicationFacade.makeNewProgram(producerid,programName);
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
