package database;

import java.sql.*;
import java.util.ArrayList;

public class PersistanceHandler {
    private static PersistanceHandler instance;
    private static String url = "localhost";
    private static int port = 3000;
    private static String dbname = "creditsdb";
    private static String username = "postgres";
    private static String password = "toor";
    private static Connection conn;
    public static void initDB() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + dbname, username, password);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (conn == null) System.exit(-1);
            SqlPostQuery("CREATE TABLE IF NOT EXISTS producers (\n" +
                    "producer_id SERIAL PRIMARY KEY,\n" +
                    "producerName VARCHAR(250),\n" +
                    "producerInfo VARCHAR(250)\n);");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS programs (\n" +
                    "program_id SERIAL PRIMARY KEY,\n" +
                    "producer_id INT,\n" +
                    "programName VARCHAR(250),\n" +
                    "playingTimeSec INT" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS approved (\n" +
                    "approved_id SERIAL PRIMARY KEY,\n" +
                    "program_id INT,\n" +
                    "status INT,\n" +
                    "approvedBy INT,\n" +
                    "approvedDate TIMESTAMP\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS persons (\n" +
                    "person_id SERIAL PRIMARY KEY,\n" +
                    "personName VARCHAR(200),\n" +
                    "personDesc VARCHAR(250)\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS categorys (\n" +
                    "category_id SERIAL PRIMARY KEY,\n" +
                    "categoryName VARCHAR(200),\n" +
                    "categoryType INT,\n" +
                    "categoryNumber INT\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS credits (\n" +
                    "program_id INT,\n" +
                    "category_id INT,\n" +
                    "person_id INT,\n" +
                    "numberInCategory INT,\n" +
                    "PRIMARY KEY (program_id,category_id,person_id)" +
                    ");");
        }
    }
    public static void SqlPostQuery(String s) {
        try {
            PreparedStatement stmt = conn.prepareStatement(s);
            stmt.executeQuery();
        } catch (SQLException e) {
        }
    }
    public static Connection getConn() {
        return conn;
    }
}
