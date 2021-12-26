package Oggetti.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Oggetti.AreaTematica;

public class AreaTematicaDaoImpl implements AreaTematicaDao{
	
	public void inserimento(AreaTematica tema ,Connection connection) {
		
		String statement = "INSERT INTO \"AreaTematica\" VALUES (" + "'" + tema.getNome() + "'" + ");";
		Statement inserimento = null;
		
		try {
			
			inserimento = connection.createStatement();
			inserimento.execute(statement);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(inserimento != null) {
				
				try {
					inserimento.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}

	public LinkedList<String> getThemes(Connection connection) {
		
		String statement = "SELECT * FROM \"AreaTematica\";";
		LinkedList<String> lista = new LinkedList<>();
		Statement inserimento = null;
		ResultSet result = null;
		
		try {
			
			inserimento = connection.createStatement();
			result = inserimento.executeQuery(statement);
			
			while(result.next()) {
				
				lista.add(result.getString(1));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(inserimento != null) {
				
				try {
					inserimento.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(result != null) {
				
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			return lista;
		}
		
		
	}
	
	public void update(Connection connection ,String oldTheme ,String newTheme) {
		
		String update = "UPDATE \"AreaTematica\" SET \"Nome\" = '" + newTheme + "' WHERE \"Nome\" = '" + oldTheme + "';";
		Statement statement = null;
		
		try {
			
			statement = connection.createStatement();
			statement.execute(update);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}finally {
			
			if(statement != null) {
						
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
	
	public void delete(Connection connection ,String nome) {
		
		String delete = "DELETE FROM \"AreaTematica\" WHERE \"Nome\" = '" + nome + "';";
		
		Statement statement = null;
		try {
			
			statement = connection.createStatement();
			statement.execute(delete);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(statement != null) {
				
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
}
