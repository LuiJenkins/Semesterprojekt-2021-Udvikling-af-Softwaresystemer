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
        /////
        ApplicationFacade.initDB();
        ApplicationFacade.DownloadFromDB();
        mainmenuController.start();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void SwitchScene(int SceneId) {
        System.out.println("Switching Scene to: "+SceneId);
        primaryStage.setScene(scenes.get(SceneId));
        primaryStage.show();
        if (SceneId == 0) {mainmenuController.start();}
        else if (SceneId == 1) {dashboardController.start();}
    }
    public static void displayCredits(Program program) {
        mainmenuController.credits.setText(program.toText());
    }

    public static void displayPersonInfo(Person selectedPerson) {
        mainmenuController.credits.setText(ApplicationFacade.crossReferencePersonFromCredits(selectedPerson));
    }


    public static ObservableList<Category> getAllCategoryForProgram(Program selectedProgram) {
        return FXCollections.observableArrayList(ApplicationFacade.getCategoriesFromProgram(selectedProgram));
    }
    public static ObservableList<Object> getAllPrograms() {
        ArrayList<Program> ap = ApplicationFacade.getCurrentPrograms();
        return FXCollections.observableArrayList(ap.toArray());
    }
    public static ObservableList<Object> getAllPersons() {
        ArrayList<Person> ap = ApplicationFacade.getPersonsFromPersonDB();
        return FXCollections.observableArrayList(ap.toArray());
    }

    public static ObservableList<Program> getAllProgramsICanEdit() {
        return FXCollections.observableArrayList(ApplicationFacade.getCurrentProgramsICanEdit());
    }
    public static ObservableList<Person> getAllPersonsFromPersonDB() {
        return FXCollections.observableArrayList(ApplicationFacade.getPersonsFromPersonDB());
    }
    public static ObservableList<Person> getAllPersonsNotInCategory(Category category) {
        ArrayList<Person> personindb = new ArrayList<>();
        personindb.addAll(ApplicationFacade.getPersonsFromPersonDB());
        for (Person p : category.getPersonsFromCategory()) {
            personindb.remove(p);
        }
        return FXCollections.observableArrayList(personindb);
    }
    public static ObservableList<Person> getAllPersonsInCategory(Category category) {
        return FXCollections.observableArrayList(category.getPersonsFromCategory());
    }

    public static ObservableList<Object> searchProgram(String text) {
        ArrayList<Program> ap = ApplicationFacade.searchProgram(text);
        return FXCollections.observableArrayList(ap.toArray());
    }

    public static ObservableList<Object> searchPerson(String text) {
        ArrayList<Person> ap = ApplicationFacade.searchPerson(text);
        return FXCollections.observableArrayList(ap.toArray());
    }

    public void makeNewProgram(String programName) {
        ApplicationFacade.makeNewProgram(programName);
    }
}
