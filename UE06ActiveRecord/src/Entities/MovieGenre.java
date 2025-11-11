package Entities;

public class MovieGenre {
    private long movieId;
    private long genreId;

    public long GetMovieId() { return movieId; }
    public void SetMovieId(long movieId) { this.movieId = movieId; }
    public long GetGenreId() { return genreId; }
    public void SetGenreId(long genreId) { this.genreId = genreId; }
}
