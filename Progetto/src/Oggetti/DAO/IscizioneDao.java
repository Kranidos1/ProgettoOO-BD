package Oggetti.DAO;

import java.sql.Connection;

public interface IscizioneDao {
	public void inserimento(Connection connection ,int CorsoId ,String Cf ,String data);
	public int controlloDuplicati(Connection connection ,String cf ,int corsoId);
	
}
