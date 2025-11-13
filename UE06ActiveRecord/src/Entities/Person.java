package Entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.*;

public class Person implements DbActions {
    private long Id;
    private String name;

    private DbManager dbManager;

    public long GetId() { return Id; }
    public void SetId(long Id) { this.Id = Id; }
    public String GetName(){
        return name;
    }
    public void SetName(String name){
        this.name = name;
    }

    @Override
    public int insert() {
        String insert_person = "INSERT INTO Person VALUES (nextval('seq_person'), ?);";
        PreparedStatement stmt = null;
        int cnt = 0;

        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();

        try {
            stmt = dbManager.getConnection().prepareStatement(insert_person);
            stmt.setString(1, name);
            cnt = stmt.executeUpdate();
        } catch(SQLException e){
            System.err.println("Fehler beim einfügen:\n"+e.getMessage());
        }
        return cnt;
    }
}
