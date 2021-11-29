package Oggetti.DAO;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

public interface CorsoETemaDao {
	
	public void inserimento(Connection connection ,int corsoId ,String nomeTema);
	public LinkedList<String> ricercaCorsoByTheme(Connection connection ,String theme);
	public List<String> getAllThemeOfCorso(Connection connection ,String id);
	public void deleteCollegamento(Connection connection ,String id);
	
}
