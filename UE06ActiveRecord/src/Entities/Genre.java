package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.*;
import db.DbCredentials;

public class Genre implements DbActions {
    private String genre;
    private DbManager dbManager;
    private long genreID;
    private DbCredentials dbCredentials = new DbCredentials();

    public void setGenreID(long genreID) {
        this.genreID = genreID;
    }

    public long getGenreID() {
        return this.genreID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int insert() {
        int num = 0;
        PreparedStatement stmt = null;
        String getSequence = "SELECT nextval('genreid')";
        String query = "INSERT INTO Genre VALUES ( ?, ? );";
        long sequence = 0;
        dbManager = new DbManager(dbCredentials.getUsername(), dbCredentials.getPassword(), dbCredentials.getUrl());
        dbManager.connectToDB();
        try {
            stmt = dbManager.getConnection().prepareStatement(getSequence);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sequence = rs.getLong("genreID");
            }
            stmt.setLong(1, sequence);
            stmt.setString( 2,genre);
            num = stmt.executeUpdate();
            System.out.println(num + " Zeile(n) geändert.");
            setGenreID(sequence);

        } catch (SQLException e) {
            System.err.println("Fehler beim einfügen:\n" + e.getMessage());
        }
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Schließen des Statements: " + e.getMessage());
        }
        return num;
    }
}