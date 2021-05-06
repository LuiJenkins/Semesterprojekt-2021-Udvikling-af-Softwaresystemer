package database;

import java.sql.*;

public class PersistanceHandler {
    private static PersistanceHandler instance;
    /*private static String url = "localhost";
    private static int port = 3000;
    private static String dbname = "creditsdb";
    private static String username = "postgres";
    private static String password = "toor";*/
    private static String url = "rogue.db.elephantsql.com";
    private static int port = 5432;
    private static String dbname = "cyhlnbjt";
    private static String username = "cyhlnbjt";
    private static String password = "sQ3Tg5T6-kPdUAuzYPP0kjTewCC7m7rE";
    private static Connection conn;
    public static void initDB() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + dbname, username, password);
            //conn = DriverManager.getConnection(url);
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (conn == null) System.exit(-1);
            /*SqlPostQuery("CREATE DATABASE \"creditsdb\"\n" +
                    "WITH \n" +
                    "OWNER = postgres\n" +
                    "ENCODING = 'UTF8'\n" +
                    "CONNECTION LIMIT = -1;");*/
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
                    "program_id SERIAL PRIMARY KEY,\n" +
                    "status INT,\n" +
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
            SqlPostQuery("CREATE TABLE IF NOT EXISTS app_user (\n" +
                    "user_id SERIAL PRIMARY KEY,\n" +
                    "username VARCHAR(45) NOT NULL,\n" +
                    "password VARCHAR(150),\n" +
                    "fullName VARCHAR(250),\n" +
                    "userRole INT,\n" +
                    "producer_id INT" +
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
