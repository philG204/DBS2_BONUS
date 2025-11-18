package db;

import java.sql.SQLException;

public interface DbActions {
    public int insert(String movieTitle, String genre) throws SQLException;

}
