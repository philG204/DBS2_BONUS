package logic;

import Entities.Person;
import Factories.PersonFactory;
import db.DbManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param name Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public List<String> getPersonList(String name) throws Exception {

        try{
            List <String> persons = new ArrayList<String>();
            List<Map<String, Object>> allPersons = PersonFactory.getPersons(name);
            DbManager.getConnection().commit();

            for (Map<String, Object> row : allPersons) {
                System.out.println("ID = " + row.get("id"));
                System.out.println("Name = " + row.get("genre"));
                persons.add((String) row.get("name"));
            }
            return persons;
        } catch(SQLException e){
            System.out.println("Fehler: " + e.getStackTrace());
            DbManager.getConnection().rollback();
            throw new RuntimeException(e);
        }
	}

	/**
	 * Liefert die ID einer Person, deren Name genau name ist. Wenn die Person nicht existiert,
	 * wird eine Exception geworfen.
	 * @param name Exakter Name der Person
	 * @return ID der Person
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public int getPerson(String name) throws Exception {
        try {
            List<Map<String, Object>> person = PersonFactory.getPerson(name);
            DbManager.getConnection().commit();

            if(person == null) {
                Exception Exception = new Exception();
                throw Exception;
            }
            Map<String, Object> id = person.get(0);
            return Integer.parseInt(id.get("personid").toString());
        } catch (SQLException e){
            System.out.println("Fehler: " + e.getStackTrace());
            DbManager.getConnection().rollback();
            throw new RuntimeException(e);
        }
	}
}
