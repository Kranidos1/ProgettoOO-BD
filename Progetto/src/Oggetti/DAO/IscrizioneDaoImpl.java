package Oggetti.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Oggetti.Studente;

public class IscrizioneDaoImpl {
	public void inserimento(Connection connection ,int CorsoId ,String Cf ,String data) {
		
		String statement = "INSERT INTO \"Iscrizione\" (\"Cf\",\"DataIscrizione\",\"CorsoId\") VALUES ('"+Cf+"','"+data+"','"+CorsoId+"');";
		
		try {
			
			Statement inserimento = connection.createStatement();
			inserimento.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
