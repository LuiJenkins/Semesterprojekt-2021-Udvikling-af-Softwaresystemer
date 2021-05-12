package logic;
/*
Facade that acts as interface between the GUI layer and the domain layer of the application.

 */
import logic.nextGenPersistance.PersistanceFacade;
import java.util.ArrayList;
import static logic.LoginHandler.*;
import database.PersistanceHandler;

public class ApplicationFacade {

    public static void UploadToDB() {
        PersistanceFacade.UploadProgramsToDB(CreditsHandler.getAllCreditsFromLocal());
        PersistanceFacade.UploadPersonsToDB(CreditsHandler.getPersonsFromPersonDB());
    }
    public static void DownloadFromDB() {PersistanceFacade.DownloadProgramsFromDB();}

    public static void makeNewProgram(String Titel) {
        if (currentUser.isAllowed(1)) {
            CreditsHandler.makeNewCredit(LoginHandler.currentUser.getProducerID(), Titel);
        }
    }
    public static void makeNewProgram(int producerid, String Titel) {
        CreditsHandler.makeNewCredit(producerid, Titel);
    }

    public static void deleteProgram(Program p){
        if (p.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
            CreditsHandler.deleteCredit(p);
            PersistanceFacade.programMapper.removeFromDB(p.getProgramID());
        }
    }

    public static void denyProgram(Program p){
        if (LoginHandler.currentUser.isAllowed(2)) {
            System.out.println("Deny program");
            p.getApproved().deny();
            p.modified = true;
        }
    }
    public static void acceptProgram(Program p){
        if (LoginHandler.currentUser.isAllowed(2)) {
            System.out.println("Accept program");
            p.getApproved().approve();
            p.modified = true;
        }
    }


    public static void sendCreditToReview(Program p){
        if (p.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
            System.out.println("Sending program to review"); // ## test
            p.getApproved().setStatus(1);
            p.modified = true;
        }
    }

    public static void makeNewCategory(Program p,String new_cat){
        if (p.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
            p.createCategory(new_cat);
        }
    }

    public static void deleteCategory(Category selectedCategory, Program selectedProgram) {
        if (selectedProgram.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
            selectedProgram.deleteCategory(selectedCategory);
            PersistanceFacade.categoryMapper.removeFromDB(selectedCategory.getId());
        }
    }

    public static void makeNewPerson(String name, String desc){
        CreditsHandler.makeNewPerson(name,desc);
    }

    public static void deletePerson(Person person) {
        PersistanceFacade.personMapper.removeFromDB(person.getId());
        CreditsHandler.deletePerson(person);
    }

    public static ArrayList<Program> getCurrentPrograms(){
        return CreditsHandler.getAllCreditsFromLocal();
    }
    public static ArrayList<Program> getCurrentProgramsICanEdit(){
        ArrayList<Program> pcicanedit = new ArrayList<>();
        for (Program p : CreditsHandler.getAllCreditsFromLocal()) {
            if (p.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
                pcicanedit.add(p);
            }
        }
        return pcicanedit;
    }

    public static Program getCurrentProgram(int id) {
        return CreditsHandler.getSpecificCredit(id);
    }


    public static void addPersonToCategory(int programid, int categoryid, int personid) {
        Program p = getCurrentProgram(programid);
        Category c = p.getCategory(categoryid);
        Person pe = CreditsHandler.getSpecificPerson(personid);
        c.addPersonToCategory(pe);
    }
    public static void addPersonToCategory(Category category,Person person) {
        category.addPersonToCategory(person);
    }
    public static void removePersonFromCategory(Category category,Person person) {
        category.removePersonFromCategory(person);
        PersistanceFacade.creditsMapper.removeCategoryPersonFromDB(person.getId(),category.getId());
    }

    public static ArrayList<Category> getCategoriesFromProgram(Program program) {
        return program.getAllCategory();
    }

    public static ArrayList<Person> getPersonsFromPersonDB() {
        return CreditsHandler.getPersonsFromPersonDB();
    }

    public static void addPersonToDB(String name,String desc) {
        CreditsHandler.makeNewPerson(name,desc);
    }

    public static void logUserOut() {
        System.out.println("LogOut Event");
        PersistanceFacade.TestUserFromDB("","");
 //       LoginHandler.loginToAccount("","");
    }

    public static void logUserIn(String username,String password) {
        System.out.println("LogIn Event");
        PersistanceFacade.TestUserFromDB(username,password);
//        LoginHandler.loginToAccount(username,password);
    }

    public static String loggedInRoleString() {
        return LoginHandler.getUserRoleText();
    }

    public static ArrayList<Program> searchProgram(String text) {
        return CreditsHandler.searchProgram(text);
    }

    public static ArrayList<Person> searchPerson(String text) {
        return CreditsHandler.searchPerson(text);
    }

    public static String crossReferencePersonFromCredits(Person selectedPerson) {
        return CreditsHandler.crossReferencePersonFromCredits(selectedPerson);
    }

    public static void initDB() {
        PersistanceHandler.initDB();
    }
}
