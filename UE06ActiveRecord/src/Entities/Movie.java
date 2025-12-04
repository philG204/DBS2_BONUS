package Entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.*;

public class Movie implements DbActions {
    private long movieID;
    private String title;
    private int year;
    private String type;

    private DbManager dbManager;

    public long getMovieId(){ return movieID;}

    public String getTitle(){ return title; }
    public void setTitle(String title){this.title = title; }

    public int getYear(){ return year;}
    public void setYear(int year){ this.year = year; }

    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }


    public void SetId(long id){ this.movieID = id; }

    public int insert() throws SQLException{
        String insert_query = "INSERT INTO Movie VALUES ( nextval('seq_movie'), ?, ?, ? );";
        String id_select_query = "SELECT currval('seq_movie');";

        int status=0;

        PreparedStatement stmt = null, stmt2 = null;
        dbManager =  DbManager.getInstance();

        stmt = DbManager.getConnection().prepareStatement(insert_query);
        stmt.setString(1, title);
        stmt.setInt(2, year);
        stmt.setString(3, type);
        status = dbManager.executeInsert(stmt);

        stmt2 = DbManager.getConnection().prepareStatement(id_select_query);
        this.movieID = dbManager.getID(stmt2);

        if(status == 0) {
            System.out.println("Eingefügt in Movie:\nid: " + movieID + "\ntitle: " + title + "\nyear: " + year + "\ntype: " + type);
        }
        return status;
    }

    public int update() throws SQLException {
        String update_movie = "UPDATE Movie SET title=?, year=?, type=? WHERE movieID=?";

        PreparedStatement stmt = null;

        int status = 0;

        dbManager =  DbManager.getInstance();

        try {
            stmt = DbManager.getConnection().prepareStatement(update_movie);
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.setString(3, type);
            stmt.setLong(4, movieID);
            status = stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }
        return status;
    }

    public int delete(long movieId) throws SQLException {
        MovieCharacter m = new MovieCharacter();
        m.delete(movieId);
        String delete_movie = "DELETE FROM Movie WHERE movieID = ?";
        PreparedStatement stmt = null;

        int status = 0;

        dbManager.getInstance();

        try {
            stmt = DbManager.getConnection().prepareStatement(delete_movie);
            stmt.setLong(1, movieId);
            status = stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }
        return status;
    }
}