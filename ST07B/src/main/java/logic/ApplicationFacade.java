package logic;

import javafx.collections.ObservableList;

import java.util.ArrayList;

import static logic.LoginHandler.*;

public class ApplicationFacade {

    public static void makeNewProgram(String Titel) {
        if (currentUser.isAllowed(1)) {
            CreditsHandler.makeNewCredit(LoginHandler.currentUser.getProducerID(), Titel);
        }
    }
    public static void makeNewProgram(int producerid, String Titel) {
        CreditsHandler.makeNewCredit(producerid, Titel);
    }

    public static void deleteProgram(Program p){
        if (p.getProducerID() == LoginHandler.currentUser.getProducerID()) {
            CreditsHandler.deleteCredit(p);
        }
    }

    public static void denyProgram(int ID,int status){
        CreditsHandler.getSpecificCredit(ID).getApproved().setStatus(status);
    }

    public static void sendCreditToReview(int ID){
        throw new UnsupportedOperationException();
    }

    public static void makeNewCategory(Program p,String new_cat){
        if (p.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
            p.createCategory(new_cat);
        }
    }

    public static void deleteCategory(Category selectedCategory, Program selectedProgram) {
        if (selectedProgram.getProducerID() == LoginHandler.currentUser.getProducerID() || currentUser.isAllowed(2)) {
            selectedProgram.deleteCategory(selectedCategory);
        }
    }

    public static void makeNewPerson(String name, String desc){
        CreditsHandler.makeNewPerson(name,desc);
    }

    public static void deletePerson(Person person) {
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
        LoginHandler.loginToAccount("","");
    }

    public static void logUserIn(String username,String password) {
        LoginHandler.loginToAccount(username,password);
    }

    public static String loggedInRoleString() {
        return LoginHandler.getUserRoleText();
    }

    public static ArrayList<Program> searchProgram(String text) {
        return CreditsHandler.searchProgram(text);
    }
}
