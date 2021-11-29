package Oggetti.DAO.Exceptions;

import javax.swing.JOptionPane;

public class DaoExceptions extends Exception {
	
	public void pkPresente(String Message) {
		
		JOptionPane.showMessageDialog(null, "Studente gia' presente nei database,ma inserito comunque nel corso indicato.", "Invalid input", JOptionPane.ERROR_MESSAGE);
		
	}
	
}
