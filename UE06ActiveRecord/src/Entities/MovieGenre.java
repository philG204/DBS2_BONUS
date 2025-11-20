package Entities;

import db.DbActions;
import db.DbManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieGenre {
    private long movieID;
    private long genreID;
    private DbManager dbManager;

    public long getMovieId() { return movieID; }
    public void setMovieId(long movieId) { this.movieID = movieId; }
    public long getGenreId() { return genreID; }
    public void setGenreId(long genreId) { this.genreID = genreId; }

    /*
    fügt einem Film ein Genre hinzu
    holt sich aus den Tabellen Movie und Genre die IDs
    fügt beides dann der Tabelle MovieGenre hinzu
     */

    public int insert(String movieTitle, String genre) throws Exception {
        String getGenreID = "SELECT genreid FROM genre WHERE genre = ?";
        String getMovieID = "SELECT movieid FROM movie WHERE title = ?";
        String insert_query = "INSERT INTO MovieGenre VALUES (?, ?);";
        ResultSet rs = null;
        int status = 0;
        PreparedStatement stmt;
        dbManager = DbManager.getInstance();
        stmt = dbManager.getConnection().prepareStatement(getGenreID);
        stmt.setString(1, genre);
        rs = dbManager.getInstance().executeSelect(stmt);
        if(rs.wasNull()) {
            throw new Exception("Fehler: Genre existiert nicht");
        }
            rs.next();
            genreID = rs.getLong(1);
            System.out.println(genreID);

        stmt = dbManager.getConnection().prepareStatement(getMovieID);
        stmt.setString(1, movieTitle);
        rs = DbManager.getInstance().executeSelect(stmt);
        if(rs.wasNull()) {
            throw new Exception("Fehler: Film existiert nicht");
        }
        rs.next();
            movieID = rs.getLong(1);
        stmt = dbManager.getConnection().prepareStatement(insert_query);
        stmt.setLong(1, genreID);
        stmt.setLong(2, movieID);
        status = dbManager.executeInsert(stmt);
        if (status == 0) {
            System.out.println("Eingefügt in MovieGenre:\nmovieID: "+movieID+"\ngenreID: "+genreID);
        }
        if(status != 0) {
            //  vthrow new Exception("Fehler beim Einfügen: "+status);
        }
        return status;
    }
}
