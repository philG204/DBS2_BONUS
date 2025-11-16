package Entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.*;

public class Person implements DbActions {
    private long personID;
    private String name;

    private DbManager dbManager;

    public long getPersonId() { return personID; }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public int insert() throws SQLException {
        String insert_person = "INSERT INTO Person VALUES (nextval('seq_person'), ?);";
        String id_select_query = "SELECT currval('seq_person');";
        PreparedStatement stmt = null, stmt2 = null;
        int cnt = 0;
        dbManager =  DbManager.getInstance();

        stmt = dbManager.getConnection().prepareStatement(insert_person);
        stmt.setString(1, name);
        dbManager.executeInsert(stmt);

        stmt2 = dbManager.getConnection().prepareStatement(id_select_query);
        this.personID = dbManager.getID(stmt2);

        System.out.println("Eingefügt in Person:\nPpersonID: " + this.personID + "\nname: "+this.name);
        return cnt;
    }
}
