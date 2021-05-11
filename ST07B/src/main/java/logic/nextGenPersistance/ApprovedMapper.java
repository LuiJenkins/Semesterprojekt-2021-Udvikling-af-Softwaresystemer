package logic.nextGenPersistance;

import database.PersistanceHandler;
import logic.Approved;
import logic.Person;
import logic.Program;

import java.sql.*;
import java.util.ArrayList;
/*
Class that maps the Approved table in the database.
Approved handles program approvals.
 */
public class ApprovedMapper implements AbstractClassMapper<Approved> {
    public Connection conn;
    public void getConnection() {
        if (conn == null) {
            conn = PersistanceHandler.getConn();
        }
    }
    public ApprovedMapper() {
        getConnection();
    }

    public Approved getFromDB(int id) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT program_id,status,approveddate FROM approved WHERE program_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlRV = stmt.executeQuery();
            if (!sqlRV.next()){
                return null;
            }
            return new Approved(sqlRV.getInt(1),sqlRV.getInt(2),sqlRV.getDate(3));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Approved> getAllFromDB() {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT program_id,status,approveddate FROM approved");
            ResultSet sqlRV = stmt.executeQuery();
            int rowcount = 0;
            ArrayList<Approved> returnValue = new ArrayList<>();
            while (sqlRV.next()){
                returnValue.add(new Approved(sqlRV.getInt(1),sqlRV.getInt(2),sqlRV.getDate(3)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addToDB(Approved o) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO approved (program_id,status,approveddate) VALUES (?,?,?) ON CONFLICT (program_id) DO UPDATE SET status=?,approveddate=?");
            stmt.setInt(1, o.programID);
            stmt.setInt(2,o.status);
            stmt.setInt(4,o.status);
            if (o.approvedDate != null) {
                stmt.setDate(3,new java.sql.Date(o.approvedDate.getTime()));
                stmt.setDate(5,new java.sql.Date(o.approvedDate.getTime()));
            } else {
                stmt.setDate(3,null);
                stmt.setDate(5,null);
            }
            stmt.executeUpdate();
            System.out.println("Approved"+o.programID);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAllToDB(ArrayList<Approved> o) {
        for (Approved a : o) {
            addToDB(a);
        }
    }

    public void removeFromDB(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM approved WHERE program_id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
