package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Oggetti.Corso;

public class CorsoDaoImpl implements CorsoDao{
	
	//TODO MAX > MIN ALTRIMENTI ERRORE
	
	public void inserimento(Corso corso ,Connection connection) {
		
		String statement = "INSERT INTO \"Corso\" (\"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\") VALUES (" + "'" + corso.getNome() + "'"  + "," +  "'" + corso.getDescrizione() + "'" + ","
		+ corso.getMaxPartecipanti() + "," + corso.getMinPartecipazione() + ");";
		
		int procedere = controlloDuplicati(connection ,corso.getNome());
		
		if(procedere == 1) {
			
			try {
				
				Statement inserimento = connection.createStatement();
				inserimento.execute(statement);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			JOptionPane.showMessageDialog(null, "Corso gia' presente nei databas.", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	}
	
	
	public int controlloDuplicati (Connection connection ,String nome) {
		
		String statement = "SELECT \"CorsoId\" FROM \"Corso\" WHERE \"Nome\" LIKE '" + nome + "';";
		
		Statement check;
		try {
			
			check = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet risultato = check.executeQuery(statement);
			
			if(risultato.absolute(1)) {
				
				return 0;
				
			}else
				return 1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}
	public int getNextCorsoId(Connection connection) {
		
		String statement = "SELECT Max(\"CorsoId\") FROM \"Corso\";";
		int code = 0;
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			
			if(risultato.next()) {
				
				code = Integer.parseInt(risultato.getString(1));
				
			}
			
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
	
	public LinkedList<String> getNomiCorsiByKey(Connection connection ,String key) {
		
		LinkedList<String> lista = new LinkedList<String>();
		
		String statement = "SELECT \"Nome\" FROM \"Corso\" WHERE \"Nome\" LIKE '%" + key + "%' OR \"Descrizione\" LIKE '%" + key + "%';";
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			
			while(risultato.next()) {
				
				lista.add(risultato.getString(1).toString());
				
			}
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public LinkedList<String> getNomiById(Connection connection ,LinkedList<String> list) {
		
		String stringRicerca = null;	
		String statement ;
		
		LinkedList<String> tmpNomi = new LinkedList<String>();
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato;
			
			int i = 0;
			
			if(list.get(i) != null) {
				
				while(i < list.size()) {
					
					stringRicerca = list.get(i);
					statement = "SELECT \"Nome\" FROM \"Corso\" WHERE \"CorsoId\" = '" + stringRicerca + "';";
					risultato = ricerca.executeQuery(statement);
						
					risultato.next();
					tmpNomi.add(risultato.getString(1));
					
						i++;
						
				}
				
			}
			
			
			return tmpNomi;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public LinkedList<String> getCorsiTramiteKeyETema(Connection connection ,String key ,String theme) {
		
		LinkedList<String> lista = new LinkedList<String>();
		CorsoETemaDaoImpl corsoTema = new CorsoETemaDaoImpl();
		
		//TROVA GLI ID TRAMITE TEMA
		LinkedList<String> listaIdCorsi = corsoTema.ricercaCorsoByTheme(connection , theme);
		
		
		int size = listaIdCorsi.size();

		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato;
			String statementCorso;
			
			int i = 0;
			
			while(i < size) {
				
				statementCorso = "SELECT \"Nome\" FROM \"Corso\" WHERE (\"Nome\" LIKE '%" + key + "%' OR \"Descrizione\" LIKE '%" + key + "%') AND \"Corso\".\"CorsoId\" = '" + listaIdCorsi.get(i) + "';";
				risultato = ricerca.executeQuery(statementCorso);
				
				
				while(risultato.next()) {
					
					lista.add(risultato.getString(1));
				
				}

				
				i++;
				
			}
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
	public String getNomeById(Connection connection ,String id) {
		
		String statement = "SELECT \"Nome\" FROM \"Corso\" WHERE \"CorsoId\" = '" + id + "';";
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			if(risultato.next()) {
				String result = risultato.getString(1);
				return result;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	public void deleteCorsoByName(Connection connection ,String nome) {
		
		String statement = "DELETE FROM \"Corso\" WHERE \"Nome\" = '" + nome + "';";
		
		try {
			
			Statement delete = connection.createStatement();
			
			if(delete.execute(statement)) {
				
				JOptionPane.showMessageDialog(null ,"Corso cancellato correttamente", "PSQL", JOptionPane.INFORMATION_MESSAGE);
				
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<String> getCorso(Connection connection ,String nome) {
		
		List<String> result = new LinkedList();
		
		String statement = "SELECT \"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\",\"CorsoId\" FROM \"Corso\" WHERE \"Nome\" = '"+ nome + "';";
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			
			while(risultato.next()) {
				
				result.add(risultato.getString(1));
				result.add(risultato.getString(2));
				result.add(risultato.getString(3));
				result.add(risultato.getString(4));
				result.add(risultato.getString(5));
				
			}
			
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateCorso(Connection connection ,String id ,String nome ,String descrizione ,String maxPartecipanti ,String minPartecipazione) {
		
		String statement = "UPDATE \"Corso\" SET \"Nome\" = '" + nome + "', \"Descrizione\" = '" + descrizione + "', \"MaxPartecipanti\" = '"+ maxPartecipanti + "', \"MinPartecipazione\" = '" + minPartecipazione + "' WHERE \"CorsoId\" = " + id + ";" ;
		
		try {
			
			Statement update = connection.createStatement();
			update.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
