package logic;

public class ApplicationFacade {

    public static void makeNewProgram(int id,int producerid,String Titel) {
        CreditsHandler.makeNewCredit(id,producerid,Titel);
    }

    public static void deleteProgram(int ID){
        throw new UnsupportedOperationException();
    }

    public static void editProgram(int ID){
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public static void deletePerson(int ID){
        throw new UnsupportedOperationException();
    }

    public static void updatePerson(int ID, String name, String desc){
        throw new UnsupportedOperationException();
    }

    public static String getCurrentPrograms(){
        throw new UnsupportedOperationException();
    }

    public static ProgramCredits getCurrentProgram(int id) {
        return CreditsHandler.getSpecificCredit(id);
    }


}
