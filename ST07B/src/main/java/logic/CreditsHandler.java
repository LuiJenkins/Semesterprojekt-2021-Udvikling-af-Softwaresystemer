package logic;

import java.util.ArrayList;

public class CreditsHandler {
    private static ArrayList<Program> currentLoadedProgramCredits = new ArrayList<>();

    public static void getProgramCreditsFromDB (int [] IDList){
        throw new UnsupportedOperationException();
    }

    public static int[] getAllCreditsFromLocal(){
        throw new UnsupportedOperationException();
    }
    public static void makeNewCredit(int id,int producerid, String name) {
        currentLoadedProgramCredits.add(new Program(id,producerid,name));
    }
    public static void deleteCredit(int id) {
        currentLoadedProgramCredits.remove(getSpecificCredit(id));
    }


    public static Program getSpecificCredit(int ID){
        for (int i = 0; i < currentLoadedProgramCredits.size();i++) {
            if (currentLoadedProgramCredits.get(i).getProgramID() == ID) {
                return currentLoadedProgramCredits.get(i);
            }
        }
        return new Program();
    }
    public static void addSpecificCredit(Program program) {
        currentLoadedProgramCredits.add(program);
    }
}
