package Entities;

import db.DbActions;

public class MovieCharacter implements DbActions {
    private long movieId;
    private long charId;
    private String character;
    private int position;
    private String alias;

    public long GetMovieId() { return movieId; }
    public void SetMovieId(long movieId){this.movieId = movieId; }
    public long GetCharId() { return charId; }
    public void SetCharId(long charId) { this.charId = charId; }
    public String GetName() { return character; }
    public void SetName(String name) { character = name; }
    public int GetPosition() { return position; }
    public void SetPosition(int position) { this.position = position; }
    public String GetAlias() { return alias; }
    public void SetAlias(String alias) { this.alias = alias; }


    public void insert() {
    }
}
