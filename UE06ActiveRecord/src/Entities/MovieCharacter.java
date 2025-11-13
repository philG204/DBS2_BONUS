package Entities;

import db.DbActions;
import db.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieCharacter implements DbActions {
    private long movieCharID;
    private long movieId;
    private long charId;
    private String character;
    private int position;
    private String alias;

    private DbManager dbManager;

    public MovieCharacter(long movieId, long charId, String character, int position, String alias) {
        this.movieId = movieId;
        this.charId = charId;
        this.character = character;
        this.position = position;
        this.alias = alias;
    }

    public long getMovieCharID() {
        return movieCharID;
    }

    public void setMovieCharID(long movieCharID) {
        this.movieCharID = movieCharID;
    }

    public long setMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getCharId() {
        return charId;
    }

    public void setCharId(long charId) {
        this.charId = charId;
    }

    public String getName() {
        return character;
    }

    public void setName(String name) {
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


    public int insert() throws SQLException {
        int cnt = 0;
        String insert_query = "INSERT INTO MovieCharacter VALUES(nextval('seq_movieCharacter'), ?, ?, ?, ?, ?);";
        String id_select_query = "SELECT movieCharID FROM MovieCharacter WHERE movieID = ?;";
        PreparedStatement stmt = null, stmt2 = null;
        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();

        // Einfügen der Movie-Daten in DB:
       stmt = dbManager.getConnection().prepareStatement(insert_query);
       stmt.setLong(1, movieId);
       stmt.setLong(2, charId);
       stmt.setString(3, character);
       stmt.setInt(4, position);
       stmt.setString(5, alias);
       dbManager.executeInsert(stmt);


        // Holen der movieID:
        stmt2 = dbManager.getConnection().prepareStatement(id_select_query);
        stmt2.setLong(1, movieId);
        dbManager.getID(stmt2);
        System.out.println("Eingefügt in MovieCharacter:\nid: " + movieCharID + "\nmovieID: " + movieId + "\ncharID: " + charId + "\ncharacter: " + character + "\nposition: " + position + "\nalias: " + alias);

        return cnt;
    }
}