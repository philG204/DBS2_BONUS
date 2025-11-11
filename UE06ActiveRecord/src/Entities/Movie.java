package Entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import db.*;

public class Movie implements DbActions {
    private long id;
    private String title;
    private int year;
    private String type;

    private DbCredentials dbCredentials= new DbCredentials();
    private DbManager dbManager;

    public Movie (long id, String title, int year, String type) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.type = type;
    }

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
    public String GetType(){
        return type;
    }
    public void SetType(String type){
        this.type = type;
    }

    public void insert() {
        String query = "INSERT INTO Movie VALUES ( ?, ?, ?, ? );";
        dbManager = new DbManager(dbCredentials.getUsername(), dbCredentials.getPassword(), dbCredentials.getUrl());
        dbManager.connectToDB();
        try(PreparedStatement stmt = dbManager.getConnection().prepareStatement(query)){
            stmt.setLong(1, id);
            stmt.setString(2, title);
            stmt.setInt(3, year);
            stmt.setString(4, type);
            int num= stmt.executeUpdate();

            System.out.println(num+" Zeile(n) geändert.");

        } catch (SQLException e) {
            System.err.println("Fehler beim einfügen:\n"+e.getMessage());
        }
    }

    public boolean update(){
        return false;
    }

    public boolean delete(){
        return false;
    }
}
