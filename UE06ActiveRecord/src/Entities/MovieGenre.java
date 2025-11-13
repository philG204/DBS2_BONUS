package Entities;

import db.DbActions;

public class MovieGenre implements DbActions {
    private long movieId;
    private long genreId;

    public long GetMovieId() { return movieId; }
    public void SetMovieId(long movieId) { this.movieId = movieId; }
    public long GetGenreId() { return genreId; }
    public void SetGenreId(long genreId) { this.genreId = genreId; }


    public int insert(){
        return 0;
    }

    @Override
    public int update() {
        return 0;
    }

    @Override
    public int delete() {
        return 0;
    }
}
