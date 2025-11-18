package logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entities.Genre;

public class GenreManager {

	/**
	 * Ermittelt eine vollstaendige Liste aller in der Datenbank abgelegten Genres
	 * Die Genres werden alphabetisch sortiert zurueckgeliefert.
	 * @return Alle Genre-Namen als String-Liste
	 * @throws Exception error describing e.g. the database problem
	 */
	public List<String> getGenres() throws Exception {
		List<String> genres = new ArrayList<String>();
        ResultSet allGenres = Genre.getALlGenres();

        while (allGenres.next()){
            genres.add(allGenres.getString(0));
        }
		return genres;
	}


}
