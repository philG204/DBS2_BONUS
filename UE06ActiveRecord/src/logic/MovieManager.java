package logic;

import java.util.ArrayList;
import java.util.List;

import Entities.Genre;
import Entities.Movie;
import Factories.MovieFactory;
import logic.dto.CharacterDTO;
import logic.dto.MovieDTO;

public class MovieManager {

	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt.
	 * Wenn der String leer ist, sollen alle Filme zurueckgegeben werden.
	 * Der Suchstring soll ohne Ruecksicht auf Gross-/Kleinschreibung verarbeitet werden.
	 * @param search Suchstring. 
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception {
        // Holt eine (Array)List mit Movie-Objekten.
        List<Movie> movieList =  MovieFactory.MovieFindByTitle(search);
        MovieDTO movieDTO;
        List<MovieDTO> movieDTOList = new ArrayList<>();

        // Konvertiert Movie-Objekte in movieDTO-Objekte um.
        for (Movie row : movieList) {
            movieDTO = createMovieDTO(row);
            movieDTOList.add(movieDTO);
        }

		return movieDTOList;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movieDTO Film-Objekt mit Genres und Charakteren.
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {		
		/* TODO */
        Movie m = new Movie();
        m.setTitle(movieDTO.getTitle());
        m.setYear(movieDTO.getYear());
        m.setType(movieDTO.getType());

        // Insert/ Add:
        if(movieDTO.getId() == null){
            m.insert();
            movieDTO.setId((int)m.getMovieId());

            // Update/ Edit:
        } else {
            m.SetId(movieDTO.getId());
            m.update();
            movieDTO.setTitle(m.getTitle());
            movieDTO.setYear(m.getYear());
            movieDTO.setType(m.getType());
        }
	}

	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte geloescht,
	 * d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * @param movieId id des zu löschenden Films
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public void deleteMovie(long movieId) throws Exception {
		Movie.delete(movieId);
	}

	/**
	 * Ermittelt alle Daten zu einem Movie (d.h. auch Genres und Charaktere) und
	 * trägt diese Daten in einem MovieDTO-Objekt ein.
	 * @param movieId ID des Films der eingelesen wird.
	 * @return MovieDTO-Objekt mit allen Informationen zu dem Film
	 * @throws Exception Z.B. bei Datenbank-Fehlern oder falls der Movie nicht existiert.
	 */
	public MovieDTO getMovie(long movieId) throws Exception {
        Movie m = MovieFactory.MovieFindById(movieId);
        MovieDTO mdto =  createMovieDTO(m);

        //TODO: Genres und Character zu einem Film auslesen

        return mdto;
	}

    /**
     * Erstellt aus gegebener movieId ein MovieDTO-Objekt.
     * @param m
     * @return
     * @throws Exception
     */
    private MovieDTO createMovieDTO(Movie m) throws Exception {
        MovieDTO mdto = new MovieDTO();
        mdto.setId((int)m.getMovieId());
        mdto.setTitle(m.getTitle());
        mdto.setYear(m.getYear());
        mdto.setType(m.getType());
        return mdto;
    }
}