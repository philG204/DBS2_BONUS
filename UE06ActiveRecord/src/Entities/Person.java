package Entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.*;

public class Person implements DbActions {
    private long personID;
    private String name;

    private DbManager dbManager;

    public Person(String name) {
        this.name = name;
    }

    public long GetId() { return personID; }
    public void SetId(long Id) { this.personID = Id; }
    public String GetName(){
        return name;
    }
    public void SetName(String name){
        this.name = name;
    }

    @Override
    public int insert() throws SQLException {
        String insert_person = "INSERT INTO Person VALUES (nextval('seq_person'), ?);";
        String id_select_query = "SELECT currval('seq_person');";
        PreparedStatement stmt = null, stmt2 = null;
        int cnt = 0;

        dbManager =  DbManager.getInstance();
        dbManager.connectToDB();

       stmt = dbManager.getConnection().prepareStatement(insert_person);
       stmt.setString(1, name);
       dbManager.executeInsert(stmt);

       stmt2 = dbManager.getConnection().prepareStatement(id_select_query);
       this.personID = dbManager.getID(stmt2);

       System.out.println("Eingefügt in PERSON:\nPpersonID: " + this.personID + "\nname: "+this.name);
       return cnt;
    }
}
