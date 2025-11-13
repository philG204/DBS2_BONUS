package Entities;

import db.DbActions;
import db.DbManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieCharacter implements DbActions {
    private long movieCharID;
    private long movieID;
    private long charID;
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
        return charID;
    }
    public void setPlayerId(long charID) {
        this.charID = charID;
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


    public int insert() throws SQLException {
        int cnt = 0;
        String insert_query = "INSERT INTO movieCharacter VALUES(nextval('seq_movieCharacter'), ?, ?, ?, ?, ?);";
        String get_id_query = "SELECT currval('seq_movieCharacter');";
        PreparedStatement stmt = null, stmt2 = null;
        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();
        // Einfügen der Movie-Daten in DB:
       stmt = dbManager.getConnection().prepareStatement(insert_query);
       stmt.setLong(1, movieID);
       stmt.setLong(2, charID);
       stmt.setString(3, character);
       stmt.setInt(4, position);
       stmt.setString(5, alias);
       dbManager.executeInsert(stmt);


        // Holen der movieID:
        stmt2 = dbManager.getConnection().prepareStatement(get_id_query);
        this.movieCharID = dbManager.getID(stmt2);

        System.out.println("Eingefügt in MovieCharacter:\nid: " + movieCharID + "\nmovieID: " + movieID + "\ncharID: " + charID + "\ncharacter: " + character + "\nposition: " + position + "\nalias: " + alias+"\n");
        return cnt;
    }
}