package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.*;

public class Movie implements DbActions {
    private long movieID;
    private String title;
    private int year;
    private String type;

    private DbManager dbManager;

    public Movie (String title, int year, String type) {
        this.title = title;
        this.year = year;
        this.type = type;
    }

    public long getMovieID(){ return movieID;}


    public String getTitle(){ return title; }
    public void setTitle(String title){this.title = title; }

    public int getYear(){ return year;}
    public void setYear(int year){ this.year = year; }

    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }


    public void SetId(long id){ this.movieID = id; }
//    public String GetTitle(){
//        return title;
//    }


    public int insert() throws SQLException{
        String insert_query = "INSERT INTO Movie VALUES ( nextval('seq_movie'), ?, ?, ? );";
        String id_select_query = "SELECT currval('seq_movie');";

        int cnt=0;

        PreparedStatement stmt = null, stmt2 = null;
        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();

        stmt = dbManager.getConnection().prepareStatement(insert_query);
        stmt.setString(1, title);
        stmt.setInt(2, year);
        stmt.setString(3, type);
        dbManager.executeInsert(stmt);

        stmt2 = dbManager.getConnection().prepareStatement(id_select_query);
        this.movieID = dbManager.getID(stmt2);

        System.out.println("Eingefügt in MOVIE:\nid: "+ movieID +"\ntitle: "+title+"\nyear: "+year+"\ntype: "+type);

        return cnt;
    }

    public int update(){
        String update_movie = "UPDATE Movie SET title=?, year=?, type=? WHERE movieID=?";

        PreparedStatement stmt = null;

        int cnt = 0;

        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();

        try {
            stmt = dbManager.getConnection().prepareStatement(update_movie);
            stmt.setString(2, title);
            stmt.setInt(3, year);
            stmt.setString(4, type);
            stmt.setLong(1, movieID);
            cnt = stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }
        return cnt;
    }

    public int delete(){
        String delete_movie = "DELETE Movie, movieCharacter, movieGenre FROM Movie INNER JOIN movieCharacter FROM Movie INNER JOIN movieGenre WHERE Movie.movieID=movieCharacter.movieID AND Movie.movieID=movieGenre.movieID AND Movie.movieID=?";

        PreparedStatement stmt = null;

        int cnt = 0;

        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();

        try {
            stmt = dbManager.getConnection().prepareStatement(delete_movie);
            stmt.setLong(1, movieID);
            cnt = stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }
        return cnt;
    }
}