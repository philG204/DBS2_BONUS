package db;

import java.sql.*;
import java.util.*;
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
    static DbManager instance;


    /**
     * Gibt die Singleton-Instanz des {@code DbManager} zurück.
     * Falls noch keine Instanz existiert, wird sie erstellt.
     *
     * @return die einzige Instanz des {@code DbManager}
     */
    public static DbManager getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbManager();
        }
        return instance;
    }


    /**
     * Konstruktor.
     */
    private DbManager() {
        this.username = System.getenv("DBS_USERNAME");
        this.password = System.getenv("DBS_PASSWORD");
        this.url = System.getenv("DBS_URL");
        //this.url = "jdbc:postgresql://localhost:5432/db01";   //Windows
        //this.url = "jdbc:postgresql://localhost:5433/db01";   //Linux
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


    /**
     * Fügt einen neuen Datensatz in eine Tabelle ein.
     * @param stmt
     */
    public int executeInsert(PreparedStatement stmt){
        int status = 0;
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fehler beim einfügen:\n"+e.getMessage());
            status = 1;
        }

        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Fehler beim schließen:\n"+e.getMessage());
                status = 1;
            }
        }
        return status;
    }


    /**
     * Liest die ID eines hinzugefügten Datensatzes aus.
     * @param stmt
     * @return
     * @throws SQLException
     */
    public long getID(PreparedStatement stmt) throws SQLException{
        ResultSet rs = null;
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
     * Führt SQL-Statment mit Zeilenrückgabe aus
     * @param stmt
     * @return ResultSet
     */
    public List<Map<String, Object>> executeSelect(PreparedStatement stmt){
        List<Map<String, Object>> rows = new ArrayList<>();

        try {
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                rows.add(row);
            }
            return rows;
        } catch (SQLException e) {
            System.err.println("Fehler beim auslesen:\n"+e.getMessage());
            throw new RuntimeException(e);

        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Fehler beim schlie0en:\n"+e.getMessage());
                }
            }
        }
    }


    /**
     * Gibt Connection conn zurück.
     * @return
     */
    public static Connection getConnection() {
        return conn;
    }
}