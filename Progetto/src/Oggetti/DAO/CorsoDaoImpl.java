package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Oggetti.Corso;

public class CorsoDaoImpl implements CorsoDao{
	
	public void inserimento(Corso corso ,Connection connection) {
		
		String statement = "INSERT INTO \"Corso\" (\"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\") VALUES (" + "'" + corso.getNome() + "'"  + "," +  "'" + corso.getDescrizione() + "'" + ","
		+ corso.getMaxPartecipanti() + "," + corso.getMaxPartecipanti() + ");";
		
		try {
			
			Statement inserimento = connection.createStatement();
			inserimento.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getNextCorsoId(Connection connection) {
		
		String statement = "SELECT \"CorsoId\" FROM \"Corso\";";
		
		try {
			
			Statement ricerca = connection.createStatement();
			List<String> lista = new LinkedList<>();
			
			ResultSet result = ricerca.executeQuery(statement);
			
			int i = 0;
			while(result.next()) {
				
				lista.add(result.getString(1));
				System.out.println(lista.get(i));
				i++;
			}
			
			int size = lista.size();
			size -= 1;
			
			int code = Integer.parseInt(lista.get(size));
			
			
			return code;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public List<String> getNomiCorsi(Connection connection) {
		
		String statement = "SELECT \"Nome\" FROM \"Corso\";";
		
		try {
			
			Statement ricerca = connection.createStatement();
			List<String> lista = new LinkedList<>();
			
			ResultSet result = ricerca.executeQuery(statement);
			

			while(result.next()) {
				
				lista.add(result.getString(1));
				
			}
			
			
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
		
	}
	
	public int trovaCorsoId(Connection connection ,String nomeCorso) {
		
		String statement = "SELECT \"CorsoId\" FROM \"Corso\" WHERE \"Nome\" = '" + nomeCorso + "';"; 
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			String risu = null;
			while(risultato.next()) {
				risu = risultato.getString(1).toString();
			}
			int corsoId = Integer.parseInt(risu);
			return corsoId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
}
