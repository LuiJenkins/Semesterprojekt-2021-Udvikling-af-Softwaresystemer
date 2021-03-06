package logic.nextGenPersistance;

import logic.*;

import java.util.ArrayList;
/*
The facade of the persistance system, it makes instances of the individual mappers
 */
public class PersistanceFacade {
    public static CreditsMapper creditsMapper = new CreditsMapper();
    public static CategoryMapper categoryMapper = new CategoryMapper();
    public static PersonMapper personMapper = new PersonMapper();
    public static ProgramMapper programMapper = new ProgramMapper();
    public static ApprovedMapper approvedMapper = new ApprovedMapper();
    public static CurrentUserMapper currentUserMapper = new CurrentUserMapper();

    public static void UploadProgramsToDB(ArrayList<Program> prog) {
        ArrayList<CreditRelation> creditRelations = new ArrayList<>();
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Program> programs = new ArrayList<>();
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<Approved> approved = new ArrayList<>();


        for (Program p : prog) {
            for(Category c : p.getAllCategory()) {
                for (Person pe : c.getPersonsFromCategory()) {
                    if (pe.modified) {
                        creditRelations.add(new CreditRelation(p.getProgramID(),c.getId(),pe.getId()));
                        people.add(pe);
                    }
                }
                if (c.modified) {
                    categories.add(c);
                }
            }
            if (p.modified) {
                programs.add(p);
            }
            if (p.getApproved() != null && p.modified) {
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

    public static void TestUserFromDB(String name, String password) {
        CurrentUser currentUser = currentUserMapper.getLoginFromDB(name,password);
        if(currentUser != null) {
            LoginHandler.currentUser = currentUser;
        } else {
            if(LoginHandler.currentUser == null) {
                LoginHandler.currentUser = new CurrentUser(0,"",0,0);
            } else {
                LoginHandler.currentUser.resetUser();
            }
        }
    }

    public static void downloadAllFromDB() {
        ArrayList<Program> Credits;
        ArrayList<Person> Persons;
        ArrayList<Category> Categorys;
        ArrayList<CreditRelation> Creditrelation;

        Persons = personMapper.getAllFromDB();
        Credits = programMapper.getAllFromDB();
        Categorys = categoryMapper.getAllFromDB();
        Creditrelation = creditsMapper.getAllFromDB();
        for (Program p : Credits) { p.modified = false; }
        for (Person p : Persons) { p.modified = false; }
        CreditsHandler.setAllCredits(Credits,Persons);
        for (CreditRelation c : Creditrelation) {
            for (Category cat : Categorys) {
                if (c.categoryId==cat.getId()) {
                    CreditsHandler.getSpecificCredit(c.creditId).createCategory(cat.getName());
                    for (Person p : Persons) {
                        if (p.getId()==c.personId) {
                            p.modified = false;
                            CreditsHandler.getSpecificCredit(c.creditId).getCategory(c.categoryId).addPersonToCategory(p);
                        }
                    }
                }
            }
        }
    }
}
