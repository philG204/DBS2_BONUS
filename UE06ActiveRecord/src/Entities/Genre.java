package Entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.*;

public class Genre {
    public Genre(String genre){
        this.genre = genre;
    }
    private String genre;
    private long genreID;
    private DbManager dbManager;

    public long getGenreId() {
        return this.genreID;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int insert() throws SQLException {
        int status = 0;
        PreparedStatement stmt = null, stmt2 = null;
        String getSequence = "SELECT currval('seq_genre')";
        String insert_query = "INSERT INTO Genre VALUES (nextval('seq_genre'), ?);";
        dbManager = DbManager.getInstance();
        dbManager.connectToDB();
        stmt = dbManager.getConnection().prepareStatement(insert_query);
        stmt.setString(1, genre);
        status = dbManager.executeInsert(stmt);

        stmt2 = dbManager.getConnection().prepareStatement(getSequence);
        this.genreID = dbManager.getID(stmt2);

        if(status == 0) {
            System.out.println("Eingefügt in Genre:\ngenreID: "+genreID+"\ngenre: "+genre);
        }
        return status;
    }
}