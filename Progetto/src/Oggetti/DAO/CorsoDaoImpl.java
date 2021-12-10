package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Frames.Controller;
import Oggetti.Corso;


public class CorsoDaoImpl implements CorsoDao{
	
	//TODO MAX > MIN ALTRIMENTI ERRORE
	
	public int inserimento(Corso corso ,Connection connection) {
		
		Controller controller = new Controller();
		String descrizione = corso.getDescrizione();
		String statement;
		Statement inserimento = null;
		
		if(descrizione != null) {
			
			 statement = "INSERT INTO \"Corso\" (\"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\") VALUES (" + "'" + controller.escape(corso.getNome()) + "'"  + "," +  "'" + controller.escape(corso.getDescrizione()) + "'" + ","
					+ corso.getMaxPartecipanti() + "," + corso.getMinPartecipazione() + ");";
		
		}else {
			statement = "INSERT INTO \"Corso\" (\"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\") VALUES (" + "'" + controller.escape(corso.getNome()) + "'"  + "," +  "'" + corso.getDescrizione() + "'" + ","
					+ corso.getMaxPartecipanti() + "," + corso.getMinPartecipazione() + ");";
		}
		
		
		int procedere = controlloDuplicati(connection ,corso.getNome());
		
		if(procedere == 1) {
			
			try {
				
				inserimento = connection.createStatement();
				inserimento.execute(statement);
				
				JOptionPane.showMessageDialog(null, "Corso Creato!", "Ok!", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return 0 ;
			}
				finally{
					
					if(inserimento != null) {
						try {
							inserimento.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					return 1;
				}
			
		}else
			if(procedere == 99){
			
			JOptionPane.showMessageDialog(null, "Corso gia' presente nei database.", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		return 0;
		
		
	}
	
	
	public int controlloDuplicati (Connection connection ,String nome) {
		
		Controller controller = new Controller();
		String statement = "SELECT \"CorsoId\" FROM \"Corso\" WHERE \"Nome\" LIKE '" + controller.escape(nome) + "';";
		
		Statement check = null;
		ResultSet risultato = null;
		
		try {
			
			check = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			risultato = check.executeQuery(statement);
			
			if(risultato.absolute(1)) {
				
				if(risultato != null) {
					risultato.close();
				}
				return 99;
				
			}else {
				
				if(risultato != null) {
					risultato.close();
				}
				return 1;
				
			}		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return 0;
	}
	public int getNextCorsoId(Connection connection) {
		
		String statement = "SELECT Max(\"CorsoId\") FROM \"Corso\";";
		int code = 0;
		Statement ricerca = null;
		ResultSet risultato = null;
		
		try {
			
			ricerca = connection.createStatement();
			risultato = ricerca.executeQuery(statement);
			
			if(risultato.next()) {
				
				code = Integer.parseInt(risultato.getString(1));
				
			}
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(risultato != null) {
				risultato.close();
			}
			
			return code;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public List<String> getNomiCorsi(Connection connection) {
		
		String statement = "SELECT \"Nome\" FROM \"Corso\" WHERE \"Finito\" = 'false';";
		
		try {
			
			Statement ricerca = connection.createStatement();
			List<String> lista = new LinkedList<>();
			
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
	
	public int trovaCorsoId(Connection connection ,String nomeCorso) {
		
		String statement = "SELECT \"CorsoId\" FROM \"Corso\" WHERE \"Nome\" = '" + nomeCorso + "';"; 
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			String risu = null;
			
			while(risultato.next()) {
				
				risu = risultato.getString(1).toString();
				
			}
			if(risu != null) {
				
				int corsoId = Integer.parseInt(risu);
				if(ricerca != null) {
					ricerca.close();
				}
				
				if(risultato != null) {
					risultato.close();
				}
				return corsoId;
				
			}else {
				if(ricerca != null) {
					ricerca.close();
				}
				
				if(risultato != null) {
					risultato.close();
				}
				return -1;
			}
				
			
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
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(risultato != null) {
				risultato.close();
			}
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		return null;
		
	}
	
	public LinkedList<String> getNomiById(Connection connection ,LinkedList<String> list) {
		
		String stringRicerca = null;	
		String statement ;
		
		LinkedList<String> tmpNomi = new LinkedList<String>();
		
		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = null;
			
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
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(risultato != null) {
				risultato.close();
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
		ConnectionDao connectionDao = new ConnectionDao();
		//TROVA GLI ID TRAMITE TEMA
		LinkedList<String> listaIdCorsi = connectionDao.getCorsoTemaDao().ricercaCorsoByTheme(connection , theme);
		
		
		int size = listaIdCorsi.size();

		try {
			
			Statement ricerca = connection.createStatement();
			ResultSet risultato = null;
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
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(risultato != null) {
				risultato.close();
			}
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
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
				
				if(ricerca != null) {
					ricerca.close();
				}
				
				if(risultato != null) {
					risultato.close();
				}
				
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
				if(delete != null) {
					delete.close();
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Corso getCorso(Connection connection ,String nome) {
		
		
		String statement = "SELECT \"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\",\"CorsoId\",\"Finito\" FROM \"Corso\" WHERE \"Nome\" = '"+ nome + "';";
		Corso corso = new Corso();
		
		try {
			
			List<String> result = new LinkedList();
			Statement ricerca = connection.createStatement();
			ResultSet risultato = ricerca.executeQuery(statement);
			
			while(risultato.next()) {
				
				corso.setNome(risultato.getString(1));
				corso.setDescrizione(risultato.getString(2));
				corso.setMaxPartecipanti(Integer.parseInt(risultato.getString(3)));
				corso.setMinPartecipazione(Integer.parseInt(risultato.getString(4)));
				corso.setCorsoId(Integer.parseInt(risultato.getString(5)));
				corso.setCheck(risultato.getString(6));
				
			}
			
			if(ricerca != null) {
				ricerca.close();
			}
			
			if(risultato != null) {
				risultato.close();
			}
			
			return corso;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void updateCorso(Connection connection ,Corso corso) {
		
		String statement = "UPDATE \"Corso\" SET \"Nome\" = '" + corso.getNome() + "', \"Descrizione\" = '" + corso.getDescrizione() + "', \"MaxPartecipanti\" = '"+ corso.getMaxPartecipanti().toString() + 
				"', \"MinPartecipazione\" = '" + corso.getMinPartecipazione().toString() + "' WHERE \"CorsoId\" = " + corso.getCorsoId().toString() + ";" ;
		
		try {
			
			Statement update = connection.createStatement();
			update.execute(statement);
			
			if(update != null) {
				
				update.close();
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateCheckCorso(Connection connection ,int corsoId) {
		
		String update = "UPDATE \"Corso\" SET \"Finito\" = true WHERE \"CorsoId\" = '" + corsoId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(update);
			
			if(statement != null) {
				
				statement.close();;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public LinkedList getNomeCorsiFiniti(Connection connection ) {
		
		String ricerca = "SELECT \"Nome\" FROM \"Corso\" WHERE \"Finito\" = 'true';";
		
		int count = 0;
		
		try {
			
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery(ricerca);
			
			while(result.next()) {
				
				count++;
				
			}
			result.beforeFirst();
			
			LinkedList corsiTrovati = new LinkedList();

			while(result.next()) {
				
				corsiTrovati.add(result.getString(1));
				
			}
			
			if(statement != null) {
				statement.close();
			}
			
			if(result != null) {
				result.close();
			}
			
			return corsiTrovati;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void updateCheckCorsoGestito(Connection connection ,int corsoId) {
		
		String update = "UPDATE \"Corso\" SET \"Finito\" = '" + "truex" + "' WHERE \"CorsoId\" = '" + corsoId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(update);
			
			if(statement != null) {
				statement.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String controllaStatoCorso(Connection connection ,int corsoId) {
		
		String ricerca = "SELECT \"Finito\" FROM \"Corso\" WHERE \"CorsoId\" = " + corsoId + ";";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				String result = risultato.getString(1);
				
				if(statement != null) {
					statement.close();
				}
				
				if(risultato != null) {
					risultato.close();
				}
				
				return result;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		
	}
	
	public LinkedList getNomeCorsiArchiviati(Connection connection ) {
		
		String ricerca = "SELECT \"Nome\" FROM \"Corso\" WHERE \"Finito\" = 'truex';";
		
		int count = 0;
		
		try {
			
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery(ricerca);
			
			while(result.next()) {
				
				count++;
				
			}
			result.beforeFirst();
			
			LinkedList corsiTrovati = new LinkedList();

			while(result.next()) {
				
				corsiTrovati.add(result.getString(1));
				
			}
			
			if(statement != null) {
				statement.close();
			}
			
			if(result != null) {
				result.close();
			}
			
			return corsiTrovati;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
