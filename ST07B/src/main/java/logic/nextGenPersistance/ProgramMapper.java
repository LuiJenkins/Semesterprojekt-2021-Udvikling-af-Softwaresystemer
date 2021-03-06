package logic.nextGenPersistance;
/*
Class that maps the programs table in the database.
the programs table contains the individual programs that is credited.
 */
import database.PersistanceHandler;
import logic.Program;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProgramMapper implements AbstractClassMapper<Program> {
    public Connection conn = null;

    public ProgramMapper() {
        getConnection();
    }

    public void getConnection() {
        if (conn == null) {
            conn = PersistanceHandler.getConn();
        }
    }
    public Program getFromDB(int id) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement( "SELECT programs.program_id, programs.producer_id, programs.programName,approved.status FROM programs " +
                                                                "JOIN approved on (programs.program_id=approved.program_id) AND approved.program_id=?;");
            stmt.setInt(1, id);
            ResultSet sqlRV = stmt.executeQuery();
            if (!sqlRV.next()){
                return null;
            }
            return new Program(sqlRV.getInt(1),sqlRV.getInt(2),(String)sqlRV.getString(3),sqlRV.getInt(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public ArrayList<Program> getAllFromDB() {
        try {
            PreparedStatement stmt = conn.prepareStatement( "SELECT programs.program_id, programs.producer_id, programs.programName,approved.status FROM programs " +
                                                                "JOIN approved on (programs.program_id=approved.program_id);");
            ResultSet sqlRV = stmt.executeQuery();
            int rowcount = 0;
            ArrayList<Program> returnValue = new ArrayList<>();
            while (sqlRV.next()){
                returnValue.add(new Program(sqlRV.getInt(1),sqlRV.getInt(2),sqlRV.getString(3), sqlRV.getInt(4)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addToDB(Program o) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO programs (program_id,producer_id,programName,playingTimeSec) VALUES (?,?,?,NULL) ON CONFLICT (program_id) DO UPDATE SET producer_id=?,programName=?");
            stmt.setInt(1, o.getProgramID());
            stmt.setInt(2,o.getProducerID());
            stmt.setString(3,o.getName());
            stmt.setInt(4,o.getProducerID());
            stmt.setString(5,o.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAllToDB(ArrayList<Program> o) {
        for(Program p : o) {
            addToDB(p);
        }
    }

    public void removeFromDB(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM programs WHERE program_id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
