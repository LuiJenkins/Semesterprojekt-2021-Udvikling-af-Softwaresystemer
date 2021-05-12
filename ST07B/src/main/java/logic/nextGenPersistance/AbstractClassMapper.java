package logic.nextGenPersistance;

import logic.Program;

import java.sql.Connection;
import java.util.ArrayList;
/*
Interface which is implemented by all Mapper classes

 */
public interface AbstractClassMapper<T> {
    Connection conn = null;
    void getConnection();
    T getFromDB(int id);
    ArrayList<T> getAllFromDB();
    void addToDB(T o);
    void addAllToDB(ArrayList<T> o);
    void removeFromDB(int id);
}
