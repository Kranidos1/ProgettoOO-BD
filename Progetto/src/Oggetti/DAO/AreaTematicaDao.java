package Oggetti.DAO;

import java.sql.Connection;
import java.util.List;

import javax.swing.DefaultListModel;

import Oggetti.AreaTematica;

public interface AreaTematicaDao {

	public void inserimento(AreaTematica tema ,Connection connection);
	public List<String> getThemes(Connection connection);
	public void update(Connection connection ,String oldTheme ,String newTheme);
	public void delete(Connection connection ,String nome);
	
}
