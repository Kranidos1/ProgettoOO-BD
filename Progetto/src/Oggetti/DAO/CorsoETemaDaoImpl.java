package Oggetti.DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CorsoETemaDaoImpl implements CorsoETemaDao {

	public void inserimento(Connection connection ,int corsoId ,String nomeTema) {
		
		String statement = "INSERT INTO \"Corso-Tema\" (\"CorsoId\",\"NomeTema\") VALUES ("+ corsoId + "," + "'" + nomeTema + "'" + ");";
		
		try {
			
			Statement inserimento = connection.createStatement();
			inserimento.execute(statement);
			if(inserimento != null) {
				inserimento.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> ricercaCorsoByTheme(Connection connection ,String theme) {
		
		LinkedList<String> lista = new LinkedList<String>();
		
		String statement = "SELECT \"CorsoId\" FROM \"Corso-Tema\" WHERE \"NomeTema\" = '" + theme + "';";
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet result = ricerca.executeQuery(statement);
			
			while(result.next()) {
				
				lista.add(result.getString(1));
				
			}
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(result != null) {
				result.close();
			}
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public List<String> getAllThemeOfCorso(Connection connection ,String id) {
		
		List<String> risultato = new LinkedList<String>();
		String statement = "SELECT \"NomeTema\" FROM \"Corso-Tema\" WHERE \"CorsoId\" = '" + id + "';";
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet result = ricerca.executeQuery(statement);
			
			while(result.next()) {
				
				risultato.add(result.getString(1));
				
			}
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(result != null) {
				result.close();
			}
			
			return risultato;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void deleteCollegamento(Connection connection ,String id) {
		
		String statement = "DELETE FROM \"Corso-Tema\" WHERE \"CorsoId\" = '" + id + "';";
		
		try {
			
			Statement delete = connection.createStatement();
			delete.execute(statement);
			if(delete != null) {
				delete.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
