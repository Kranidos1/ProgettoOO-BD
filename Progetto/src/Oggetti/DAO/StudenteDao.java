package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

import Oggetti.Studente;
import Oggetti.DAO.Exceptions.DaoExceptions;

public interface StudenteDao {
	
	public void inserimento(Connection connection ,Studente studente) ;
	public Vector[][] ricercaStudenteByName(Connection connection ,String name);
	public Vector[][] ricercaStudenteByCognome(Connection connection ,String cognome);
	public Vector[][] ricercaStudenteByCf(Connection connection ,String cf);
	public Vector[][] ricercaStudenteByDataIscrizione(Connection connection ,String data);
	public List<String>[] ricercaStudenteByNomeECognome(Connection connection ,String nome ,String cognome);
	public List<String>[] ricercaStudenteByNomeECf(Connection connection ,String nome ,String cf);
	public List<String>[] ricercaStudenteByCognomeECf(Connection connection ,String Cognome ,String cf);
	public List<String>[] ricercaStudenteByCognomeDataECf(Connection connection ,String cognome ,String data ,String cf);
	public List<String>[] ricercaStudenteByNomeDataECf(Connection connection ,String nome ,String data ,String cf);
	public List<String>[] ricercaStudenteByNomeDataECognome(Connection connection ,String nome ,String data ,String cognome);
	public List<String>[] ricercaStudenteByNomeCfECognome(Connection connection ,String nome ,String cf ,String cognome);
	
	
}
