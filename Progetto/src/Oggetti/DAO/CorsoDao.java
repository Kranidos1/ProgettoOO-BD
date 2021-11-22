package Oggetti.DAO;

import java.sql.Connection;

import Oggetti.Corso;

public interface CorsoDao {
public void inserimento(Corso corso ,Connection connection);
public int getNextCorsoId(Connection connection);
public int trovaCorsoId(Connection connection ,String nomeCorso);

}
