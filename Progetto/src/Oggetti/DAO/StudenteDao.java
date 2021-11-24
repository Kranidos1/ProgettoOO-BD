package Oggetti.DAO;

import java.sql.Connection;
import java.util.Vector;

import Oggetti.Studente;

public interface StudenteDao {
	
	public void inserimento(Connection connection ,Studente studente);
	public Vector[][] ricercaStudenteByName(Connection connection ,String name);
	public Vector[][] ricercaStudenteByCognome(Connection connection ,String cognome);
	public Vector[][] ricercaStudenteByCf(Connection connection ,String cf);
	public Vector[][] ricercaStudenteByData(Connection connection ,String data);
	
}
