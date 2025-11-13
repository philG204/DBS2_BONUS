package db;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


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
     * Fügt einen neuen Datensatz in eine Tabelle ein.
     * @param stmt
     */
    public void executeInsert(PreparedStatement stmt){
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim auslesen von Movie.title:\n"+e.getMessage());
        }

        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Fehler beim auslesen von Movie.title:\n"+e.getMessage());
            }
        }
    }

    /**
     * Liest die ID eines hinzugefügten Datensatzes aus.
     * @param stmt
     * @return
     * @throws SQLException
     */
    public long getID(PreparedStatement stmt) throws SQLException{
        ResultSet rs = null;
        System.out.println(stmt.toString());
        long id=0;
        try {
            rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim auslesen:\n"+e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Fehler beim schlie0en:\n"+e.getMessage());
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Fehler beim schlie0en:\n"+e.getMessage());
                }
            }
        }
        return id;
    }


    /**
     * Gibt Connection conn zurück.
     * @return
     */
    public Connection getConnection() {
        return conn;
    }
}
