package Entities;

import db.DbActions;
import db.DbManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieCharacter implements DbActions {
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


    public int insert() throws SQLException {
        int status = 0;
        String insert_query = "INSERT INTO movieCharacter VALUES(nextval('seq_movieCharacter'), ?, ?, ?, ?, ?);";
        String get_id_query = "SELECT currval('seq_movieCharacter');";
        PreparedStatement stmt = null, stmt2 = null;
        dbManager =  DbManager.getInstance();

        // Einfügen der Movie-Daten in DB:
       stmt = DbManager.getConnection().prepareStatement(insert_query);
       stmt.setLong(1, movieID);
       stmt.setLong(2, personID);
       stmt.setString(3, character);
       stmt.setInt(4, position);
       stmt.setString(5, alias);
       status = dbManager.executeInsert(stmt);


        // Holen der movieID:
        stmt2 = DbManager.getConnection().prepareStatement(get_id_query);
        this.movieCharID = dbManager.getID(stmt2);

        if(status == 0){
            System.out.println("Eingefügt in MovieCharacter:\nid: " + movieCharID + "\nmovieID: " + movieID + "\ncharID: " + personID + "\ncharacter: " + character + "\nposition: " + position + "\nalias: " + alias+"\n");
        }
        return status;
    }
    public void delete(Long movieID) throws SQLException {
        String delete_movie = "DELETE FROM moviecharacter WHERE movieID = ?";
        PreparedStatement stmt = null;

        int status = 0;

        dbManager.getInstance();

        try {
            stmt = DbManager.getConnection().prepareStatement(delete_movie);
            stmt.setLong(1, movieID);
            status = stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }
    }
}