package Entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.*;

public class Person {
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

        System.out.println("Eingefügt in Person:\nPpersonID: " + this.personID + "\nname: "+this.name);
        return cnt;
    }
    /*
    liefert die ID als ResultSet zurück
    akzeptiert einen Namen als Eingabe
    wirft keine eigene SQLException ruft aber Methoden auf, welche Exceptions werfen
     */
    public ResultSet getIdAsResultSet(String name) throws SQLException {

        PreparedStatement stmt = DbManager.getConnection().prepareStatement("SELECT ID FROM Person WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = DbManager.getInstance().executeSelect(stmt);
        return rs;
    }
    /*
    gibt mehrere Namen in einem ResultSet zurück akzeptiert einen Namen
     */
    public ResultSet getNameListAsResultSet (String name) throws SQLException {
        PreparedStatement stmt = DbManager.getConnection().prepareStatement("SELECT name FROM Person WHERE name = LIKE %?%");
        stmt.setString(1, name);
        ResultSet rs = DbManager.getInstance().executeSelect(stmt);
        return rs;
    }
    public ResultSet getNameAsResultSet(String name) throws SQLException {
        PreparedStatement stmt = DbManager.getConnection().prepareStatement("SELECT name FROM Person WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = DbManager.getInstance().executeSelect(stmt);
        return rs;
    }
}
