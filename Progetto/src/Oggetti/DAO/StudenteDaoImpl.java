package Oggetti.DAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Oggetti.Studente;

public class StudenteDaoImpl implements StudenteDao {
	
	
public void inserimento(Connection connection ,Studente studente) {
	
	String statement = "INSERT INTO \"Studente\" (\"Nome\",\"Cognome\",\"DataN\",\"Cf\") VALUES ('" +studente.getNome()+"','"+studente.getCognome()+"','"
	+studente.getData()+"','"+studente.getCF()+"');";
	
	try {
		
		Statement inserimento = connection.createStatement();
		inserimento.execute(statement);
		
		
	} catch (SQLException e) {
//		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Studente gia' presente nei database,ma inserito comunque nel corso indicato.", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
		
	}
}

public Vector[] ricercaStudenteByName(Connection connection ,String name) {
	
	String statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + name + "%';";
	Statement perCorso;
	ResultSet corsoId;
	String corsoById;
	String ricercaCorsoId;
	
	
	try {
		
		Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet risultato = ricerca.executeQuery(statement);
		perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int size = 0;
		while(risultato.next()) {
			
			size++;
			
		}
		risultato.beforeFirst();
		
		ConnectionDao connectionDao = new ConnectionDao();
		
		Vector[] vettoreStudenti = new Vector[size];
		
		int i = 0;
		while(risultato.next()) {
			
			vettoreStudenti[i] = new Vector();
			vettoreStudenti[i].add(risultato.getString(1));

			vettoreStudenti[i].add(risultato.getString(2));

			vettoreStudenti[i].add(risultato.getString(3));

			
			ricercaCorsoId = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '" + risultato.getString(3) + "';";
			corsoId = perCorso.executeQuery(ricercaCorsoId);

			
			if(corsoId.next()) {
				
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
				vettoreStudenti[i].add(corsoById);
				
			}else
				vettoreStudenti[i].add("Empty");
			
			i++;
		}
		
		return vettoreStudenti;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

public Vector[] ricercaStudenteByCognome(Connection connection ,String cognome) {
	
	String statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cognome\" LIKE '%" + cognome + "%';";
	Statement perCorso;
	ResultSet corsoId;
	String corsoById;
	String ricercaCorsoId;
	
	try {
		
		Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet risultato = ricerca.executeQuery(statement);
		perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int size = 0;
		while(risultato.next()) {
			
			size++;
			
		}
		risultato.beforeFirst();
		ConnectionDao connectionDao = new ConnectionDao();
		
		Vector[] vettoreStudenti = new Vector[size];
		
		int i = 0;
		while(risultato.next()) {
			
			vettoreStudenti[i] = new Vector();
			
			vettoreStudenti[i].add(risultato.getString(1));
			
			vettoreStudenti[i].add(risultato.getString(2));
			
			vettoreStudenti[i].add(risultato.getString(3));
			
			
			ricercaCorsoId = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '" + risultato.getString(3) + "';";
			corsoId = perCorso.executeQuery(ricercaCorsoId);
			

			
			if(corsoId.next()) {
				
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
				vettoreStudenti[i].add(corsoById);
				
			}else
				vettoreStudenti[i].add("Empty");
			
			i++;
		}
		
		return vettoreStudenti;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

public Vector[][] ricercaStudenteByCf(Connection connection ,String cf) {
	
	String statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cf\" LIKE '%" + cf + "%';";
	Statement perCorso;
	ResultSet corsoId;
	String corsoById;
	String ricercaCorsoId;
	
	
	try {
		
		Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet risultato = ricerca.executeQuery(statement);
		perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int size = 0;
		while(risultato.next()) {
			
			size++;
			
		}
		risultato.beforeFirst();
		
		ConnectionDao connectionDao = new ConnectionDao();
		Vector[][] vettoreStudenti = new Vector[size][7];
		
		int i = 0;
		while(risultato.next()) {
			
			vettoreStudenti[i][0] = new Vector();
			vettoreStudenti[i][0].add(risultato.getString(1));
			vettoreStudenti[i][1] = new Vector();
			vettoreStudenti[i][1].add(risultato.getString(2));
			vettoreStudenti[i][2] = new Vector();
			vettoreStudenti[i][2].add(risultato.getString(3));
			vettoreStudenti[i][3] = new Vector();

			
			ricercaCorsoId = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '" + risultato.getString(3) + "';";
			corsoId = perCorso.executeQuery(ricercaCorsoId);
			
			
			if(corsoId.next()) {
				
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
				vettoreStudenti[i][3].add(corsoById);
				
			}else
				vettoreStudenti[i][3].add("Empty");
			
			i++;
		}
		
		return vettoreStudenti;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

@Override
public Vector[] ricercaStudenteByDataIscrizione(Connection connection ,String data) {
	
	String result = null;
	String statement;
	String corsoById;
	//TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	
	
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		ConnectionDao connectionDao = new ConnectionDao();
		
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		
		Vector[] vettoreStudenti = new Vector[size];
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cf\" LIKE '" + risultatoDate.getString(3) + "';" ;
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i] = new Vector();
				
				vettoreStudenti[i].add(risultato.getString(1));
				
				vettoreStudenti[i].add(risultato.getString(2));
				
				vettoreStudenti[i].add(risultato.getString(3));
				
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

public List<String>[] ricercaStudenteByNomeECognome(Connection connection ,String nome ,String cognome) {
	
	String statement;
	String ricercaCorsoId;
	Statement perCorso;
	ResultSet corsoId;
	String corsoById;
	
	try {
		
		Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet risultato;
		perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int i = 0;
			
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + nome + "%' AND \"Cognome\" LIKE '%" + cognome + "%';" ;
			
			risultato = ricerca.executeQuery(statement);
			int size = 0;
			
			if(risultato.last()) {
				
				size = risultato.getRow();
				risultato.beforeFirst();

			}
			
			//vettore con al massimo quel numero di studenti 
			List<String>[] vettore = new List[size];
			Arrays.setAll(vettore, ArrayList :: new);
			ConnectionDao connectionDao = new ConnectionDao();
			
			while(risultato.next()) {
				
				ricercaCorsoId = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '" + risultato.getString(3) + "';";
				corsoId = perCorso.executeQuery(ricercaCorsoId);
				
				vettore[i].add(risultato.getString(1));
				vettore[i].add(risultato.getString(2));
				vettore[i].add(risultato.getString(3));
				
				if(corsoId.next()) {
					
					corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
					vettore[i].add(corsoById);
					
				}else
					vettore[i].add("Empty");
				
				vettore[i].add("ciao");
				vettore[i].add("ciao");
				vettore[i].add("ciao");
				i++;
				
			}

		return vettore;
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	return null;
	
}

public List<String>[] ricercaStudenteByNomeECf(Connection connection ,String nome ,String cf) {
	
	String statement;
	
	String ricercaCorsoId;
	Statement perCorso;
	ResultSet corsoId;
	String corsoById;
	
	
	try {
		
		Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet risultato;
		perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int i = 0;
		
			
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + nome + "%' AND \"Cf\" LIKE '%" + cf + "%';" ;
			
			risultato = ricerca.executeQuery(statement);
			int size = 0;
			
			if(risultato.last()) {
				
				size = risultato.getRow();
				risultato.beforeFirst();

			}
			
			//vettore con al massimo quel numero di studenti 
			List<String>[] vettore = new List[size];
			Arrays.setAll(vettore, ArrayList :: new);
			ConnectionDao connectionDao = new ConnectionDao();
			
			while(risultato.next()) {
				
				ricercaCorsoId = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '" + risultato.getString(3) + "';";
				corsoId = perCorso.executeQuery(ricercaCorsoId);
				
				vettore[i].add(risultato.getString(1));
				vettore[i].add(risultato.getString(2));
				vettore[i].add(risultato.getString(3));
				
				if(corsoId.next()) {
					
					corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
					vettore[i].add(corsoById);
					
				}else
					vettore[i].add("Empty");
				
				vettore[i].add("ciao");
				vettore[i].add("ciao");
				vettore[i].add("ciao");
				i++;
				
			}

		return vettore;
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	return null;
	
}

public List<String>[] ricercaStudenteByCognomeECf(Connection connection ,String Cognome ,String cf) {
	
	String statement;

	String ricercaCorsoId;
	Statement perCorso;
	ResultSet corsoId;
	String corsoById;
	
	
	try {
		
		Statement ricerca = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet risultato;
		perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		int i = 0;
		
			
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cognome\" LIKE '%" + Cognome + "%' AND \"Cf\" LIKE '%" + cf + "%';" ;
			
			risultato = ricerca.executeQuery(statement);
			int size = 0;
			
			if(risultato.last()) {
				
				size = risultato.getRow();
				risultato.beforeFirst();

			}
			
			//vettore con al massimo quel numero di studenti 
			List<String>[] vettore = new List[size];
			Arrays.setAll(vettore, ArrayList :: new);
			ConnectionDao connectionDao = new ConnectionDao();
			
			while(risultato.next()) {

				ricercaCorsoId = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '" + risultato.getString(3) + "';";
				corsoId = perCorso.executeQuery(ricercaCorsoId);
				
				vettore[i].add(risultato.getString(1));
				vettore[i].add(risultato.getString(2));
				vettore[i].add(risultato.getString(3));
				
				if(corsoId.next()) {
					
					corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
					vettore[i].add(corsoById);
					
				}else
					vettore[i].add("Empty");
				
				vettore[i].add("ciao");
				vettore[i].add("ciao");
				vettore[i].add("ciao");
				i++;
				
			}

		return vettore;
		
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	return null;
	
}

public List<String>[] ricercaStudenteByNomeEData(Connection connection ,String nome ,String data){
	
	String statement;
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	String corsoById;
	
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		ConnectionDao connectionDao = new ConnectionDao();
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		
		List[] vettoreStudenti = new List[size];
		Arrays.setAll(vettoreStudenti, ArrayList :: new);
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + nome + "%' AND \"Cf\" = '" + risultatoDate.getString(3) + "';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public List<String>[] ricercaStudenteByCfEData(Connection connection ,String cf ,String data){
	
	String statement;
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	String corsoById;
	
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		ConnectionDao connectionDao = new ConnectionDao();
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		
		List[] vettoreStudenti = new List[size];
		Arrays.setAll(vettoreStudenti, ArrayList :: new);
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cf\" LIKE '%" + cf + "%' AND \"Cf\" = '" + risultatoDate.getString(3) + "';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public List<String>[] ricercaStudenteByCognomeEData(Connection connection ,String cognome ,String data){
	
	String statement;
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	String corsoById;
	
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		ConnectionDao connectionDao = new ConnectionDao();
		List[] vettoreStudenti = new List[size];
		Arrays.setAll(vettoreStudenti, ArrayList :: new);
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cognome\" LIKE '%" + cognome + "%' AND \"Cf\" = '" + risultatoDate.getString(3) + "';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public List<String>[] ricercaStudenteByCognomeDataECf(Connection connection ,String cognome ,String data ,String cf) {
	
	String statement;
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	String corsoById;
	
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		ConnectionDao connectionDao = new ConnectionDao();
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		
		List[] vettoreStudenti = new List[size];
		Arrays.setAll(vettoreStudenti, ArrayList :: new);
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Cognome\" LIKE '%" + cognome + "%' AND \"Cf\" = '%" + cf + "%' AND \"Cf\" = '" + risultatoDate.getString(3) + "';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public List<String>[] ricercaStudenteByNomeDataECf(Connection connection ,String nome ,String data ,String cf) {
	
	String statement;
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	String corsoById;
	ConnectionDao connectionDao = new ConnectionDao();
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		
		List[] vettoreStudenti = new List[size];
		Arrays.setAll(vettoreStudenti, ArrayList :: new);
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + nome + "%' AND \"Cf\" LIKE '%" + cf + "%' AND \"Cf\" = '" + risultatoDate.getString(3) + "';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public List<String>[] ricercaStudenteByNomeDataECognome(Connection connection ,String nome ,String data ,String cognome) {
	
	String statement;
	String statementData = "SELECT \"DataIscrizione\",\"CorsoId\",\"Cf\" FROM \"Iscrizione\" WHERE TO_CHAR(\"DataIscrizione\" :: DATE,'YYYY-MM-DD') = TO_CHAR('" + data + "' :: DATE,'YYYY-MM-DD');";
	String corsoById;
	ConnectionDao connectionDao = new ConnectionDao();
	try {
		
		Statement ricercaData = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		int i = 0;
		
		ResultSet risultatoDate = ricercaData.executeQuery(statementData);
		
		int size = 0;
		while(risultatoDate.next()) {
			
			size++;
			
		}
		risultatoDate.beforeFirst();
		
		List[] vettoreStudenti = new List[size];
		Arrays.setAll(vettoreStudenti, ArrayList :: new);
		
		while(risultatoDate.next()) {
	
			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + nome + "%' AND \"Cognome\" LIKE '%" + cognome + "%' AND \"Cf\" = '" + risultatoDate.getString(3) + "';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			while(risultato.next()) {
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				corsoById = connectionDao.getCorsoDao().getNomeById(connection, risultatoDate.getString(2));
				vettoreStudenti[i].add(corsoById);
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		}
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public List<String>[] ricercaStudenteByNomeCfECognome(Connection connection ,String nome ,String cf ,String cognome) {
	
	String statement;
	String corsoById;
	ConnectionDao connectionDao = new ConnectionDao();
	try {
		

		Statement ricercaStudente = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			statement = "SELECT \"Nome\",\"Cognome\",\"Cf\" FROM \"Studente\" WHERE \"Nome\" LIKE '%" + nome + "%' AND \"Cognome\" LIKE '%" + cognome + "%' AND \"Cf\" LIKE '%" + cf + "%';";
			ResultSet risultato = ricercaStudente.executeQuery(statement);
			
			String qualcosa = null;
			String statementCorso;
			Statement perCorso = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet corsoId;
			
			int i = 0;
			int size = 0;
			while(risultato.next()) {
				
				size++;
				
			}
			risultato.beforeFirst();
			
			List[] vettoreStudenti = new List[size];
			Arrays.setAll(vettoreStudenti, ArrayList :: new);
			
			while(risultato.next()) {
				
				statementCorso = "SELECT \"CorsoId\" FROM \"Iscrizione\" WHERE \"Cf\" LIKE '%" + risultato.getString(3) + "%';";
				
				
				vettoreStudenti[i].add(risultato.getString(1));
				vettoreStudenti[i].add(risultato.getString(2));
				vettoreStudenti[i].add(risultato.getString(3));
				
				corsoId = perCorso.executeQuery(statementCorso);
				
				if(corsoId.next()) {
					
					
					corsoById = connectionDao.getCorsoDao().getNomeById(connection, corsoId.getString(1));
					vettoreStudenti[i].add(corsoById);
					
				}else {
					
					vettoreStudenti[i].add("Empty");
				}
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				vettoreStudenti[i].add("ciao");
				
				i++;
			}

			
		
		return vettoreStudenti;
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;

}

public void deleteStudente(Connection connection ,String cf) {

String delete = "DELETE FROM \"Studente\" WHERE \"Cf\" = '" + cf + "';";

try {
	
	Statement statement = connection.createStatement();
	statement.execute(delete);
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}

}
