package Entities;

import db.DbManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieCharacter{
    private long movieCharID;
    private long movieID;
    private long personID;
    private String character;
    private int position;
    private String alias;

    private DbManager dbManager;

    public long getMovieCharID() {
        return movieCharID;
    }

    public long getMovieId() {
        return movieID;
    }
    public void setMovieId(long movieID) {
        this.movieID = movieID;
    }

    public long getPlayerId() {
        return personID;
    }
    public void setPlayerId(long charID) {
        this.personID = charID;
    }

    public String getCharacter() {
        return character;
    }
    public void setCharacter(String name) {
        character = name;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }

// Verknüft die FilmID mit der PersonID und dem Character, sowie der positon und alias zusammen

    public int insert(String movieTitle, String personName, String character, int position, String alias) throws SQLException {
        int cnt = 0;
        String getMovieID = "SELECT movieID FROM movie WHERE title = ? ";
        String getPersonID = "SELECT personID FROM person WHERE name = ?";
        String insert_query = "INSERT INTO movieCharacter VALUES(nextval('seq_movieCharacter'), ?, ?, ?, ?, ?);";
        String get_id_query = "SELECT currval('seq_movieCharacter')";
        PreparedStatement stmt = null;
        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();
        stmt = dbManager.getConnection().prepareStatement(getMovieID);
        stmt.setString(1, movieTitle);
        movieID = dbManager.getID(stmt);
        stmt = dbManager.getConnection().prepareStatement(getPersonID);
        stmt.setString(1, personName);
        personID = dbManager.getID(stmt);
        // Einfügen der Movie-Daten in DB:
        stmt = dbManager.getConnection().prepareStatement(insert_query);
        stmt.setLong(1, movieID);
        stmt.setLong(2, personID);
        stmt.setString(3, character);
        stmt.setInt(4, position);
        stmt.setString(5, alias);
        dbManager.executeInsert(stmt);
        stmt =  dbManager.getConnection().prepareStatement(get_id_query);
        movieCharID = dbManager.getID(stmt);
        System.out.println("Eingefügt in MovieCharacter:\nid: " + movieCharID + "\nmovieID: " + movieID + "\ncharID: " + personID + "\ncharacter: " + character + "\nposition: " + position + "\nalias: " + alias+"\n");
        return cnt;
    }
}