package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PresenzaDaoImpl {
	
	public void inserimentoAssociazioneConStudenti(Connection connection ,int corsoId ,int lezioneId) {
		String cf;
		String insert;
		ConnectionDao connectionDao = new ConnectionDao();
		List<String>[] studenti = connectionDao.getIscrizioneDao().getStudentiByCorsoId(connection, corsoId);
		
		try {
			
			Statement statement = connection.createStatement();
			int  i = 0;
			
			while(i < Arrays.asList(studenti).size()) {
				
				cf = studenti[i].get(2).substring(1, studenti[i].get(2).length()-1);
				
				insert = "INSERT INTO \"Presenza\" (\"LezioneId\",\"Cf\",\"Presente\") VALUES ('" + lezioneId + "','" + cf +"','X');";
				statement.execute(insert);
				i++;
				
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<String> getStudentiCfByLezioneId(Connection connection ,int lezioneId) {
		
		List<String> result = new LinkedList();
		String ricerca = "SELECT \"Cf\" FROM \"Presenza\" WHERE \"LezioneId\" = '" + lezioneId + "';";
		
		Statement statement;
		try {
			
			statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				result.add(risultato.getString(1));
				
			}
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updatePresenzaStudente(Connection connection ,int lezioneId ,String cf ,String presenza) {
		

		String update = "UPDATE \"Presenza\" SET \"Presente\" = '" + presenza + "' WHERE \"Cf\" = '" + cf + "' AND \"LezioneId\" = '" + lezioneId + "';";
		
		try {
			
			Statement statement  = connection.createStatement();
			statement.execute(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String checkPresenzaStudente(Connection connection ,String cf ,int lezioneId) {
		
		String ricerca = "SELECT \"Presente\" FROM \"Presenza\" WHERE \"Cf\" = '" + cf + "' AND \"LezioneId\" = " + lezioneId + ";";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			
			while(risultato.next()) {
				
				return risultato.getString(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
		return null;
		
	}
	
	public int countPresenti(Connection connection ,int lezioneId) {
		
		int numero = 0;
		String ricerca = "SELECT COUNT(\"Cf\") FROM \"Presenza\" WHERE \"LezioneId\" = " + lezioneId + " AND \"Presente\" = 'Presente';";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			
			while(risultato.next()) {
				
				numero = Integer.parseInt(risultato.getString(1));
				
			}
			
			return numero;
			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return lezioneId;
		
	}
	
}
