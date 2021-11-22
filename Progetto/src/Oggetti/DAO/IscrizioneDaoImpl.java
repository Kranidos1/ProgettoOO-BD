package Oggetti.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Oggetti.Studente;

public class IscrizioneDaoImpl {
	public void inserimento(Connection connection ,int CorsoId ,String Cf) {
		
		String statement = "INSERT INTO \"Iscrizione\" (\"Cf\",\"Iscritto\",\"CorsoId\") VALUES ('"+Cf+"','"+true+"','"+CorsoId+"');";
		
		try {
			
			Statement inserimento = connection.createStatement();
			inserimento.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
