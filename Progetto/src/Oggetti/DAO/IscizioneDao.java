package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;

public interface IscizioneDao {
	
	public void inserimento(Connection connection ,int CorsoId ,String Cf ,String data);
	public int controlloDuplicati(Connection connection ,String cf ,int corsoId);
	public List<String>[] getStudentiByCorsoName(Connection connection ,String nomeCorso);
	public List<String>[] getStudentiByCorsoId(Connection connection ,int corsoId);
	public void deleteStudente(Connection connection ,String cf ,int corsoId);
	public int countStudentiIscritti(Connection connection ,int corsoId);
	public void updateStatoStudente(Connection connection ,String cf ,String valutazione);
	public String getPromozioneStudente(Connection connection ,String cf ,int corsoId);
	
}
