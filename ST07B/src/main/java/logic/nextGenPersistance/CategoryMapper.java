package logic.nextGenPersistance;

import database.PersistanceHandler;
import logic.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
Class that maps the category table in the database.
category's are groups that arranges persons with function in program in the same group
 */

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
            PreparedStatement stmt = conn.prepareStatement("SELECT category_id,categoryName,categoryType FROM categorys ");
            ResultSet sqlRV = stmt.executeQuery();
            int rowcount = 0;
            ArrayList<Category> returnValue = new ArrayList<>();
            while (sqlRV.next()){
                System.out.println(sqlRV.getInt(1)+":"+sqlRV.getString(2)+":"+sqlRV.getInt(3));
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
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO categorys (category_id,categoryname,categorytype,categorynumber) VALUES (?,?,NULL,?) ON CONFLICT (category_id) DO UPDATE SET categoryname=?,categorynumber=?");
            stmt.setInt(1, o.getId());
            stmt.setString(2,o.getName());
            stmt.setInt(3,o.getSortingOrder());
            stmt.setString(4,o.getName());
            stmt.setInt(5,o.getSortingOrder());
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

    @Override
    public void removeFromDB(int id) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM categorys WHERE category_id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
