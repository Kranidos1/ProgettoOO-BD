package Oggetti.DAO;

import java.sql.Connection;
import java.util.LinkedList;

public interface CorsoETemaDao {
	public void inserimento(Connection connection ,int corsoId ,String nomeTema);
	public LinkedList<String> ricercaCorsoByTheme(Connection connection ,String theme);
}
