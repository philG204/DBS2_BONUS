package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.*;

public class Movie implements DbActions {
    private long id;
    private String title;
    private int year;
    private String type;

    private DbCredentials dbCredentials= new DbCredentials();
    private DbManager dbManager;

    public Movie (String title, int year, String type) {
        this.title = title;
        this.year = year;
        this.type = type;
    }

    public long getId(){ return id;}

    public String getTitle(){ return title; }
    public void setTitle(String title){this.title = title; }

    public int getYear(){ return year;}
    public void setYear(int year){ this.year = year; }

    public String getType(){ return type; }
    public void setType(String type){ this.type = type; }


//    public void SetId(long id){ this.id = id; }
//    public String GetTitle(){
//        return title;
//    }


    public int insert() {
        String insert_query = "INSERT INTO Movie VALUES ( nextval('seq_movie'), ?, ?, ? );",
                id_select_query = "SELECT movieid FROM Movie WHERE title = ?;";

        int cnt=0;

        PreparedStatement stmt = null, stmt2 = null;
        ResultSet rs = null;

        dbManager = new DbManager(dbCredentials.getUsername(), dbCredentials.getPassword(), dbCredentials.getUrl());
        dbManager.connectToDB();

        // Einfügen der Movie-Daten in DB:
        try{
            stmt = dbManager.getConnection().prepareStatement(insert_query);
            //stmt.setLong(1, id);
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.setString(3, type);
            cnt= stmt.executeUpdate();

            System.out.println(cnt+" Zeile hinzugefügt.");

            // Holen der movieID:
            try{
                stmt2 = dbManager.getConnection().prepareStatement(id_select_query);

                stmt2.setString(1, title);
                try{
                    rs = stmt2.executeQuery();
                    while(rs.next()){
                        id = rs.getLong(1);
                    }
                    System.out.println("Film wurde in MOVIE eingefügt:\nmovieID: "+id+"\ntitle: "+title+"\nyear: "+year+"\ntype: "+type);

                } catch(SQLException g){
                    System.out.println("Fehler beim auslesen von Movie.movieID:\n"+g.getMessage());
                }

            } catch (Exception f) {
                System.out.println("Fehler beim auslesen von Movie.title:\n"+f.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim einfügen:\n"+e.getMessage());
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Fehler beim schlie0en:\n"+e.getMessage());
                }
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Fehler beim schlie0en:\n"+e.getMessage());
                }
            }

            if (stmt2 != null) {
                try {
                    stmt2.close();
                } catch (SQLException e) {
                    System.err.println("Fehler beim schlie0en:\n"+e.getMessage());
                }
            }
        }
        return cnt;
    }

    public int update(){


        return 0;
    }

    public int delete(){
        return 0;
    }
}
