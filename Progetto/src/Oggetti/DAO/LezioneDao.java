package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;

import Oggetti.Lezione;

public interface LezioneDao {

	public void inserimentoLezione(Connection connection ,Lezione lezione) ;
	public int recuperaIdUltimaInserita(Connection connection);
	
	//ora prende anche LezioneId evitando di fare un altra query per prendere l'id.Rimango la funzione getlezioneIdByData in quanto potrebbe servire
	public List<String> getDateLezioniDaGestireELezione(Connection connection ,int corsoId);
	//dato che non ci sono costi per le query la rimango,piu' facile da gestire.
	public int getLezioneIdByData(Connection connection ,String data);
	public void updateCheck(Connection connection ,int lezioneId);
	public int gestioneDuplicati(Connection connection ,String data ,int corsoId);
	public int gestioneDuplicatiUpdate(Connection connection ,String data ,int corsoId);
	public void updateLezione(Connection connection ,Lezione lezione,int lezioneId);
	public int countLezioni(Connection connection ,int corsoId);
	public int countCheckFalse(Connection connection ,int corsoId);
	public List getLezioniByCorsoId(Connection connection ,int corsoId);
	public int countPresenti(Connection connection ,int lezioneId);
	
}
