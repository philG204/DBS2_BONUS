package Factories;

import db.DbManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PersonFactory {

    /**
     * Returns a person with a part string
     * @param name
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> getPersons(String name) throws SQLException {
        PreparedStatement stmt = DbManager.getConnection().prepareStatement("SELECT name FROM Person WHERE name LIKE ?");
        stmt.setString(1, "%" + name + "%");
        List<Map<String, Object>> rs = DbManager.getInstance().executeSelect(stmt);
        return rs;
    }

    /**
     * Returns a person wich has the name that is in the parameter
     * @param name
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> getPerson(String name) throws SQLException {
        PreparedStatement stmt = DbManager.getConnection().prepareStatement("SELECT * FROM Person WHERE name = ?");
        stmt.setString(1, name);
        List<Map<String, Object>> rs = DbManager.getInstance().executeSelect(stmt);
        return rs;
    }
}
