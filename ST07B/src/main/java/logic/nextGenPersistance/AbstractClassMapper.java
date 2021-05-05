package logic.nextGenPersistance;

import logic.Program;

import java.sql.Connection;
import java.util.ArrayList;

public interface AbstractClassMapper<T> {
    Connection conn = null;
    void getConnection();
    T getFromDB(int id);
    ArrayList<T> getAllFromDB();
    void addToDB(T o);
    void addAllToDB(ArrayList<T> o);
}
