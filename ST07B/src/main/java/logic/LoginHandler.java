package logic;

import java.io.File;
import java.util.Scanner;

public class LoginHandler {
    public static CurrentUser currentUser;

    public static void loginToAccount(String username, String password) {
        // dummy block for test      ######
        int userRole = 0;
        int producerId = 0;
        if (password.contains("#1")) {
            userRole=1;
        } else {
            if (password.contains("#2")) {
                userRole = 2;
            } else {
                if (password.contains("#3")) {
                    userRole = 3;
                }
            }
        }
        if (password.contains("&1")) {
            producerId=1;
        } else {
            if (password.contains("&2")) {
                producerId = 2;
            } else {
                if (password.contains("&3")) {
                    producerId = 3;
                }
            }
        }
        if(currentUser == null){
            currentUser = new CurrentUser(0,username, userRole, producerId);
        }

        // dummy block end
        // CurrentUser currentUser = new CurrentUser(0,username,userRole,producerId);

    }
    public int getUserRole(){
        return currentUser.getUserRole();

//      throw new UnsupportedOperationException();
    }



}
