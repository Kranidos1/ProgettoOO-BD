package Oggetti.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CorsoETemaDaoImpl implements CorsoETemaDao {

	public void inserimento(Connection connection ,int corsoId ,String nomeTema) {
		
		String statement = "INSERT INTO \"Corso-Tema\" (\"CorsoId\",\"NomeTema\") VALUES ("+ corsoId + "," + "'" + nomeTema + "'" + ");";
		
		try {
			
			Statement inserimento = connection.createStatement();
			inserimento.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
