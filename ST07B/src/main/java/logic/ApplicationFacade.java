package logic;

import java.util.ArrayList;

public class ApplicationFacade {

    public static void makeNewProgram(int producerid,String Titel) {
        // checks for highest id and creates new program with id+1;
        CreditsHandler.makeNewCredit(producerid,Titel);
    }

    public static void deleteProgram(int ID){
        CreditsHandler.deleteCredit(ID);
    }

    public static void editProgram(int ID, String Title){
        CreditsHandler.getSpecificCredit(ID).setName(Title);
    }

    public static void acceptProgram(int ID){
        throw new UnsupportedOperationException();
    }

    public static void denyProgram(int ID){
        throw new UnsupportedOperationException();
    }

    public static void sendCreditToReview(int ID){
        throw new UnsupportedOperationException();
    }

    public static void makeNewCategory(String new_cat){
        throw new UnsupportedOperationException();
    }

    public static void deleteCategory(int ID){
        throw new UnsupportedOperationException();
    }

    public static int editCategory(int ID){
        throw new UnsupportedOperationException();
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


}
