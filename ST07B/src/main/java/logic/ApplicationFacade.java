package logic;

public class ApplicationFacade {

    public static void makeNewProgram(int id,int producerid,String Titel) {
        CreditsHandler.makeNewCredit(id,producerid,Titel);
    }

    public static void deleteProgram(int ID){
        CreditsHandler.deleteCredit(ID);
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

    public static void makeNewCategory(String new_cat, int prgID, int catID){
        CreditsHandler.getSpecificCredit(prgID).createCategory(catID, new_cat);
    }

    public static void deleteCategory(int catID, int prgID){
        CreditsHandler.getSpecificCredit(prgID).deleteCategory(catID);
    }

    public static void editCategory(int ID, int prgID, String newName){
        CreditsHandler.getSpecificCredit(prgID).setName(newName);
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

    public static void getCurrentPrograms(){
    }

    public static Program getCurrentProgram(int id) {
        return CreditsHandler.getSpecificCredit(id);
    }


}
