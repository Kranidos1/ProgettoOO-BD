package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;

public interface IscizioneDao {
	
	public void inserimento(Connection connection ,int CorsoId ,String Cf ,String data);
	public int controlloDuplicati(Connection connection ,String cf ,int corsoId);
	public List<String>[] getStudentiByCorsoName(Connection connection ,String nomeCorso);
	
}
