import db.DbManager;
import Entities.*;
import java.sql.*;


/**
 * Testmethode, die Daten aus der MovieDB2 ausgibt.
 */
public static void testDBConnection() throws SQLException {
    String url="jdbc:postgresql://localhost:5432/db01";
    //DbManager dbManager = new DbManager(credentials.getUsername(), credentials.getPassword(), credentials.getUrl());
    DbManager dbManager = DbManager.getInstance();
    dbManager.connectToDB();
    String sqlTest = "SELECT ID, TITLE FROM MOVIEDB2.MOVIE WHERE ID BETWEEN 600000 AND 600100";
    Connection conn = dbManager.getConnection();
    try(Statement stmt = conn.createStatement()){
        try(ResultSet rs = stmt.executeQuery(sqlTest)){
            while (rs.next()) {
                long mID = rs.getLong("ID");
                String mTitle = rs.getString("TITLE");
                System.out.println("ID: " + mID + " Title: " + mTitle);
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei Statement stmt = conn.createStatement():\n"+e);
        }
    } catch (SQLException e) {
        System.out.println("Fehler bei ResultSet rs = stmt.executeQuery(sqlTest):\n"+e);
    }
}


/**
 * Main-Methode
 * @param args
 * @throws SQLException
 */
public static void main(String[] args) throws SQLException {

//    testDBConnection();
//    Genre genre = new Genre();
//    genre.setGenre("Horror");
//    genre.insert();


    // Bitte stehen lassen! Sonst ist keine Verbindung zur DB bzw. zur GUI möglich!
    DbManager dbManager = DbManager.getInstance();
    dbManager.connectToDB();
    Starter starter = new Starter();
    starter.run();
    //=============================================================================


    Movie m2 = new Movie();
    m2.setTitle("Der Terminator");
    m2.setType("C");
    m2.setYear(1984);
    m2.insert();

    Person p2 = new Person();
    p2.setName("Arnold Schwarzenegger");

    p2.insert();

    Genre g2 = new Genre();
    g2.setGenre("Horror");
    g2.insert();
    Genre g3 = new Genre();
    g3.setGenre("Science Fiction");

}