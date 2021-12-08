package Oggetti.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

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
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		return 0;
	}
	
	public List<String>[] getStudentiByCorsoName(Connection connection ,String nomeCorso) {
		
		ConnectionDao connectionDao = new ConnectionDao();
		int id = connectionDao.getCorsoDao().trovaCorsoId(connection, nomeCorso);
		String qualcosa;
		String ricerca = "SELECT \"Cf\" FROM \"Iscrizione\" WHERE \"CorsoId\" = '" + id  + "';";
			
		List<String> listaCf = new LinkedList<String>();
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				listaCf.add(risultato.getString(1));
				
			}
			
			int size = listaCf.size();
			List<String>[] listaStudenti = new LinkedList[size];
			
			

			Vector[][] valori = new Vector[1][3];
			valori[0][0] = new Vector();
			valori[0][1] = new Vector();
			valori[0][2] = new Vector();
			
			int i = 0;
			while(i < size) {
				
				//PRENDE NOME COGNOME E CF
				valori = connectionDao.getStudenteDao().ricercaStudenteByCf(connection, listaCf.get(i));
				listaStudenti[i] = new LinkedList();
				listaStudenti[i].add(valori[0][0].toString());
				listaStudenti[i].add(valori[0][1].toString());
				listaStudenti[i].add(valori[0][2].toString());
				listaStudenti[i].add("ciao");
				listaStudenti[i].add("ciao");
				listaStudenti[i].add("ciao");
				
				i++;
			}
			
			return listaStudenti;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public List<String>[] getStudentiByCorsoId(Connection connection ,int corsoId) {
		
		String qualcosa;
		String ricerca = "SELECT \"Cf\" FROM \"Iscrizione\" WHERE \"CorsoId\" = '" + corsoId  + "';";
			
		List<String> listaCf = new LinkedList<String>();
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			while(risultato.next()) {
				
				listaCf.add(risultato.getString(1));
				
			}
			
			int size = listaCf.size();
			List<String>[] listaStudenti = new LinkedList[size];
			
			

			Vector[][] valori = new Vector[1][3];
			valori[0][0] = new Vector();
			valori[0][1] = new Vector();
			valori[0][2] = new Vector();
			
			ConnectionDao connectionDao = new ConnectionDao();
			
			int i = 0;
			while(i < size) {
				
				//PRENDE NOME COGNOME E CF
				valori = connectionDao.getStudenteDao().ricercaStudenteByCf(connection, listaCf.get(i));
				listaStudenti[i] = new LinkedList();
				listaStudenti[i].add(valori[0][0].toString());
				listaStudenti[i].add(valori[0][1].toString());
				listaStudenti[i].add(valori[0][2].toString());
				listaStudenti[i].add("ciao");
				listaStudenti[i].add("ciao");
				listaStudenti[i].add("ciao");
				
				i++;
			}
			
			return listaStudenti;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void deleteStudente(Connection connection ,String cf ,int corsoId) {
		
		String delete = "DELETE FROM \"Iscrizione\" WHERE \"Cf\" = '" + cf + "' AND \"CorsoId\" = '" + corsoId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(delete);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int countStudentiIscritti(Connection connection ,int corsoId) {
		
		String count = "SELECT COUNT(\"Cf\") FROM \"Iscrizione\" WHERE \"CorsoId\" = '" + corsoId + "';";
		int numeroStud = 0;
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(count);
			
			if(risultato.next()) {
				
				numeroStud = Integer.parseInt(risultato.getString(1));
				
			}
			
			return numeroStud;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numeroStud;
	}
	
	public void updateStatoStudente(Connection connection ,String cf ,String valutazione) {
		
		String update = "UPDATE \"Iscrizione\" SET \"ExamPassed\" = '"+ valutazione + "' WHERE \"Cf\" = '" + cf + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getPromozioneStudente(Connection connection ,String cf ,int corsoId) {
		
		String ricerca = "SELECT \"ExamPassed\" FROM \"Iscrizione\" WHERE \"Cf\" = '" + cf + "' AND \"CorsoId\" = '" + corsoId + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet risultato = statement.executeQuery(ricerca);
			
			if(risultato.next()) {
				
				return risultato.getString(1).toString();
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ricerca;
		
		
	}
	

}

