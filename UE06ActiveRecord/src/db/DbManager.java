package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * DbManager verwaltet die Verbindung mit einer Datenbank.
 */
public class DbManager {
    private final String url;
    private final String username;
    private final String password;

    private static Connection conn;
    private static DbManager instance;


    /**
     * Konstruktor für DbManager-Objekt.
     * @param username
     * @param password
     * @param url
     */
    public DbManager(String username, String password, String url) {
            this.url = url;
            this.username = username;
            this.password = password;
    }


    /**
     * Stellt eine Verbindung zur Datenbank her und speichert diese in conn.
     */
    public void connectToDB(){
        try {
            System.out.println("Verbinden mit "+url+"...");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Verbunden mit "+url+"!");
        } catch(SQLException e){
            // e.printStackTrace();
            throw new RuntimeException("Fehler beim Aufbau der Datenbankverbindung!\n", e);
        }
    }


//    /**
//     * Gibt die Singleton-Instanz des {@code DbManager} zurück.
//     * Falls noch keine Instanz existiert, wird sie erstellt.
//     *
//     * @return die einzige Instanz des {@code DbManager}
//     */
//    public static synchronized DbManager getInstance() {
//        if (instance == null) {
//            instance = new DbManager();
//        }
//        return instance;
//    }


    /**
     * Gibt Connection conn zurück.
     * @return
     */
    public Connection getConnection() {
        return conn;
    }
}
