package logic.nextGenPersistance;

/*
Class that maps the persons table in the database.
the persons table contains the individuals tha is registeret as being credited in the system.
 */

import database.PersistanceHandler;
import logic.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonMapper implements AbstractClassMapper<Person> {
    public Connection conn;

    public PersonMapper() {
        getConnection();
    }

    public void getConnection() {
        if (conn == null) {
            conn = PersistanceHandler.getConn();
        }
    }

    public Person getFromDB(int id) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT person_id,personName,personDesc FROM persons WHERE person_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlRV = stmt.executeQuery();
            if (!sqlRV.next()){
                return null;
            }
            return new Person(sqlRV.getInt(1),sqlRV.getString(2),sqlRV.getString(3));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Person> getAllFromDB() {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT person_id,personName,personDesc FROM persons ");
            ResultSet sqlRV = stmt.executeQuery();
            int rowcount = 0;
            ArrayList<Person> returnValue = new ArrayList<>();
            while (sqlRV.next()){
                returnValue.add(new Person(sqlRV.getInt(1),sqlRV.getString(2),sqlRV.getString(3)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addToDB(Person o) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO persons (person_id,personName,personDesc) VALUES (?,?,?) ON CONFLICT (person_id) DO UPDATE SET personname = ?,persondesc=?");
            stmt.setInt(1, o.getId());
            stmt.setString(2,o.getName());
            stmt.setString(3,o.getDesc());
            stmt.setString(4,o.getName());
            stmt.setString(5,o.getDesc());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAllToDB(ArrayList<Person> o) {
        for (Person p : o) {
            addToDB(p);
        }
    }

    public void removeFromDB(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM persons WHERE person_id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
