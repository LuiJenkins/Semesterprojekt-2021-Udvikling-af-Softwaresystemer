package logic.nextGenPersistance;

import logic.Approved;
import logic.Category;
import logic.Person;
import logic.Program;

import java.util.ArrayList;

public class PersistanceFacade {
    public static CreditsMapper creditsMapper = new CreditsMapper();
    public static CategoryMapper categoryMapper = new CategoryMapper();
    public static PersonMapper personMapper = new PersonMapper();
    public static ProgramMapper programMapper = new ProgramMapper();
    public static ApprovedMapper approvedMapper = new ApprovedMapper();


    public static void UploadProgramsToDB(ArrayList<Program> prog) {
        ArrayList<CreditRelation> creditRelations = new ArrayList<>();
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Program> programs = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Approved> approved = new ArrayList<>();

        for (Program p : prog) {
            for(Category c : p.getAllCategory()) {
                for (Person pe : c.getPersonsFromCategory()) {
                    creditRelations.add(new CreditRelation(p.getProgramID(),c.getId(),pe.getId()));
                    people.add(pe);
                }
                categories.add(c);
            }
            programs.add(p);
            if (p.getApproved() != null) {
                approved.add(p.getApproved());
            }
        }
        System.out.println("");
        /*creditsMapper.addAllToDB(creditRelations);
        programMapper.addAllToDB(programs);
        categoryMapper.addAllToDB(categories);
        personMapper.addAllToDB(people);*/
        approvedMapper.addAllToDB(approved);
    }
}
