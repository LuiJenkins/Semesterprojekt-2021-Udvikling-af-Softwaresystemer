package logic.nextGenPersistance;

import database.PersistanceHandler;

import logic.Category;
import logic.CurrentUser;
import logic.Program;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CurrentUserMapper implements AbstractClassMapper<CurrentUser> {
    public Connection conn = null;

    public CurrentUserMapper() {
        getConnection();
    }

    public void getConnection() {
        if (conn == null) {
            conn = PersistanceHandler.getConn();
        }
    }
    public CurrentUser getFromDB(int id) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT user_id, username, userRole, producer_id FROM app_user WHERE user_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlRV = stmt.executeQuery();
            if (!sqlRV.next()) {
                return null;
            }
            return new CurrentUser(sqlRV.getInt(1), sqlRV.getString(2), sqlRV.getInt(3), sqlRV.getInt(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
        public ArrayList<CurrentUser> getAllFromDB() {
            getConnection();
            try {
                PreparedStatement stmt = conn.prepareStatement("SELECT user_id, username, userRole, producer_id FROM app_user ");
                ResultSet sqlRV = stmt.executeQuery();
                int rowcount = 0;
                ArrayList<CurrentUser> returnValue = new ArrayList<>();
                while (sqlRV.next()){
                    System.out.println(sqlRV.getInt(1)+":"+sqlRV.getString(2)+":"+sqlRV.getInt(3)+":"+sqlRV.getInt(4));
                    returnValue.add(new CurrentUser(sqlRV.getInt(1),sqlRV.getString(2),sqlRV.getInt(3),sqlRV.getInt(4)));
                }
                return returnValue;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }

    public void addToDB(CurrentUser o) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO app_user (user_id,username, userRole, producer_id) VALUES (?,?,?,?) ON CONFLICT (user_id) DO UPDATE SET username=?, userRole=?, producer_id=?");
            stmt.setInt(1, o.getUserID());
            stmt.setString(2,o.getUserName());
            stmt.setInt(3,o.getUserRole());
            stmt.setInt(4,o.getProducerID());
            stmt.setString(5,o.getUserName());
            stmt.setInt(6,o.getUserRole());
            stmt.setInt(7,o.getProducerID());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAllToDB(ArrayList<CurrentUser> o) {
        for(CurrentUser c : o) {
            addToDB(c);
        }
    }

}



