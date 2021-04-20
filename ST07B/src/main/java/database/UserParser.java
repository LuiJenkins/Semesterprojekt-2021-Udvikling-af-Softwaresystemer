package database;

import logic.CurrentUser;
import logic.ProgramCredits;

public class UserParser extends FileParser{
    public void addUser(ProgramCredits programCredits){}
    public void updateProgram(ProgramCredits programCredits){}
    public void deleteUser(int ID){}
    public CurrentUser getUser(int ID){throw new UnsupportedOperationException();}
}
