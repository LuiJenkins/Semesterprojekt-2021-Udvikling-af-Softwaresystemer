package logic;
/*
Class that holds uesers of the system and their roles.
userRole = 1 producer
userRole = 2 maintainer
userRole = 3 Administrator

 */

public class CurrentUser {
    public int userID;
    public String userName;
    public String password;
    public String fullName;
    public int userRole;
    public int producerID;

    public CurrentUser(int userID, String userName, String password, String fullName, int userRole, int producerID) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.userRole = userRole;
        this.producerID = producerID;
    }

    public CurrentUser(int userID, String userName, int userRole, int producerID) {
        this.userID = userID;
        this.userName = userName;
        this.userRole = userRole;
        this.producerID = producerID;
        this.fullName = "";
        this.password = "";
    }

    public void setProducerID(int producerID){
        this.producerID = producerID;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void updateUser(){
        throw new UnsupportedOperationException();
    }

    public void deleteUser(){
        throw new UnsupportedOperationException();
    }

    public void resetUser(){
        this.userID = 0;
        this.userName = "";
        this.userRole = 0;
        this.producerID = 0;
        this.fullName = "";
        this.password = "";

    }
}
