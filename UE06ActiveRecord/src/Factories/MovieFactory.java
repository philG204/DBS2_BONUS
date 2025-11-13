package Factories;

import db.DbManager;
import Entities.Movie;
import db.DbCredentials;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MovieFactory {

    private static DbManager conn;

    public static Movie MovieFindById(long id){
        String movieId_select = "SELECT ";
        ResultSet rs = null;

        conn.connectToDB();
        //PreparedStatement db = conn.getConnection();
        return null;

    }

    public static List<Movie> MovieFindByTitle(String title){
        return null;
    }
}
