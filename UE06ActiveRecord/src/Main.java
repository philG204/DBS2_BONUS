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

public static void testInsert() throws SQLException {
    boolean ok = false;
    try {
        Person person = new Person();
        person.setName("Karl Tester");
        person.insert();
        System.out.println();

        Movie movie = new Movie();
        movie.setTitle("Die tolle Komoedie");
        movie.setYear(2012);
        movie.setType("C");
        movie.insert();
        System.out.println();

        MovieCharacter chr = new MovieCharacter();
        chr.setMovieId(movie.getMovieId());
        chr.setPlayerId(person.getPersonId());
        chr.setCharacter("Hauptrolle");
        chr.setAlias(null);
        chr.setPosition(1);
        chr.insert();
        System.out.println();

        Genre genre = new Genre();
        genre.setGenre("Unklar");
        genre.insert();
        System.out.println();

        MovieGenre movieGenre = new MovieGenre();
        movieGenre.setGenreId(genre.getGenreId());
        movieGenre.setMovieId(movie.getMovieId());
        movieGenre.insert();
        System.out.println();

        //DbManager.getConnection().commit();
    } catch (Exception e) {
        //DbManager.getConnection().rollback();
        throw e;
    }
}

public static void testInsert2() throws SQLException {
    try{
        Movie m2 = new Movie();
        m2.setTitle("M3GAN");
        m2.setYear(2023);
        m2.setType("c");
        m2.insert();
        System.out.println();

        Person p2 = new Person();
        p2.setName("Allison Williams");
        p2.insert();
        System.out.println();

        Genre g2 = new Genre();
        g2.setGenre("Horror");
        g2.insert();
        System.out.println();

        MovieCharacter mc2 = new MovieCharacter();
        mc2.setMovieId(m2.getMovieId());
        mc2.setPlayerId(p2.getPersonId());
        mc2.setCharacter("Gemma Forrester");
        mc2.setPosition(1);
        mc2.setAlias("");
        mc2.insert();
        System.out.println();

        MovieGenre mg2 = new MovieGenre();
        mg2.setGenreId(g2.getGenreId());
        mg2.setMovieId(m2.getMovieId());
        mg2.insert();
        System.out.println();

        // DbManager.getConnection().commit();
    } catch (Exception e) {
        // DbManager.getConnection().rollback();
        throw e;
    }
}


/**
 * Main-Methode
 * @param args
 * @throws SQLException
 */
public static void main(String[] args) throws SQLException {

//    DbManager dbManager = DbManager.getInstance();
//    dbManager.connectToDB();

//    testDBConnection();

//    testInsert();
//    System.out.println();
//    testInsert2();

Starter starter = new Starter();
    starter.run();

}