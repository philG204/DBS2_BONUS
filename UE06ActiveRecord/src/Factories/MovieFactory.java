package Factories;

import db.DbManager;
import Entities.Movie;

import java.sql.*;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> rs = null;
        Movie movie = null;

        conn = DbManager.getInstance();
        conn.connectToDB();

        stmt = conn.getConnection().prepareStatement(movieId_select);
        stmt.setLong(1, id);
        rs = conn.executeSelect(stmt);

        for (Map<String, Object> row : rs) {
            movie.SetId(Long.parseLong((String)row.get("movieID")));
            movie.setTitle((String) row.get("title"));
            movie.setYear(Integer.parseInt((String)row.get("year")));
            movie.setType((String)row.get("type"));
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
        List<Map<String, Object>> rs = null;
        List<Movie> movies = null;

        conn = DbManager.getInstance();
        conn.connectToDB();

        stmt = conn.getConnection().prepareStatement(movieId_select);
        stmt.setString(1, title);
        rs = conn.executeSelect(stmt);

        for (Map<String, Object> row : rs) {
            Movie movie = new Movie();
            movie.SetId(Long.parseLong((String)row.get("movieID")));
            movie.setTitle((String) row.get("title"));
            movie.setYear(Integer.parseInt((String)row.get("year")));
            movie.setType((String)row.get("type"));
            movies.add(movie);
        }
        return movies;
    }
}
