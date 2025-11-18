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
public static void main(String[] args) throws Exception {
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

 Movie m3gan = new Movie();
    m3gan.setTitle("M3GAN");
    m3gan.setType("C");
    m3gan.setYear(2022);
    //m3gan.insert();


    Person Allison_Williams = new Person();
    Allison_Williams.setName("Allison Williams");
    //Allison_Williams.insert();
    Person Violet_McGraw = new Person();
    Violet_McGraw.setName("Violet McGraw");
    //Violet_McGraw.insert();

    Genre horror = new Genre();
    horror.setGenre("Horror");
    //horror.insert();
    MovieGenre mv = new MovieGenre();
    mv.insert("M3GAN", "Horror");
    MovieCharacter movChar = new MovieCharacter();
    movChar.insert("M3GAN","Allison Williams","Gemma Forrester",1,"Gem");

}