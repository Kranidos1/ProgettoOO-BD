package Oggetti.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Oggetti.Studente;

public class StudenteDaoImpl implements StudenteDao {
	
public void inserimento(Connection connection ,Studente studente) {
	
	String statement = "INSERT INTO \"Studente\" (\"Nome\",\"Cognome\",\"DataN\",\"Cf\",\"DataIscrizione\") VALUES ('" +studente.getNome()+"','"+studente.getCognome()+"','"
	+studente.getData()+"','"+studente.getCF()+"','"+studente.getDataIscrizione()+"'"+ ");";
	
	try {
		
		Statement inserimento = connection.createStatement();
		inserimento.execute(statement);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
