package Oggetti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;

import Oggetti.AreaTematica;

public class AreaTematicaDaoImpl implements AreaTematicaDao{
	
	public void inserimento(AreaTematica tema ,Connection connection) {
		
		String statement = "INSERT INTO \"AreaTematica\" VALUES (" + "'" + tema.getNome() + "'" + ");";
		
		try {
			
			Statement inserimento = connection.createStatement();
			inserimento.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public LinkedList<String> getThemes(Connection connection) {
		
		String statement = "SELECT * FROM \"AreaTematica\";";
		LinkedList<String> lista = new LinkedList<>();
		
		try {
			
			Statement inserimento = connection.createStatement();
			ResultSet result = inserimento.executeQuery(statement);
			
			while(result.next()) {
				
				lista.add(result.getString(1));
				
			}
			
			return lista;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public void update(Connection connection ,String oldTheme ,String newTheme) {
		
		String update = "UPDATE \"AreaTematica\" SET \"Nome\" = '" + newTheme + "' WHERE \"Nome\" = '" + oldTheme + "';";
		
		try {
			
			Statement statement = connection.createStatement();
			statement.execute(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void delete(Connection connection ,String nome) {
		
		String delete = "DELETE FROM \"AreaTematica\" WHERE \"Nome\" = '" + nome + "';";
		
		Statement statement;
		try {
			
			statement = connection.createStatement();
			statement.execute(delete);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
