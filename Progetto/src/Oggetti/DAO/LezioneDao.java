package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;

import Frames.Lezione;

public interface LezioneDao {

	public void inserimentoLezione(Connection connection ,Lezione lezione) ;
	public int recuperaIdUltimaInserita(Connection connection);
	public List<String> getDateLezioniDaGestire(Connection connection ,int corsoId);
	public int getLezioneIdByData(Connection connection ,String data);
	
}
