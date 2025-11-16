package Entities;

import db.DbActions;
import db.DbManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieGenre implements DbActions {
    private long movieID;
    private long genreID;
    private DbManager dbManager;

    public long getMovieId() { return movieID; }
    public void setMovieId(long movieId) { this.movieID = movieId; }
    public long getGenreId() { return genreID; }
    public void setGenreId(long genreId) { this.genreID = genreId; }


    public int insert() throws SQLException {
        String insert_query = "INSERT INTO MovieGenre VALUES (?, ?);";
        int status = 0;
        PreparedStatement stmt;
        dbManager = DbManager.getInstance();

        stmt = dbManager.getConnection().prepareStatement(insert_query);
        stmt.setLong(1, genreID);
        stmt.setLong(2, movieID);
        status = dbManager.executeInsert(stmt);

        if (status == 0) {
            System.out.println("Eingefügt in MovieGenre:\nmovieID: "+movieID+"\ngenreID: "+genreID);
        }

        return status;
    }
}
