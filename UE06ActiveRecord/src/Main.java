import db.DbManager;
import Entities.*;
import java.sql.*;


/**
 * Testmethode, die Daten aus der MovieDB2 ausgibt.
 */
public static void testDBConnection() throws SQLException {
    String url="jdbc:postgresql://localhost:5432/db01";
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
    //testDBConnection();
//    Genre genre = new Genre();
//    genre.setGenre("Horror");
//    genre.insert();


    // Bitte stehen lassen! Sonst ist keine Verbindung zur DB bzw. zur GUI möglich!
    DbManager dbManager = DbManager.getInstance();
    dbManager.connectToDB();
    Starter starter = new Starter();
    starter.run();
    //=============================================================================


//    Movie m3gan = new Movie();
//    m3gan.setTitle("Der Terminator");
//    m3gan.setType("C");
//    m3gan.setYear(1998);
//    m3gan.insert();
//
//
//    Person Allison_Williams = new Person();
//    Allison_Williams.setName("Arnold Schwarzenegger");
//    Allison_Williams.insert();
//    Person Violet_McGraw = new Person();
//    Violet_McGraw.setName("Violet McGraw");
//    Violet_McGraw.insert();
//
//    Genre scienceFiction = new Genre();
//    scienceFiction.setGenre("Scinece Fiction");
//    scienceFiction.insert();
//    MovieGenre mv = new MovieGenre();
    //mv.insert("Der Terminator","Science Fiction");
}