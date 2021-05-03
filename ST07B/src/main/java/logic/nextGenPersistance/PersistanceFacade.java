package logic.nextGenPersistance;

import logic.Category;
import logic.Person;
import logic.Program;

import java.util.ArrayList;

public class PersistanceFacade {
    public static CreditsMapper creditsMapper = new CreditsMapper();
    public static CategoryMapper categoryMapper = new CategoryMapper();
    public static PersonMapper personMapper = new PersonMapper();
    public static ProgramMapper programMapper = new ProgramMapper();


    public static void UploadProgramsToDB(ArrayList<Program> prog) {
        ArrayList<CreditRelation> creditRelations = new ArrayList<>();
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Program> programs = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        for (Program p : prog) {
            for(Category c : p.getAllCategory()) {
                for (Person pe : c.getPersonsFromCategory()) {
                    System.out.println(p.getName()+":"+c.getName()+":"+pe.getName());
                    creditRelations.add(new CreditRelation(p.getProgramID(),c.getId(),pe.getId()));
                    //people.add(pe);
                }
                //categories.add(c);
            }
            //prog.add(p);
        }
        System.out.println("");
        creditsMapper.addAllToDB(creditRelations);
        programMapper.addAllToDB(prog);
        categoryMapper.addAllToDB(categories);
        personMapper.addAllToDB(people);
    }
}
