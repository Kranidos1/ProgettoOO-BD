package Oggetti.DAO;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import Oggetti.Corso;

public interface CorsoDao {
	
public int inserimento(Corso corso ,Connection connection);
public int controlloDuplicati (Connection connection ,String nome);
public int getNextCorsoId(Connection connection);
public String getNomeById(Connection connection ,String id);
public List<String> getNomiCorsi(Connection connection);
public int trovaCorsoId(Connection connection ,String nomeCorso);
public List<String> getNomiCorsiByKey(Connection connection ,String key);
public LinkedList<String> getNomiById(Connection connection ,LinkedList<String> list);
public LinkedList<String> getCorsiTramiteKeyETema(Connection connection ,String key ,String theme);
public void deleteCorsoByName(Connection connection ,String nome);
//\"Nome\",\"Descrizione\",\"MaxPartecipanti\",\"MinPartecipazione\",\"CorsoId\"
public Corso getCorso(Connection connection ,String nome);
public void updateCorso(Connection connection ,Corso corso);
public void updateCheckCorso(Connection connection ,int corsoId);
public List getNomeCorsiFiniti(Connection connection );
public void updateCheckCorsoGestito(Connection connection ,int corsoId);
public LinkedList getNomeCorsiArchiviati(Connection connection );

}
