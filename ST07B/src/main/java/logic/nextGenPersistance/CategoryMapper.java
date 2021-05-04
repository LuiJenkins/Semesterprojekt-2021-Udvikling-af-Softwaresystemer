package logic.nextGenPersistance;

import database.PersistanceHandler;
import logic.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryMapper implements AbstractClassMapper<Category> {
    public Connection conn;

    public CategoryMapper() {
        getConnection();
    }

    public void getConnection() {
        if (conn == null) {
            conn = PersistanceHandler.getConn();
        }
    }

    public Category getFromDB(int id) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT catagory_id,catagoryName,catagoryType,catagoryNumber FROM categorys WHERE category_id = ?");
            stmt.setInt(1, id);
            ResultSet sqlRV = stmt.executeQuery();
            if (!sqlRV.next()){
                return null;
            }
            return new Category(sqlRV.getInt(1),sqlRV.getString(2),sqlRV.getInt(4));
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<Category> getAllFromDB() {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT catagory_id,catagoryName,catagoryType,catagoryNumber FROM categorys ");
            ResultSet sqlRV = stmt.executeQuery();
            int rowcount = 0;
            ArrayList<Category> returnValue = new ArrayList<>();
            while (sqlRV.next()){
                returnValue.add(new Category(sqlRV.getInt(1),sqlRV.getString(2),sqlRV.getInt(3)));
            }
            return returnValue;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void addToDB(Category o) {
        getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO categorys (category_id,categoryname,categorytype,categorynumber) VALUES (?,?,NULL,?)");
            stmt.setInt(1, o.getId());
            stmt.setString(2,o.getName());
            stmt.setInt(3,o.getSortingOrder());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addAllToDB(ArrayList<Category> o) {
        for(Category c : o) {
            addToDB(c);
        }
    }
}
