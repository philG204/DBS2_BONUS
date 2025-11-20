package Factories;

import Entities.Movie;
import db.DbManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GenreFactory {
    /**
     *
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>>  getALlGenres() throws SQLException{
        String allGenres_sql = "SELECT genre FROM Genre";
        PreparedStatement stmt = null;
        List<Map<String, Object>> rs = null;
        DbManager dbManager1 = null;

        try{
            dbManager1 = DbManager.getInstance();
            stmt = DbManager.getConnection().prepareStatement(allGenres_sql);
            rs = dbManager1.executeSelect(stmt);
            DbManager.getConnection().commit();
            return rs;
        } catch(SQLException e) {
            System.out.println("Fehler beim Einfügen.\nQuery: "+stmt.toString());
            DbManager.getConnection().rollback();
            throw new RuntimeException(e);
        }
    }
}
