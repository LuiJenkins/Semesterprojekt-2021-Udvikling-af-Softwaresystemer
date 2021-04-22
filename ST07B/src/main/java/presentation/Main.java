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
        ApplicationFacade.makeNewCategory(1,"Vært");
        ApplicationFacade.makeNewCategory(1,"Fotograf");

        ApplicationFacade.makeNewProgram(2,"Et lorte program");
        ApplicationFacade.makeNewCategory(2,"Skuespiller");
        ApplicationFacade.makeNewCategory(2,"lyd");
        ApplicationFacade.makeNewCategory(2,"hvem der spurgte");

        ApplicationFacade.addPersonToCategory(1,1,1);
        ApplicationFacade.addPersonToCategory(1,1,2);
        ApplicationFacade.addPersonToCategory(1,2,3);
        ApplicationFacade.addPersonToCategory(2,2,3);
        ApplicationFacade.addPersonToCategory(1,2,4);
        ApplicationFacade.addPersonToCategory(1,2,5);
        ApplicationFacade.addPersonToCategory(1,2,6);
        ApplicationFacade.addPersonToCategory(2,1,7);
        ApplicationFacade.addPersonToCategory(2,2,8);
        ApplicationFacade.addPersonToCategory(2,2,9);
        ApplicationFacade.addPersonToCategory(2,2,10);


        /////
        mainmenuController.suggestions.setItems(getAllPrograms());
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


    public static ObservableList<Category> getAllCategoryForProgram(Program selectedProgram) {
        return FXCollections.observableArrayList(ApplicationFacade.getCategoriesFromProgram(selectedProgram));
    }
    public static ObservableList<Program> getAllPrograms() {
        return FXCollections.observableArrayList(ApplicationFacade.getCurrentPrograms());
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
