package Entities;

import db.DbActions;

public class Genre implements DbActions{
    private String genre;

    public String GetGenre(){
        return genre;
    }
    public void SetGenre(String name){
        genre = name;
    }

    public int insert(){
        return 0;
    }
}
