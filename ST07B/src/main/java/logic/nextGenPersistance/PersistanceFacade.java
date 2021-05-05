package logic.nextGenPersistance;

import logic.*;

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
        System.out.println(approved);
        creditsMapper.addAllToDB(creditRelations);
        programMapper.addAllToDB(programs);
        categoryMapper.addAllToDB(categories);
        personMapper.addAllToDB(people);
        approvedMapper.addAllToDB(approved);
    }
    public static void UploadPersonsToDB(ArrayList<Person> pers) {
        personMapper.addAllToDB(pers);
    }

    public static void DownloadProgramsFromDB() {
        ArrayList<Program> Credits;
        ArrayList<Person> Persons;
        ArrayList<Category> Categorys;
        ArrayList<CreditRelation> Creditrelation;

        Persons = personMapper.getAllFromDB();
        Credits = programMapper.getAllFromDB();
        Categorys = categoryMapper.getAllFromDB();
        Creditrelation = creditsMapper.getAllFromDB();
        CreditsHandler.setAllCredits(Credits,Persons);
        for (CreditRelation c : Creditrelation) {
            for (Category cat : Categorys) {
                if (c.categoryId==cat.getId()) {
                    CreditsHandler.getSpecificCredit(c.creditId).createCategory(cat.getName());
                    for (Person p : Persons) {
                        if (p.getId()==c.personId) {
                            CreditsHandler.getSpecificCredit(c.creditId).getCategory(c.categoryId).addPersonToCategory(p);
                        }
                    }
                }
            }
        }
    }
}
