package logic;

public class CurrentUser {
    public int userID;
    public String userName;
    public int userRole;
    public int producerID;

    public CurrentUser(int userID, String userName, int userRole, int producerID) {
        this.userID = userID;
        this.userName = userName;
        this.userRole = userRole;
        this.producerID = producerID;
    }

    public void createUser(String name, int role) {
        throw new UnsupportedOperationException();
    }

    public void setProducerID(int producerID){
        this.producerID = producerID;        ;
    }

    public int getProducerID(){
        return producerID;
    }

    public void setUserName(String uname){
        this.userName = uname;
    }

    public String getUserName(){
        return this.userName;
    }

    public void setUserRole(int userRole){
        this.userRole=userRole;
    }

    public int getUserRole(){
        return userRole;
    }

    public boolean isAllowed(int reqRole){
        return(this.userRole >= reqRole);
    }

    public int getUserID() {
        return userID;
    }

    public void updateUser(){
        throw new UnsupportedOperationException();
    }

    public void deleteUser(){
        throw new UnsupportedOperationException();
    }
}
