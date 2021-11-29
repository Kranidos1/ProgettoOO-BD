package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
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
	
	
	public int controlloDuplicati(Connection connection ,String cf ,int corsoId) {
		
		String statementControllo = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" = '" + cf + "' AND \"CorsoId\" = '" + corsoId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(statementControllo);
			
			if(risultato.next()) {
				return 0;
			}else
				return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
}

