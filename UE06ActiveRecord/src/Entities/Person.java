package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
        int status = 0;
        dbManager =  DbManager.getInstance();

        stmt = DbManager.getConnection().prepareStatement(insert_person);
        stmt.setString(1, name);
        status = dbManager.executeInsert(stmt);

        stmt2 = DbManager.getConnection().prepareStatement(id_select_query);
        this.personID = dbManager.getID(stmt2);

        if(status==0) {
            System.out.println("Eingefügt in Person:\nPpersonID: " + this.personID + "\nname: " + this.name);
        }
        return status;
    }
}
