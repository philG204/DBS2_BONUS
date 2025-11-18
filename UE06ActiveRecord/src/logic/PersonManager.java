package logic;

import Entities.Person;

import java.sql.ResultSet;
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
		List <String> persons = new ArrayList<String>();
        List<Map<String, Object>> allPersons = Person.getPersons(name);
        //if(allPersons != null) {
        //    return null;
        //}
        for (Map<String, Object> row : allPersons) {
            System.out.println("ID = " + row.get("id"));
            System.out.println("Name = " + row.get("genre"));
            persons.add((String) row.get("name"));
        }
		return persons;
	}

	/**
	 * Liefert die ID einer Person, deren Name genau name ist. Wenn die Person nicht existiert,
	 * wird eine Exception geworfen.
	 * @param name Exakter Name der Person
	 * @return ID der Person
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public int getPerson(String name) throws Exception {
        List<Map<String, Object>> person = Person.getPerson(name);

        if(person == null) {
            Exception Exception = new Exception();
            throw Exception;
        }
        Map<String, Object> id = person.get(0);
		return Integer.parseInt(id.get("personid").toString());
	}
    //eigene Methode hat nichts mit der Abgabe zu tun
    public void getNameFromPerson(String name) throws Exception {
        Person p = new Person();
        ResultSet rs = p.getNameAsResultSet(name);
        if(!rs.next()) {
            return;
        }
        while(rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
}
