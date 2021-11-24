package Oggetti.DAO;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

import Oggetti.Corso;

public interface CorsoDao {
	
public void inserimento(Corso corso ,Connection connection);
public int getNextCorsoId(Connection connection);
public int trovaCorsoId(Connection connection ,String nomeCorso);
public List<String> getNomiCorsiByKey(Connection connection ,String key);
public LinkedList<String> getNomiById(Connection connection ,LinkedList<String> list);
public LinkedList<String> getCorsiTramiteKeyETema(Connection connection ,String key ,String theme);

}
