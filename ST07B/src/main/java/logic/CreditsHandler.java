package logic;
/*
Class that handels the credits of a program.

 */
import logic.nextGenPersistance.PersistanceFacade;

import java.util.ArrayList;

public class CreditsHandler {
    private static ArrayList<Program> currentLoadedProgramCredits = new ArrayList<>();
    private static ArrayList<Person> currentLoadedPersons = new ArrayList<>();

    public static ArrayList<Program> getAllCreditsFromLocal(){
        return currentLoadedProgramCredits;
    }
    public static void setAllCredits(ArrayList<Program> credits,ArrayList<Person> persons) {
        currentLoadedProgramCredits = credits;
        currentLoadedPersons = persons;
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
        Program p = new Program(highestId+1,producerid,name);
        currentLoadedProgramCredits.add(p);
        getSpecificCredit(highestId+1).createCategory("Skuespillere");
        getSpecificCredit(highestId+1).createCategory("Producer");
        getSpecificCredit(highestId+1).createCategory("Lyd");
        getSpecificCredit(highestId+1).createCategory("Musik");
        getSpecificCredit(highestId+1).createCategory("Kamera");
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

    public static void deletePerson(Person person) {
        boolean isPersonInACredit = false;
        for (Program p : currentLoadedProgramCredits) {
            for (Category c : p.getAllCategory()) {
                for (Person pr : c.getPersonsFromCategory()) {
                    if (pr.equals(person)) {
                        isPersonInACredit = true;
                    }
                }
            }
        }
        if (isPersonInACredit == false) {
            currentLoadedPersons.remove(person);
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

    public static ArrayList<Person> getPersonsFromPersonDB() {
        System.out.println("Size of all persons in db: "+currentLoadedPersons.size());
        return currentLoadedPersons;
    }

    public static ArrayList<Program> searchProgram(String text) {
        ArrayList<Program> filteredQuery = new ArrayList<>();
        for (Program p : currentLoadedProgramCredits) {
            if(p.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredQuery.add(p);
            }
        }
        return filteredQuery;
    }

    public static ArrayList<Person> searchPerson(String text) {
        ArrayList<Person> filteredQuery = new ArrayList<>();
        for (Person p : currentLoadedPersons) {
            if(p.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredQuery.add(p);
            }
        }
        return filteredQuery;
    }

    public static String crossReferencePersonFromCredits(Person selectedPerson) {
        String s = "▶▶ "+selectedPerson.getName()+"\n";

        s += "──━━━ Medvirker i ━━━──\n";

        for (Program p : currentLoadedProgramCredits) {
            for(Category c : p.getAllCategory()) {
                for (Person pe : c.getPersonsFromCategory()) {
                    if (pe.equals(selectedPerson)) {
                        s += "\""+p.getName()+"\" som \""+c.getName()+"\"\n";
                    }
                }
            }
        }
        return s;
    }
}
