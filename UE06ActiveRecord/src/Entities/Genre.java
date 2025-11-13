package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.*;

public class Genre implements DbActions {
    private String genre;
    private long genreID;

    public void setGenreID(long genreID) {
        this.genreID = genreID;
    }

    public long getGenreID() {
        return this.genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int insert() throws SQLException {
        int num = 0;
        int position1 = 1;
        int position2 = 2;
        PreparedStatement stmt = null;
        String getSequence = "SELECT currval('genreid')";
        String query = "INSERT INTO Genre VALUES ( nextval('genreid'), ? );";
        long sequence = 0;
        DbManager dbManager = DbManager.getInstance();
        dbManager.connectToDB();
        stmt = dbManager.getConnection().prepareStatement(query);
        sequence = dbManager.getID(stmt);
        dbManager.getConnection().prepareStatement(getSequence);
        stmt.setLong(position1,sequence);
        stmt.setString(position2,getGenre());
        dbManager.executeInsert(stmt);
        return num;
    }
}