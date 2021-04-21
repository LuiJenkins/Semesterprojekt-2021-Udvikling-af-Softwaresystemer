package logic;

public class User {
    private int userID=0;
    private String userName;
    private String userPassword;
    private int userRole = 0;

    public User(int userID, String userName, String userPassword, int userRole) {
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;

        // This is for the first ver.    ##########
        if (userPassword.contains("1")) {
            this.userRole=1;
        } else {
            if (userPassword.contains("2")) {
                this.userRole = 2;
            } else {
                if (userPassword.contains("3")) {
                    this.userRole = 3;
                }
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public boolean isAllowed(int level){
        return (this.userRole >= level);
    }

}
