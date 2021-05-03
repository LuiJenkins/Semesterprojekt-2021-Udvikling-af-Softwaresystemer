package logic.nextGenPersistance;

import database.PersistanceHandler;
import logic.Category;
import logic.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditsMapper implements AbstractClassMapper<CreditRelation> {
    public Connection conn;
    public void getConnection() {
        if (conn == null) {
            conn = PersistanceHandler.getConn();
        }
    }

    public CreditRelation getFromDB(int id) {
        return null;
    }

    public CreditRelation getFromDB(int program_id,int catagory_id,int person_id) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT credit_id,program_id,catagory_id,person_id FROM credits WHERE program_id = ? AND catagory_id = ? AND person_id = ?");
            stmt.setInt(1, program_id);
            stmt.setInt(2, catagory_id);
            stmt.setInt(3, person_id);
            ResultSet sqlRV = stmt.executeQuery();
            if (!sqlRV.next()){
                return null;
            }
            return new CreditRelation(sqlRV.getInt(1),sqlRV.getInt(2),sqlRV.getInt(3));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<CreditRelation> getAllFromDB() {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT credit_id,program_id,catagory_id,person_id FROM credits");
            ResultSet sqlRV = stmt.executeQuery();
            int rowcount = 0;
            ArrayList<CreditRelation> returnValue = new ArrayList<>();
            while (sqlRV.next()){
                returnValue.add(new CreditRelation(sqlRV.getInt(1),sqlRV.getInt(2),sqlRV.getInt(3)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addToDB(CreditRelation o) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO credits (credit_id,program_id,catagory_id,person_id,numberInCatagory) VALUES (?,?,?,NULL)");
            stmt.setInt(1, o.creditId);
            stmt.setInt(2,o.programId);
            stmt.setInt(3,o.personId);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAllToDB(ArrayList<CreditRelation> o) {

    }
}
