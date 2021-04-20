package logic;

import java.util.ArrayList;

public class CreditsHandler {
    private static ArrayList<ProgramCredits> currentLoadedProgramCredits = new ArrayList<>();

    public static void getProgramCreditsFromDB (int [] IDList){
        throw new UnsupportedOperationException();
    }

    public static int getAccessibleIDsForUser(){
        throw new UnsupportedOperationException();
    }

    public static int[] getAllCreditsFromLocal(){
        throw new UnsupportedOperationException();
    }
    public static void makeNewCredit(int id,int producerid, String name) {
        currentLoadedProgramCredits.add(new ProgramCredits(id,producerid,name));
    }

    public static ProgramCredits getSpecificCredit(int ID){
        for (int i = 0; i < currentLoadedProgramCredits.size();i++) {
            if (currentLoadedProgramCredits.get(i).getProgramID() == ID) {
                return currentLoadedProgramCredits.get(i);
            }
        }
        return new ProgramCredits();
    }
    public static void addSpecificCredit(ProgramCredits programCredits) {
        currentLoadedProgramCredits.add(programCredits);
    }
}
