package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import Oggetti.Lezione;

public class LezioneDaoImpl {

	public void inserimentoLezione(Connection connection ,Lezione lezione) {
		
		String inserimento = "INSERT INTO \"Lezione\" (\"Titolo\",\"Descrizione\",\"Durata\",\"Data\",\"OraInizio\",\"CorsoId\") "
				+ "VALUES ('" + lezione.getTitolo() + "','" + lezione.getDescrizione() + "','" + lezione.getDurata() + "','" + lezione.getData() + "','" + lezione.getOraInizio() + "','" + lezione.getCorsoId() + "');";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(inserimento);
			
			int lezioneId = recuperaIdUltimaInserita(connection);
			
			ConnectionDao connectionDao = new ConnectionDao();
			connectionDao.getPresenzaDao().inserimentoAssociazioneConStudenti(connection, lezione.getCorsoId(), lezioneId);
			
			JOptionPane.showMessageDialog(null, "Lezione salvata!", "Ok!", JOptionPane.INFORMATION_MESSAGE);
			
			if(statement != null) {
				statement.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			
			if(statement != null) {
				statement.close();
			}
			if(risultato != null) {
				risultato.close();
			}
			
			return id;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Non puoi usare caratteri speciali nei field.", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		return 0;
		
	}
	
	public List<String>[] getDateLezioniDaGestireELezione(Connection connection ,int corsoId) {
		
		String statement = "SELECT \"Data\",\"LezioneId\",\"CorsoId\",\"OraInizio\",\"Durata\",\"Descrizione\",\"Titolo\",\"Check\" FROM \"Lezione\" WHERE \"CorsoId\" = '" + corsoId +"' AND \"Check\" = 'false';";
		List<String>[] listaRisultato;
		
		int size = 0;
		
		try {
			
			Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet risultato = ricerca.executeQuery(statement);
			
			while(risultato.next()) {
				
				size++;
				
			}
			risultato.beforeFirst();
			
			listaRisultato = new List[size];
			
			int i = 0;
			
			while(risultato.next()) {
				
				listaRisultato[i] = new LinkedList();
				listaRisultato[i].add(risultato.getString(1));
				listaRisultato[i].add(risultato.getString(2));
				listaRisultato[i].add(risultato.getString(3));
				listaRisultato[i].add(risultato.getString(4));
				listaRisultato[i].add(risultato.getString(5));
				listaRisultato[i].add(risultato.getString(6));
				listaRisultato[i].add(risultato.getString(7));
				listaRisultato[i].add(risultato.getString(8));
				
				i++;
			}
			
			if(ricerca != null) {
				ricerca.close();
			}
			if(risultato != null) {
				risultato.close();
			}
			
			return listaRisultato;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public int getLezioneIdByData(Connection connection ,String data) {
		
		int idLezione = 0;
		String ricerca = "SELECT \"LezioneId\" FROM \"Lezione\" WHERE TO_CHAR(\"Data\" :: Date,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD') AND \"Check\" = 'false';";
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				idLezione = Integer.parseInt(risultato.getString(1));
				
			}
			
			if(statement != null) {
				statement.close();
			}
			if(risultato != null) {
				risultato.close();
			}
			
			return idLezione;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void updateCheck(Connection connection ,int lezioneId) {
		String update = "UPDATE \"Lezione\" SET \"Check\" = '" + true + "' WHERE \"LezioneId\" = '" + lezioneId + "';";
		
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
	
	public int gestioneDuplicati(Connection connection ,String data ,int corsoId) {
		
		String ricerca = "SELECT \"LezioneId\" FROM \"Lezione\" WHERE TO_CHAR(\"Data\" :: Date,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD') AND \"CorsoId\" = '" + corsoId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			if(risultato.next()) {
				
				if(statement != null) {
					statement.close();
				}
				if(risultato != null) {
					risultato.close();
				}
				
				return 0;
			}else {
				
				if(statement != null) {
					statement.close();
				}
				if(risultato != null) {
					risultato.close();
				}
				
				return 1;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public int gestioneDuplicatiUpdate(Connection connection ,String data ,int corsoId ,int lezioneId) {
		
		String ricerca = "SELECT \"LezioneId\" FROM \"Lezione\" WHERE TO_CHAR(\"Data\" :: Date,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD') AND \"CorsoId\" = '" + corsoId + "' AND \"LezioneId\" != '" + lezioneId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			if(risultato.next()) {
				
				if(statement != null) {
					statement.close();
				}
				if(risultato != null) {
					risultato.close();
				}
				
				return 0;
				
			}else {
				
				if(statement != null) {
					statement.close();
				}
				if(risultato != null) {
					risultato.close();
				}
				
				return 1;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	public void updateLezione(Connection connection ,Lezione lezione,int lezioneId ) {
		
		String update = "UPDATE \"Lezione\" SET \"Titolo\" = '" + lezione.getTitolo() + "',\"Descrizione\" = '" + lezione.getDescrizione() + "',\"Durata\" = '" + 
		lezione.getDurata() + "',\"Data\" = '" + lezione.getData() + "',\"OraInizio\" = '" + lezione.getOraInizio() + "' WHERE \"LezioneId\" = '" + lezioneId + "';";
		
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
	
	
	public int countLezioni(Connection connection ,int corsoId) {
		
		//COUNT
		String count = "SELECT COUNT(\"LezioneId\") AS \"NumberOfLessions\" FROM \"Lezione\" WHERE \"CorsoId\" = '" + corsoId + "';";
		
		Statement statementLezioni;
		try {
			
			statementLezioni = connection.createStatement();
			ResultSet risultato = statementLezioni.executeQuery(count);
			
			if(risultato.next()) {
				String result = risultato.getString(1);
				if(statementLezioni != null) {
					statementLezioni.close();
				}
				if(risultato != null) {
					risultato.close();
				}
				
				return Integer.parseInt(result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 99;
		
		
	}
	
	public int countCheckFalse(Connection connection ,int corsoId) {
		
		//
		String countFalse = "SELECT COUNT(\"LezioneId\") AS \"NumberOfLessions\" FROM \"Lezione\" WHERE \"CorsoId\" = '" + corsoId + "' AND \"Check\" = 'false';";
		
		try {
			
			
			Statement statementLezioniFalse = connection.createStatement();
			ResultSet result = statementLezioniFalse.executeQuery(countFalse);
			
			
			if(result.next()) {
				
				
				int risultato = Integer.parseInt(result.getString(1));
				
				if(statementLezioniFalse != null) {
					statementLezioniFalse.close();
				}
				if(result != null) {
					result.close();
				}
				return risultato;
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 99;
		
	}
	
	public List getLezioniByCorsoId(Connection connection ,int corsoId) {
		
		List<Integer> listaLezioniId = new LinkedList();
		String ricerca = "SELECT \"LezioneId\" FROM \"Lezione\" WHERE \"CorsoId\" = " + corsoId + ";";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				listaLezioniId.add(Integer.parseInt(risultato.getString(1)));
				
			}
			
			if(statement != null) {
				statement.close();
			}
			if(risultato != null) {
				risultato.close();
			}
			
			return listaLezioniId;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
	}
	
	public int countPresenti(Connection connection ,int lezioneId) {
		
		String ricerca = "SELECT COUNT(\"Cf\") FROM \"Presenza\" WHERE \"LezioneId\" = "+ lezioneId +" AND \"Presente\" = 'Presente'";
		int result = 0;
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			if(risultato.next()) {
				
				result = Integer.parseInt(risultato.getString(1));
				
			}
			
			if(statement != null) {
				statement.close();
			}
			if(risultato != null) {
				risultato.close();
			}
			
			return result;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
		
	}
}
