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
		Statement statement = null;
		
		try {
			
			statement = connection.createStatement();
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
		}finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public List<String> getStudentiCfByLezioneId(Connection connection ,int lezioneId) {
		
		List<String> result = new LinkedList();
		String ricerca = "SELECT \"Cf\" FROM \"Presenza\" WHERE \"LezioneId\" = '" + lezioneId + "';";
		
		Statement statement = null;
		ResultSet risultato = null;
		
		try {
			
			statement = connection.createStatement();
			risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				result.add(risultato.getString(1));
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(risultato != null) {
				try {
					risultato.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
	}
	
	public void updatePresenzaStudente(Connection connection ,int lezioneId ,String cf ,String presenza) {
		

		String update = "UPDATE \"Presenza\" SET \"Presente\" = '" + presenza + "' WHERE \"Cf\" = '" + cf + "' AND \"LezioneId\" = '" + lezioneId + "';";
		Statement statement = null;
		
		try {
			
			statement  = connection.createStatement();
			statement.execute(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String checkPresenzaStudente(Connection connection ,String cf ,int lezioneId) {
		
		String ricerca = "SELECT \"Presente\" FROM \"Presenza\" WHERE \"Cf\" = '" + cf + "' AND \"LezioneId\" = " + lezioneId + ";";
		Statement statement = null;
		ResultSet risultato = null;
		String result = null;
		
		try {
			
			statement = connection.createStatement();
			risultato = statement.executeQuery(ricerca);
			
			
			while(risultato.next()) {
				
				result = risultato.getString(1);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(risultato != null) {
				try {
					risultato.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
				
		
	}
	
	public int countPresenti(Connection connection ,int lezioneId) {
		
		int numero = 0;
		String ricerca = "SELECT COUNT(\"Cf\") FROM \"Presenza\" WHERE \"LezioneId\" = " + lezioneId + " AND \"Presente\" = 'Presente';";
		Statement statement = null;
		ResultSet risultato = null;
		
		try {
			
			statement = connection.createStatement();
			risultato = statement.executeQuery(ricerca);
			
			
			while(risultato.next()) {
				
				numero = Integer.parseInt(risultato.getString(1));
				
			}

			
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(risultato != null) {
				try {
					risultato.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return numero;
		}
		
	}
	
}
