package logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Entities.Genre;
import Factories.GenreFactory;
import db.DbManager;

public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception error describing e.g. the database problem
	 */
	public List<String> getGenres() throws Exception {
        try{
            List<String> genres = new ArrayList<String>();
            List<Map<String, Object>>  allGenres = GenreFactory.getALlGenres();
            DbManager.getConnection().commit();

            for (Map<String, Object> row : allGenres) {
                System.out.println("ID = " + row.get("id"));
                System.out.println("Name = " + row.get("genre"));
                genres.add((String) row.get("genre"));
            }
            return genres;
        } catch (SQLException e) {
            System.out.println("Fehler");
            DbManager.getConnection().rollback();
            throw new RuntimeException(e);
        }
	}
}
