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
                    "    producer_id SERIAL PRIMARY KEY,\n" +
                    "producerName VARCHAR(250),\n" +
                    "producerInfo VARCHAR(250)\n);");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS programs (\n" +
                    "program_id SERIAL PRIMARY KEY,\n" +
                    "producer_id INT,\n" +
                    "programName VARCHAR(250),\n" +
                    "playingTimeSec INT,\n" +
                    "FOREIGN KEY (producer_id) \n" +
                    "REFERENCES producers (producer_id)\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS app_user (\n" +
                    "user_id SERIAL PRIMARY KEY,\n" +
                    "username varchar(45) NOT NULL,\n" +
                    "password varchar(150) NOT NULL,\n" +
                    "fullName varchar(250),\n" +
                    "userRole INT,\n" +
                    "producer_id INT,\n" +
                    "FOREIGN KEY (producer_id) \n" +
                    "REFERENCES producers (producer_id)\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS approved (\n" +
                    "    approved_id SERIAL PRIMARY KEY,\n" +
                    "program_id INT, -- the approved program\n" +
                    "status INT,  -- 1 if approved 0 = not approved\n" +
                    "approvedBy INT,  -- who has approved it, if not approved null\n" +
                    "approvedDate TIMESTAMP -- time and date it was approved\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS persons (\n" +
                    "    person_id SERIAL PRIMARY KEY,\n" +
                    "personName VARCHAR(200),\n" +
                    "personDesc VARCHAR(250)\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS categorys (\n" +
                    "    category_id SERIAL PRIMARY KEY,\n" +
                    "categoryName VARCHAR(200),\n" +
                    "categoryType INT,\n" +
                    "categoryNumber INT\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS channels (\n" +
                    "channel_id SERIAL PRIMARY KEY,\n" +
                    "channelName VARCHAR(200),\n" +
                    "channelInfo VARCHAR(250)\n" +
                    ");");
            SqlPostQuery("CREATE TABLE IF NOT EXISTS timeslots (\n" +
                    "    timeslot_id SERIAL PRIMARY KEY,\n" +
                    "timeFrom TIMESTAMP,\n" +
                    "timeTO TIMESTAMP,\n" +
                    "channel_id INT,\n" +
                    "program_id INT,\n" +
                    "FOREIGN KEY (program_id) \n" +
                    "REFERENCES programs (program_id),\n" +
                    "FOREIGN KEY (channel_id) \n" +
                    "REFERENCES channels (channel_id)\n" +
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
