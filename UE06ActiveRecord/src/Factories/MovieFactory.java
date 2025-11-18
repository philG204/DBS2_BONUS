package Factories;

import db.DbManager;
import Entities.Movie;

import java.sql.*;
import java.util.List;

public class MovieFactory {

    private static DbManager conn;

    /**
     * Looks for movie by ID given in the Parameters
     * @param id
     * @return Movie
     * @throws SQLException
     */
    public static Movie MovieFindById(long id) throws SQLException{
        String movieId_select = "SELECT title, year, type FROM Movie WHERE movieID = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Movie movie = null;

        conn = DbManager.getInstance();
        conn.connectToDB();

        stmt = conn.getConnection().prepareStatement(movieId_select);
        stmt.setLong(1, id);
        rs = conn.executeSelect(stmt);

        while(rs.next()){
            movie = new Movie();
            movie.SetId(rs.getLong(0));
            movie.setTitle(rs.getString(1));
            movie.setYear(rs.getInt(2));
            movie.setType(rs.getString(3));
        }
        return movie;

    }

    /**
     * Looks for movies by Title given in the Parameters
     * @param title
     * @return
     * @throws SQLException
     */
    public static List<Movie> MovieFindByTitle(String title) throws SQLException{
        String movieId_select = "SELECT movieID, year, type FROM Movie WHERE title = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movie> movies = null;

        conn = DbManager.getInstance();
        conn.connectToDB();

        stmt = conn.getConnection().prepareStatement(movieId_select);
        stmt.setString(1, title);
        rs = conn.executeSelect(stmt);

        while(rs.next()){
            Movie movie = new Movie();
            movie.SetId(rs.getLong(0));
            movie.setTitle(rs.getString(1));
            movie.setYear(rs.getInt(2));
            movie.setType(rs.getString(3));
            movies.add(movie);
        }
        return movies;
    }
}
