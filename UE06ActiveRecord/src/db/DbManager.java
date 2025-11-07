package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/db01";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection conn;
    private static DbManager instance;

    private DbManager() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to database");
        } catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Fehler beim Aufbau der Datenbankverbindung");
        }
    }

    public static synchronized DbManager getInstance() {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}
