package database;

import logic.CurrentUser;
import logic.Program;

public class UserParser extends FileParser{
    public void addUser(Program program){}
    public void updateProgram(Program program){}
    public void deleteUser(int ID){}
    public CurrentUser getUser(int ID){throw new UnsupportedOperationException();}
}
