package database;
import logic.*;
import logic.Category;
import logic.Person;

public class IOFacade {

    public void addPerson(Person person){}
    public void updatePerson(Person person){}
    public void deletePerson(int ID){}
    public Person getPerson(int id) {throw new UnsupportedOperationException();}
    //returns a given person obj with parsed ID
    public void addCategory(Category category){}
    public void updateCategory(Category category){}
    public void deleteCategory(int ID){}
    public Category getCategory(int ID){throw new UnsupportedOperationException();}
    public void addProgram(Program program){}
    public void updateProgram(Program program){}
    public void deleteProgram(int ID){};
    public Program getProgram(int ID){throw new UnsupportedOperationException();}
    public void addUser(CurrentUser currentUser){}
    public void updateUser(CurrentUser currentUser){}
    public void deleteUser(int ID){}
    public CurrentUser getUser(int ID){throw new UnsupportedOperationException();}
    public void addTimeslot(TimeSlot timeSlot){}
    public void updateTimeslot(TimeSlot timeslot){}
    public void deleteTimeslot(int ID){};
    public Program getTimeslot(int ID){throw new UnsupportedOperationException();}
    public void addApproved(Approved approved){}
    public void updateApproved(Approved approved){}
    public void deleteApproved(int ID){}
    public Approved getApproved(int ID){throw new UnsupportedOperationException();}


}
