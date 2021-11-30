package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Frames.Lezione;

public class LezioneDaoImpl {

	public void inserimentoLezione(Connection connection ,Lezione lezione) {
		
		String inserimento = "INSERT INTO \"Lezione\" (\"Titolo\",\"Descrizione\",\"Durata\",\"Data\",\"OraInizio\",\"CorsoId\") "
				+ "VALUES ('" + lezione.getTitolo() + "','" + lezione.getDescrizione() + "','" + lezione.getDurata() + "','" + lezione.getData() + "','" + lezione.getOraInizio() + "','" + lezione.getCorsoId() + "');";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(inserimento);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int recuperaIdUltimaInserita(Connection connection) {
		
		String query = "SELECT Max(\"LezioneId\") FROM \"Lezione\";";
		int id = 0;
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(query);
			
			while(risultato.next()) {
				
				id = Integer.parseInt(risultato.getString(1));
				
			}
			
			return id;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return 0;
		
	}
}
