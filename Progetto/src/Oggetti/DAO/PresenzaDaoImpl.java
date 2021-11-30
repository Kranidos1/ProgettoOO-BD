package Oggetti.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class PresenzaDaoImpl {
	
	public void inserimentoAssociazioneConStudenti(Connection connection ,int corsoId ,int lezioneId) {
		String cf;
		String insert;
		IscrizioneDaoImpl iscrizioneDao = new IscrizioneDaoImpl();
		List<String>[] studenti = iscrizioneDao.getStudentiByCorsoId(connection, corsoId);
		
		try {
			
			Statement statement = connection.createStatement();
			int  i = 0;
			
			while(i < Arrays.asList(studenti).size()) {
				
				cf = studenti[i].get(2).substring(1, studenti[i].get(2).length()-1);
				
				insert = "INSERT INTO \"Presenza\" (\"LezioneId\",\"Cf\",\"Check\",\"Presente\") VALUES ('" + lezioneId + "','" + cf +"','" + false + "','X');";
				statement.execute(insert);
				i++;
				
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
