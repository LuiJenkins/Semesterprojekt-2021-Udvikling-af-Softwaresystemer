package logic;

import java.util.ArrayList;

public class CreditsHandler {
    private static ArrayList<Program> currentLoadedProgramCredits = new ArrayList<>();
    private static ArrayList<Person> currentLoadedPersons = new ArrayList<>();

    public static void getProgramCreditsFromDB (int [] IDList){
        throw new UnsupportedOperationException();
    }

    public static ArrayList<Program> getAllCreditsFromLocal(){
        return currentLoadedProgramCredits;
    }
    public static void makeNewCredit(int producerid, String name) {
        int highestId = 0;
        for (Program p : currentLoadedProgramCredits) {
            if (p.getName().equals(name)) {
                return;
            }
            if (p.getProgramID() > highestId) {
                highestId = p.getProgramID();
            }
        }
        currentLoadedProgramCredits.add(new Program(highestId+1,producerid,name));
    }
    public static void deleteCredit(Program program) {
        currentLoadedProgramCredits.remove(program);
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
    public static Person getSpecificPerson(int id) {
        for (int i = 0; i < currentLoadedPersons.size();i++) {
            if (currentLoadedPersons.get(i).getId() == id) {
                return currentLoadedPersons.get(i);
            }
        }
        return new Person();
    }
    public static void makeNewPerson(String name, String desc) {
        int highestId = 0;
        for (Person p : currentLoadedPersons) {
            if (p.getName() == name && p.getDesc() == desc) {
                return;
            }
            if (p.getId() > highestId) {
                highestId = p.getId();
            }
        }
        currentLoadedPersons.add(new Person(highestId+1,name,desc));
    }

    public static void deletePerson(int id) {
        currentLoadedPersons.remove(getSpecificPerson(id));
        // Deleting all persons from all credits
        for (Program p : currentLoadedProgramCredits) {
            for (Category c : p.getAllCategory()) {
                for (Person pr : c.getPersonsFromCategory()) {
                    if (pr.getId() == id) {
                        c.getPersonsFromCategory().remove(p);
                    }
                }
            }
        }
    }

    public static void updatePerson(int id, String name, String desc) {
        for (int i = 0; i < currentLoadedPersons.size();i++) {
            if (currentLoadedPersons.get(i).getId() == id) {

                int highestId = 0;
                for (Person p : currentLoadedPersons) {
                    if (p.getName() == name && p.getDesc() == desc) {
                        return;
                    }
                    if (p.getId() > highestId) {
                        highestId = p.getId();
                    }
                }
                currentLoadedPersons.set(i,new Person(highestId,name,desc));

                // Updating all persons in all credtis
                for (Program p : currentLoadedProgramCredits) {
                    for (Category c : p.getAllCategory()) {
                        for (Person pr : c.getPersonsFromCategory()) {
                            if (pr.getId() == id) {
                                c.getPersonsFromCategory().set(c.getPersonsFromCategory().indexOf(pr),new Person(highestId,name,desc));
                            }
                        }
                    }
                }
            }
        }
    }
}
