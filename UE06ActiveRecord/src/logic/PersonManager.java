package logic;

import Entities.Person;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonManager {

	/**
	 * Liefert eine Liste aller Personen, deren Name den Suchstring enthaelt.
	 * @param name Suchstring
	 * @return Liste mit passenden Personennamen, die in der Datenbank eingetragen sind.
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public List<String> getPersonList(String name) throws Exception {
		List <String> list = new ArrayList<String>();
        Person person = new Person();
        ResultSet rs = person.getNameListAsResultSet(name);
        if(!rs.next()) {
            return null;
        }
        while (rs.next()) {
            list.add(rs.getString(1));
        }
		return list;
	}

	/**
	 * Liefert die ID einer Person, deren Name genau name ist. Wenn die Person nicht existiert,
	 * wird eine Exception geworfen.
	 * @param name Exakter Name der Person
	 * @return ID der Person
	 * @throws Exception Beschreibt evtl. aufgetretenen Fehler
	 */
	public int getPerson(String name) throws Exception {
        Person p = new Person();
        ResultSet rs = p.getIdAsResultSet(name);
        int IdFromPerson = 0;
        if(!rs.next()) {
            return -1;
        }
        while(rs.next()) {
            IdFromPerson = rs.getInt(1);
        }
		return IdFromPerson;
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
