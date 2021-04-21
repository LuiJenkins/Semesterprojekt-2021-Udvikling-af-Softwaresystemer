package logic;

import java.util.ArrayList;

public class ApplicationFacade {

    public static void makeNewProgram(int producerid,String Titel) {
        // checks for highest id and creates new program with id+1;
        CreditsHandler.makeNewCredit(producerid,Titel);
    }

    public static void deleteProgram(Program p){
        CreditsHandler.deleteCredit(p);
    }

    public static void editProgram(int ID, String Title){
        CreditsHandler.getSpecificCredit(ID).setName(Title);
    }

    public static void acceptProgram(int ID, int status){
        CreditsHandler.getSpecificCredit(ID).getApproved().setStatus(status);

    }

    public static void denyProgram(int ID,int status){
        CreditsHandler.getSpecificCredit(ID).getApproved().setStatus(status);
    }

    public static void sendCreditToReview(int ID){
        throw new UnsupportedOperationException();
    }

    public static void makeNewCategory(int prgID,String new_cat){
        CreditsHandler.getSpecificCredit(prgID).createCategory(new_cat);
    }
    public static void makeNewCategory(Program p,String new_cat){
        p.createCategory(new_cat);
    }

    public static void deleteCategory(int catID, int prgID){
        CreditsHandler.getSpecificCredit(prgID).deleteCategory(catID);
    }

    public static void editCategory(int ID, int prgID, String newName){
        CreditsHandler.getSpecificCredit(prgID).setName(newName);
    }

    public static void makeNewPerson(String name, String desc){
        CreditsHandler.makeNewPerson(name,desc);
    }

    public static void deletePerson(int ID){
        CreditsHandler.deletePerson(ID);
    }

    public static void updatePerson(int ID, String name, String desc){
        CreditsHandler.updatePerson(ID,name,desc);
    }

    public static ArrayList<Program> getCurrentPrograms(){
        return CreditsHandler.getAllCreditsFromLocal();
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
}
