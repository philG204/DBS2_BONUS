package Entities;

import db.DbActions;
import db.DbCredentials;
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

    private DbCredentials dbCredentials = new DbCredentials();
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

    public long GetMovieId() {
        return movieId;
    }

    public void SetMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long GetCharId() {
        return charId;
    }

    public void SetCharId(long charId) {
        this.charId = charId;
    }

    public String GetName() {
        return character;
    }

    public void SetName(String name) {
        character = name;
    }

    public int GetPosition() {
        return position;
    }

    public void SetPosition(int position) {
        this.position = position;
    }

    public String GetAlias() {
        return alias;
    }

    public void SetAlias(String alias) {
        this.alias = alias;
    }


    public int insert() throws SQLException {
        int cnt = 0;
        String insert_query = "INSERT INTO MovieCharacter VALUES(nextval('seq_movieCharacter'), ?, ?, ?, ?, ?);";
        String id_select_query = "SELECT movieCharID FROM MovieCharacter WHERE movieID = ?;";

        PreparedStatement stmt = null;

        dbManager = new DbManager(dbCredentials.getUsername(), dbCredentials.getPassword(), dbCredentials.getUrl());
        dbManager.connectToDB();

        // Einfügen der Movie-Daten in DB:
        try {
            stmt = dbManager.getConnection().prepareStatement(insert_query);

            stmt.setLong(1, movieId);
            stmt.setLong(2, charId);
            stmt.setString(3, character);
            stmt.setInt(4, position);
            stmt.setString(5, alias);

            dbManager.executeInsert(stmt);

        } catch (SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }

        // Holen der movieID:
        try {
            stmt = dbManager.getConnection().prepareStatement(id_select_query);

            stmt.setLong(1, movieId);
            dbManager.getID(stmt);

            System.out.println("Eingefügt in MovieCharacter:\nid: " + movieCharID + "\nmovieID: " + movieId + "\ncharID: " + charId + "\ncharacter: " + character + "\nposition: " + position + "\nalias: " + alias);

        } catch (SQLException g) {
            System.out.println("Fehler beim auslesen von Movie.movieID:\n" + g.getMessage());
        }

        return cnt;
    }
}
