package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;

public interface PresenzaDao {

	public void inserimentoAssociazioneConStudenti(Connection connection ,int corsoId ,int lezioneId);
	public List<String> getStudentiCfByLezioneId(Connection connection ,int lezioneId);
	public void updatePresenzaStudente(Connection connection ,int lezioneId ,String cf ,String presenza);
	public String checkPresenzaStudente(Connection connection ,String cf ,int lezioneId);
	public int countPresenti(Connection connection ,int lezioneId);
	
}
