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

        String title = "";
        int year = 0;
        String type = "0";

        conn = DbManager.getInstance();
        conn.connectToDB();
        stmt = conn.getConnection().prepareStatement(movieId_select);
        stmt.setString(1, title);
        stmt.setInt(2, year);
        stmt.setString(3, type);
        movie = new Movie(title, year, type);
        movie.SetId(id);
        return movie;

    }

    public static List<Movie> MovieFindByTitle(String title){
        return null;
    }
}
