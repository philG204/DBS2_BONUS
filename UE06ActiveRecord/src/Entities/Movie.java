package Entities;

import java.util.Date;
import db.DbActions;

public class Movie implements DbActions {
    private long id;
    private String title;
    private int year;
    private char type;

    public long GetId(){
        return id;
    }
    public void SetId(long id){ this.id = id; }
    public String GetTitle(){
        return title;
    }
    public void SetTitle(String title){
        this.title = title;
    }
    public int GetYear(){
        return year;
    }
    public void SetYear(int year){ this.year = year; }
    public char GetType(){
        return type;
    }
    public void SetType(char type){
        this.type = type;
    }

    public void insert(){

    }

    public boolean update(){
        return false;
    }

    public boolean delete(){
        return false;
    }
}
