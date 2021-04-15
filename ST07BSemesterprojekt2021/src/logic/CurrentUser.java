package logic;

public class CurrentUser {
    int userID;
    String userName;
    int userRole;
    int ProducerID;

    public void createUser(String name, int role) {
        throw new UnsupportedOperationException();
    }

    public void setProducerID(){
        throw new UnsupportedOperationException();
    }

    public int getProducerID(){
        return ProducerID;
    }

    public void setUserName(String uname){
        throw new UnsupportedOperationException();
    }

    public String getUserName(){
        return userName;
    }

    public void setUserRole(int UserRole){
        throw new UnsupportedOperationException();
    }

    public int getUserRole(){
        return userRole;
    }

    public void isAllowed(int reqRole){
        throw new UnsupportedOperationException();
        /*meningen med denne method er at man skal parse en user og et "spørgsmål" og så
        returner den om den givne user har permissions
         */
    }

    public void updateUser(){
        throw new UnsupportedOperationException();
    }

    public void deleteUser(){
        throw new UnsupportedOperationException();
    }
}
