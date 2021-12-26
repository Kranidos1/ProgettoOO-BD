package Frames;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import Oggetti.AreaTematica;
import Oggetti.Corso;
import Oggetti.Lezione;
import Oggetti.Studente;
import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;

import Oggetti.DAO.CorsoETemaDaoImpl;
import Oggetti.DAO.IscrizioneDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;
import Oggetti.DAO.PresenzaDaoImpl;
import Oggetti.DAO.StudenteDaoImpl;
import java.awt.Component;
public class Controller implements ControlloEOperazioniSuFrame {
		
	private static final char[] corsoFormattato = null;
	private int j = 0;
	private ConnectionDao connectionDao;
		
	
	public Controller() {
		
		connectionDao = new ConnectionDao();
		connectionDao.setConnection(connectionDao.createConnection());
		
	}
	
	public ConnectionDao getConnectionDao() {
		return connectionDao;
	}
	
	public void setConnectionDao(ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}
	
	public void closeConnection() {
		try {
			if(getConnectionDao().getConnection() != null) {
				
				getConnectionDao().getConnection().close();				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int controlloField(char h) {

		
		if(h == '`') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '~') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '!') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '@') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '#') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '$') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '%') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '^') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '&') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '(') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == ')') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '-') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '=') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '{') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '}') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '[') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == ']') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '\\') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == '|') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == ':') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
			
		}
		
		if(h == ';') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == '"') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == '\'') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == '<') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == '>') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == ',') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == '?') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		
		if(h == '/') {
			
			JOptionPane.showMessageDialog(null, "Non sono ammessi caratteri speciali come : \n \"\\\\\" ,\"^\" ,\"$\" ,\"{\",\"}\",\"[\",\"]\",\"(\",\")\",\".\",\"*\",\"+\",\"?\",\"|\",\"<\",\">\",\"-\",\"&\",\"%\".\",\"'\"", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
			return 1;
						
		}
		return 0;
		
		
	}
	
	public boolean isWhatYouWant(String input, int flag) {
		// TODO Auto-generated method stub
		// FLAG 0 STRINGHE 1 INT
		if(flag == 0) {
		    char[] tmp = input.toCharArray();

		    for (char c : tmp) {
		        if(!Character.isLetter(c)) {
		            return false;
		        }
		    }

		    return true;
			
		}else 
			
			  for (int i = 0; i < input.length(); i++) {
				   char ch = input.charAt(i);
				   if (!Character.isDigit(ch)) {
				    return false;
				   }
				  }

				  return true;
	}

	public String escape(String input){
	    final String[] specialChar = {"\\","^","$","{","}","[","]","(",")",".","*","+","?","|","<",">","-","&","%"};

	    for (int i = 0 ; i < specialChar.length ; i++){
	        if(input.contains(specialChar[i])){
	            input = input.replace(specialChar[i],"\\"+specialChar[i]);
	        }
	    }
	    return input;
	}
	

	public void newTheme(JLabel label) {
		String theme = JOptionPane.showInputDialog("New Theme");
		
		int i = 0;
		
		//TODO Funzione add theme nel db
		//CONTROLLO SE LA PAGINA E' CHIUSA O SE VIENE DATO UN INPUT VUOTO
		boolean control;
		int flag = 0;
		JFrame tmpFrame = (JFrame) SwingUtilities.getRoot(label);
		if(theme != null) {
			
			int value = 0;
			
			while(i < theme.length()) {
				
				value = controlloField(theme.charAt(i));
				
				if(value == 1) {
					
					return;
					
				}
				
				i++;
			}
			System.out.println("ciao");
			if(theme.length() != 0) {
				
				control = isWhatYouWant(theme,0);
				
				while(control == false || theme.length() == 0) {
					
					JOptionPane.showMessageDialog(tmpFrame ,"Only character admitted." ,"Input Error" ,JOptionPane.ERROR_MESSAGE);
					theme = JOptionPane.showInputDialog("New Theme");
					
					if(theme != null) {
						if(theme.length() != 0) {
							control = isWhatYouWant(theme,0);
						}
					}else {
						flag = 1;
						break;
					}
					
				}
				//Inserimento nel db
				if(flag == 0) {
					AreaTematica tema = new AreaTematica();
					tema.setNome(theme);
					//TODO INSERIMENTO EFFETTIVO NEL DB

					connectionDao.getAreaTematicaDao().inserimento(tema ,connectionDao.getConnection());
					
				}else {
					//GESTISCE CHIUSURA NELL'ERRORE
					JOptionPane.showMessageDialog(tmpFrame, "You didn't insert anything.");
				}
				
			}else {
				//GESTISCE INVIO VUOTO ALL'INIZIO
				JOptionPane.showMessageDialog(tmpFrame, "You didn't insert anything.");
			}
		}else {
			//GESTISCE CHIUSURA ALL'INIZIO
			JOptionPane.showMessageDialog(tmpFrame, "You didn't insert anything.");
		}
	}
	
	

	public void jpanelManagementCreaCorsoFrame(JFrame fram ,JTextArea inputArea ,JTextField inputField ,int flag) {
		
		//SI RIFERISCE A FLAG 0 = a, 1 = b , 2 = c , 3 = d , dal 4 in poi per iscrivistudenteframe
		switch(flag) {
		case 0:
			//NOME
			JOptionPane.showMessageDialog(fram, "Invalid Name", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 1:
			//NUMERIMAX
			JOptionPane.showMessageDialog(fram, "Invalid NumberMax Format", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 2:
			//NUMERIMIN
			JOptionPane.showMessageDialog(fram, "Invalid NumberMin Format", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 3:		
			//DESCRIZIONE
			JOptionPane.showMessageDialog(fram, "Empty Description.Invalid Description Format", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 4:
			//COGNOME
			JOptionPane.showMessageDialog(fram, "Invalid Surname.", "Invalid Surname", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 5:
			//DATA
			JOptionPane.showMessageDialog(fram, "Invalid Date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 6:
			//CF
			JOptionPane.showMessageDialog(fram, "Invalid CF.", "Invalid CF", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 7:
			//FIELD GENERICO
			JOptionPane.showMessageDialog(fram, "Empty Fields.Please,insert something.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			break;
		case 8:
			//Invalid input
			JOptionPane.showMessageDialog(fram, "Invalid Input.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			break;
		case 9:
			//INVALID MAX E MIN
			JOptionPane.showMessageDialog(fram, "Max < Min that's not possible.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			break;
		case 10:
			//nessuna scelta dalla list
			JOptionPane.showMessageDialog(fram, "You didn't select anything from the list.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	
	public int textEnableDisable(JButton button ,JFrame fram ,int count ,JTextField field ,JPanel pabel) {

		if(field.isEnabled()) {
			
			count = count + 1;
			field.setEnabled(false);
			pabel.setEnabled(false);
			button.setEnabled(false);
			
			return count;
		}
			if(count <2) {
				
				count += 1;
				field.setEnabled(true);
				pabel.setEnabled(true);
				button.setEnabled(true);
				
				return count;
				
			}else {
				
			field.setEnabled(false);
			pabel.setEnabled(false);
			button.setEnabled(false);
			
			JOptionPane.showMessageDialog(fram,"You can't use this field twice.", "New Theme error.", JOptionPane.ERROR_MESSAGE);
			return 3;
			
		}
	}

	//0 per insert // 1 update
	public void insertCorsoDb(JFrame fram ,JTextField nome ,JTextField max ,JTextField min ,JTextArea areaDescrizione ,DefaultListModel<String> model ,int flag ,String corsoId) {
		

		//FLAG 0 PER INSERIMENTO DA 0 1 PER UPDATE
		String name,maxString,minString;
		boolean a,b,c,d,v,u;
		a = nome.getText().isEmpty();
		name = nome.getText();
		
		b = max.getText().isEmpty();
		maxString = max.getText();
		v = isWhatYouWant(maxString,1);
		
		c = min.getText().isEmpty();
		minString = min.getText();
		u = isWhatYouWant(minString,1);
		
		d = areaDescrizione.getText().isEmpty();
		
		if(a == false) {
			if(name.length() < 40) {
			if(b == false && v == true) {
				if(c == false && u == true) {
						
					if(areaDescrizione.getText() != null) {
						if(areaDescrizione.getText().length() > 1280) {
							JOptionPane.showMessageDialog(null, "Descrizione troppo lunga", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}
					if( Integer.parseInt(maxString) > Integer.parseInt(minString) ) {
						
						//TODO SALVATAGGIO EFFETTIVO INSERIMENTO DB ricorda di estrarre stringhe dall'area tematica e aggiungerle una a una nell'oggetto desiderato.
						//INSERIMENTO ELEMENTI LIST
						List<String> tmp = new ArrayList<String>();

						int i = 0;
						int size = model.getSize();
						
						while(i < size) {
							tmp.add(model.getElementAt(i).toString());
							i++;
						}
						//EFFETTIVA AGGIUNTA TODO
						Corso corso = new Corso();

						corso.setNome(name);
						corso.setMaxPartecipanti(Integer.parseInt(maxString));
						corso.setMinPartecipazione(Integer.parseInt(minString));
						
						if(d != true) {
							
							corso.setDescrizione(areaDescrizione.getText());
							
						}
						
						//INSERIMENTO EFFETTIVO DELCORSO
						
						if(flag == 0) {
							int process = connectionDao.getCorsoDao().inserimento(corso, connectionDao.getConnection());
								
							if(process == 1) {
								
								Timer timer = new Timer(1000,new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										// TODO Auto-generated method stub

										while(j < tmp.size()) {
											
											connectionDao.getCorsoTemaDao().inserimento(connectionDao.getConnection(), connectionDao.getCorsoDao().getNextCorsoId(connectionDao.getConnection()), tmp.get(j));
											j ++;
											
										}
										
									}		
								});	
								timer.setRepeats(false);
								timer.start();
								
							}
							
							fram = (JFrame)SwingUtilities.getRoot(areaDescrizione);
							
							fram.setVisible(false);
							
							new FrameDiScelta();
							
						}else
							if(flag == 1) {
								
								connectionDao.getCorsoDao().updateCorso(connectionDao.getConnection(), corso);
								
								fram = (JFrame)SwingUtilities.getRoot(areaDescrizione);
								
								JOptionPane.showMessageDialog(fram, "Updated!", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
								
								fram.setVisible(false);
								new FrameDiScelta();
							}
						
					}else
						jpanelManagementCreaCorsoFrame(fram,null,null,9);
						
				}else
					jpanelManagementCreaCorsoFrame(fram,null,min,2);
			}else
				jpanelManagementCreaCorsoFrame(fram,null,max,1);
				
		}else
			JOptionPane.showMessageDialog(fram, "Nome troppo lungo!", "Invalid input", JOptionPane.ERROR_MESSAGE);
		}else
			jpanelManagementCreaCorsoFrame(fram,null ,nome ,0);	
		
	}
	

	public void insertNewThemeFromField(JFrame fram ,JTextField field ,DefaultListModel<String> model) {
		
	
		
		String theme = field.getText();
		boolean control , onlychar;
		//controllo field theme vuoto e anche numerico
		control = theme.isEmpty();
		
		if(control == true) {
			
			JOptionPane.showMessageDialog(fram, "Empty Description,write something.", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
			
		}else {
			
			onlychar = isWhatYouWant(theme, 0);
			
			if(onlychar == true) {
				
				model.addElement(theme);
				field.setEnabled(false);
				
				AreaTematica tema = new AreaTematica();
				tema.setNome(theme);

				
				connectionDao.getAreaTematicaDao().inserimento(tema ,connectionDao.getConnection());
							
			}else {
				
				JOptionPane.showMessageDialog(fram, "Invalid input,only char admitted.", "Invalid input", JOptionPane.ERROR_MESSAGE);
				field.setText("");
				
			}		
		}
	}
	

	public void insertInListAndControl(DefaultListModel<String> model ,String control ,JFrame fram) {
		
		//CONTROLLO DUPLICATI PRIMA DELL'AGGIUNTA
		int size = model.getSize();
		int i ;
		int j = 0;
		String compare;
		
		for(i = 0 ; i<size; i++) {
			
			compare = model.getElementAt(i).toString();
			if(compare.equals(control)) {
				
				JOptionPane.showMessageDialog(fram, "You can't insert same item twice.", "Invalid input", JOptionPane.ERROR_MESSAGE);
				j = 1;
				
			}
		}
		
		if(j == 0) {
			
			model.addElement(control);
			
		}
		
	}
	

	public int controlloCF(JTextField cfTextField ,JLabel cfLabel) {
		
		String tmpCf = cfTextField.getText();
		
		if(tmpCf.length() == 16) {
			char a;
			int i = 0;
			while(i < 16) {
				a = tmpCf.charAt(i);
				
				if(i < 6) {
					if(!Character.isLetter(a)) {
						//SBAGLIATO INPUT
						jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
						return 0;
					}
				}
				
				if( i == 6 || i == 7) {
					if(!Character.isDigit(a)) {
						//SBAGLIATO INPUT
						jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
						return 0;
					}
				}
				
				if( i == 8) {
					if(!Character.isLetter(a)) {
						//SBAGLIATO INPUT
						jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
						return 0;
					}
				}
				
				if( i == 9 || i == 10) {
					if(!Character.isDigit(a)) {
						//SBAGLIATO INPUT
						jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
						return 0;
					}
					
					if( i == 11) {
						if(!Character.isLetter(a)) {
							//SBAGLIATO INPUT
							jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
							return 0;
						}
					}	
					
					if( i > 11 && i < 15) {
						if(!Character.isDigit(a)) {
							//SBAGLIATO INPUT
							jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
							return 0;
						}
					}
					
					if( i == 15) {
						if(!Character.isLetter(a)) {
							//SBAGLIATO INPUT
							jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLabel), null, cfTextField, 6);
							return 0;
						}
					}
					
				}
				
				i++;
				
			}
			return 1;
		}else
			return 0;
		
		
	}
	
	@Override
	public void controlloInserimentoStudente(JTextField nomeField , JTextField cognomeField ,JList list ,JDateChooser dateChooser ,JLabel cfLab ,JTextField cfField){
		
		String tmpNome = nomeField.getText();
		String tmpCognome = cognomeField.getText();

	if(list.getSelectedValue()!= null) {
			
			String tmpCorso = list.getSelectedValue().toString();
			
				if(!tmpNome.isEmpty()) {
					if(isWhatYouWant(tmpNome, 0)) {
						
						if(tmpNome.length() > 40) {
							JOptionPane.showMessageDialog(null, "Nome troppo lungo", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
						}
						
						if(!tmpCognome.isEmpty()) {
							if(isWhatYouWant(tmpCognome, 0)) {
								
								if(tmpCognome.length() > 40) {
									JOptionPane.showMessageDialog(null, "Cognome troppo lungo", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
								}
								
								Date date;
								String dbDate;
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								date = dateChooser.getDate();
	//							dbDate = sdf.format(date);
										//controllo data ma prima cf
										if(controlloCF(cfField, cfLab) == 1) {
											//PRENDI ANCHE DATA ATTUALE
											Date dataAttuale = new Date();
											String AttualeData = sdf.format(date).toString();
											//TODO 
											//Inserimento
											/////INSERIMENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO dbDate
											
											Studente studente = new Studente();
	
											
											int corsoId = connectionDao.getCorsoDao().trovaCorsoId(connectionDao.getConnection(), tmpCorso);
											
											studente.setCF(cfField.getText().toString().toUpperCase());
											studente.setNome(tmpNome.toUpperCase());
	
											studente.setCognome(tmpCognome.toUpperCase());
											studente.setData(sdf.format(date));
											studente.setDataIscrizione(sdf.format(dataAttuale));
											
											int process = connectionDao.getStudenteDao().inserimento(connectionDao.getConnection(), studente);
											
											if(process == 1) {
												
												
												int procedere = connectionDao.getIscrizioneDao().controlloDuplicati(connectionDao.getConnection(), studente.getCF(), corsoId);
												
												if(procedere == 1) {
													
													connectionDao.getIscrizioneDao().inserimento(connectionDao.getConnection(), corsoId, studente.getCF() ,studente.getDataIscrizione());
													
													JOptionPane.showMessageDialog((JFrame) SwingUtilities.getRoot(cfLab) ,"Inserito lo studente " + studente.getCF() + ".", "Ok!", JOptionPane.INFORMATION_MESSAGE);
													nomeField.setText("");
													cognomeField.setText("");
													cfField.setText("");
													
												}else
													
													JOptionPane.showMessageDialog((JFrame) SwingUtilities.getRoot(cfLab) ,"Studente gia' presente nei database per questo corso.", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
												
											}
											
											
										}else
											jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, cfField, 6);
							}else
								jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, cognomeField, 4);
						}else
							jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, null, 7);			
					}else
						jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, nomeField, 0);
				}else
					jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, null, 7);
			}else
				jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, null, 7);

	}
	

	
	public int isEnbl(JTextField field ,int value ,JDateChooser date ,JList list ) {
		
			if(field != null) {
				if(field.isEnabled()) {
					
					field.setEnabled(false);
					field.setText("");
					value -= 1;
					return value;
					
				}else {
					
					field.setEnabled(true);
					value += 1;
					return value;
					
				}
			}else 
				if(date != null) {
					{
						
						if(date.isEnabled()) {
							date.setEnabled(false);
							value -= 1;
							return value;	
						}else {
							date.setEnabled(true);
							value += 1;
							return value;
						}
					}
				}else {
					
					if(list.isEnabled()) {
						
						list.setEnabled(false);
						value -= 1;
						return value;
						
					}else {
						
						list.setEnabled(true);
						value += 1;
						return value;
						
					}
				}
			
	}
	
	public void ricercaStudente(JTextField nome ,JTextField cognome ,JTextField cf ,JDateChooser dataDateChooser ,JTable table ,int flagNome ,int flagCognome ,int flagCf ,int flagDate ,JLabel label ,DefaultTableModel model) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tmpNome = nome.getText();
		String tmpCognome = cognome.getText();
		String tmpCf = cf.getText();
		//controllo per invocazione tostringgetdate
		
		String tmpDate = null;
		if(dataDateChooser.getDate() != null) {
			tmpDate = sdf.format(dataDateChooser.getDate());
		}
		//LA DATA NON VA CONTROLLATA,GIA CONTROLALTA PRIMA

		//CASO TUTTI 0 
		if(flagNome == 0 && flagCognome == 0 && flagCf == 0 && flagDate == 0) {
			//errore generico
			jpanelManagementCreaCorsoFrame(null ,null ,null ,7);
		}
		
		//SOLO DATA
		if(flagNome == 0 && flagCognome == 0 && flagCf == 0 && flagDate == 1) {
			
			Vector[] listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByDataIscrizione(connectionDao.getConnection(), tmpDate);
			
			int sizeLista = Arrays.asList(listaStudenti).size();
			int i = 0;
			String presenza;
			
			while(i < sizeLista) {
				
				String cfFormatted = listaStudenti[i].get(2).toString();
				
				if(listaStudenti[i].get(3).toString().equals("Empty")) {
					listaStudenti[i].add("X");
					listaStudenti[i].add("X");
					model.addRow(listaStudenti[i]);
					i++;
				}else {
					
					String nomeCorso = listaStudenti[i].get(3).toString();
					
					Corso corsoCompleto = new Corso();
					corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
					
					int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
					
					
					List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
					
					int j = 0;
					int count = 0;
					
					while(j < lezioniIdList.size()) {
						
						presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
						
						if(presenza != null) {
							
							if(presenza.equals("Presente")) {
								
								count++;
								
							}
							
						}
						
						j++;
					}
					
					if(nLezioni != 0) {
						
						listaStudenti[i].add(Integer.toString(count) + "/" + nLezioni);
						
						if(count != 0) {
							
							int percentualeMinima = (count * 100) / nLezioni;
							
							
							listaStudenti[i].add(percentualeMinima+"%");
							
						}else {
							
							listaStudenti[i].add("0");
							
						}
						
					}else {
						listaStudenti[i].add("X");
						listaStudenti[i].add("X");
					}
					model.addRow(listaStudenti[i]);
					i++;
					
				}
				
				
			}

			
		}
		
		//SOLO CF
		if(flagNome == 0 && flagCognome == 0 && flagCf == 1 && flagDate == 0) {
			//erroreerororororororo
			
			
			if(tmpCf != null) {
					//ricerca per cf
					
					
					Vector[][] listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByCf(connectionDao.getConnection(), tmpCf.toUpperCase());
					
					int sizeLista = Arrays.asList(listaStudenti).size();
					int i = 0;
					
					
					while(i < sizeLista) {

						if(listaStudenti[i][3].toString().substring(1, listaStudenti[i][3].toString().length()-1).equals("Empty")) {
							
							
							
							listaStudenti[i][4] = new Vector();
							listaStudenti[i][4].add("X");
							listaStudenti[i][5] = new Vector();
							listaStudenti[i][5].add("X");
							model.addRow(listaStudenti[i]);
							i++;
						}else {
							
							String nomeCorso = listaStudenti[i][3].toString().substring(1, listaStudenti[i][3].toString().length()-1);
							Corso corsoCompleto = new Corso();
							
							corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);

							
							listaStudenti[i][4] = new Vector();
							listaStudenti[i][5] = new Vector();
							int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
							
							String presenza;
							String cfFormatted = listaStudenti[i][2].toString().substring(1, listaStudenti[i][2].toString().length()-1);
							
							List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
							
							int j = 0;
							int count = 0;
							
							while(j < lezioniIdList.size()) {
								
								presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
								
								if(presenza != null) {
									
									if(presenza.equals("Presente")) {
										
										count++;
										
									}
									
								}
								
								j++;
							}

							
							if(nLezioni != 0) {
								
								
								listaStudenti[i][4].add(Integer.toString(count) + "/" + nLezioni);
								
								if(count != 0) {
									
									int percentualeMinima = (count * 100) / nLezioni;
									
									
									listaStudenti[i][5].add(percentualeMinima+"%");
									
								}else {
									
									
									listaStudenti[i][5].add("0");
									
								}
								
							}else {
								listaStudenti[i][4].add("X");
								listaStudenti[i][5].add("X");
							}
							model.addRow(listaStudenti[i]);
							i++;
						}
						
					}
					
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
			
		}
		
		//SOLO COGNOME
		if(flagNome == 0 && flagCognome == 1 && flagCf == 0 && flagDate == 0) {
			//erroreerororororororo
			if(tmpCognome != null) {
				if(isWhatYouWant(tmpNome ,0)) {
					//comando ricerca
					
					
					Vector[] listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByCognome(connectionDao.getConnection(), tmpCognome.toUpperCase());
					
					int sizeLista = Arrays.asList(listaStudenti).size();
					int i = 0;
					String presenza;
					
					while(i < sizeLista) {
						
						String cfFormatted = listaStudenti[i].get(2).toString();
						
						if(listaStudenti[i].get(3).toString().equals("Empty")) {
							listaStudenti[i].add("X");
							listaStudenti[i].add("X");
							model.addRow(listaStudenti[i]);
							i++;
						}else {
							
							String nomeCorso = listaStudenti[i].get(3).toString();
							
							Corso corsoCompleto = new Corso();
							corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);

							
							int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
							
							List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
							
							int j = 0;
							int count = 0;
							
							while(j < lezioniIdList.size()) {
								
								presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
								
								if(presenza != null) {
									
									if(presenza.equals("Presente")) {
										
										count++;
										
									}
									
								}
								
								j++;
							}
							
							if(nLezioni != 0) {
								
								listaStudenti[i].add(Integer.toString(count) + "/" + nLezioni);
								
								if(count != 0) {
									
									int percentualeMinima = (count * 100) / nLezioni;
									
									
									listaStudenti[i].add(percentualeMinima+"%");
									
								}else {
									
									listaStudenti[i].add("0");
									
								}
								
							}else {
								listaStudenti[i].add("X");
								listaStudenti[i].add("X");
							}
							
							model.addRow(listaStudenti[i]);
							i++;
							
						}
						
						
					}
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
			
		}
		
		
		//SOLO NOME //FATTOTOOTTOTOTOTO
		if(flagNome == 1 && flagCognome == 0 && flagCf == 0 && flagDate == 0) {
			//erroreerororororororo
			if(tmpNome != null) {
				
				if(isWhatYouWant(tmpNome ,0)) {
					
					
					//comando ricerca
				
					Vector[] listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByName(connectionDao.getConnection(), tmpNome.toUpperCase());
					
					int sizeLista = Arrays.asList(listaStudenti).size();
					
					int i = 0;
					String presenza;
					
					while(i < sizeLista) {
						
						
						String cfFormatted = listaStudenti[i].get(2).toString();
						
						if(listaStudenti[i].get(3).toString().equals("Empty")) {
							listaStudenti[i].add("X");
							listaStudenti[i].add("X");
							model.addRow(listaStudenti[i]);
							i++;
						}else {
							
							
							String nomeCorso = listaStudenti[i].get(3).toString();
							
							Corso corsoCompleto = new Corso();
							corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
							
							int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
							

							
							List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
							
							int j = 0;
							int count = 0;
							
							while(j < lezioniIdList.size()) {
								
								presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
								
								if(presenza != null) {
									
									if(presenza.equals("Presente")) {
										
										count++;
										
									}
									
								}
								
								j++;
							}
							
							if(nLezioni != 0) {
								
								listaStudenti[i].add(Integer.toString(count) + "/" + nLezioni);
								
								if(count != 0) {
									
									int percentualeMinima = (count * 100) / nLezioni;
									
									
									listaStudenti[i].add(percentualeMinima+"%");
									
								}else {
									
									listaStudenti[i].add("0");
									
								}
								
							}else {
								listaStudenti[i].add("X");
								listaStudenti[i].add("X");
							}
							
							model.addRow(listaStudenti[i]);
							
							
						}
						i++;
					}
					
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			
		}
		
		//DATA E CF
		if(flagNome == 0 && flagCognome == 0 && flagCf == 1 && flagDate == 1) {

			if(tmpCf != null) {
					//ricerca per cf
					
	
					List<String>[] listaStudenti;
					listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByCfEData(connectionDao.getConnection(), tmpCf.toUpperCase(), tmpDate);
					Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
					int i = 0;


					while(i < Arrays.asList(listaStudenti).size()) {
						
						if(!listaStudenti[i].isEmpty()) {
							
							fixedList[i] = new Vector();
							//NOME COGNOME CF CORSO
							fixedList[i].add(listaStudenti[i].get(0));
							fixedList[i].add(listaStudenti[i].get(1));
							fixedList[i].add(listaStudenti[i].get(2));
							fixedList[i].add(listaStudenti[i].get(3));
							
							if(listaStudenti[i].get(3).toString().equals("Empty")) {
								listaStudenti[i].add("X");
								listaStudenti[i].add("X");
								model.addRow(fixedList[i]);
								i++;
							}else {
								
							String nomeCorso = listaStudenti[i].get(3);
							Corso corsoCompleto = new Corso();
							corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
							
							int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
							
							String presenza;
							String cfFormatted = listaStudenti[i].get(2);
							
							List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
							
							int j = 0;
							int count = 0;
							
							while(j < lezioniIdList.size()) {
								
								presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
								
								if(presenza != null) {
									
									if(presenza.equals("Presente")) {
										
										count++;
										
									}
									
								}
								
								j++;
							}
							
							if(nLezioni != 0) {
								
								fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
								
								if(count != 0) {
									
									int percentualeMinima = (count * 100) / nLezioni;
									
									fixedList[i].add(percentualeMinima+"%");
									
								}else {
									
									fixedList[i].add("0");
									
								}
								
							}else {
								fixedList[i].add("X");
								fixedList[i].add("X");
							}
								//ROBA
						
						

							model.addRow(fixedList[i]);
							
						}
						}
						i++;

					}
					
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
			
		}
		
		//COGNOME E DATA
		if(flagNome == 0 && flagCognome == 1 && flagCf == 0 && flagDate == 1) {

			if(tmpCognome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					
					List<String>[] listaStudenti;
					listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByCognomeEData(connectionDao.getConnection(), tmpCognome.toUpperCase(), tmpDate);
					Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
					int i = 0;


					while(i < Arrays.asList(listaStudenti).size()) {
						
						if(!listaStudenti[i].isEmpty()) {
							
							fixedList[i] = new Vector();
							fixedList[i].add(listaStudenti[i].get(0));
							fixedList[i].add(listaStudenti[i].get(1));
							fixedList[i].add(listaStudenti[i].get(2));
							fixedList[i].add(listaStudenti[i].get(3));
							
							String nomeCorso = listaStudenti[i].get(3);
							Corso corsoCompleto = new Corso();
							corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
							
							int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
							
							String presenza;
							String cfFormatted = listaStudenti[i].get(2);
							
							List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
							
							int j = 0;
							int count = 0;
							
							while(j < lezioniIdList.size()) {
								
								presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
								
								if(presenza != null) {
									
									if(presenza.equals("Presente")) {
										
										count++;
										
									}
									
								}
								
								j++;
							}
							
							if(nLezioni != 0) {
								
								fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
								
								if(count != 0) {
									
									int percentualeMinima = (count * 100) / nLezioni;
									
									
									fixedList[i].add(percentualeMinima+"%");
									
								}else {
									
									fixedList[i].add("0");
									
								}
								
							}else {
								fixedList[i].add("X");
								fixedList[i].add("X");
							}
							
							model.addRow(fixedList[i]);
							
						}
						i++;

					}
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
		}
		
		//COGNOME E CF
		if(flagNome == 0 && flagCognome == 1 && flagCf == 1 && flagDate == 0) {

			if(tmpCognome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCf != null) {
							
					
							List<String>[] listaStudenti;
							listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByCognomeECf(connectionDao.getConnection(), tmpCognome.toUpperCase(), tmpCf.toUpperCase());
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								fixedList[i] = new Vector();
								fixedList[i].add(listaStudenti[i].get(0));
								fixedList[i].add(listaStudenti[i].get(1));
								fixedList[i].add(listaStudenti[i].get(2));
								fixedList[i].add(listaStudenti[i].get(3));
								
								String nomeCorso = listaStudenti[i].get(3);
								Corso corsoCompleto = new Corso();
								corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
								
								int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
								
								String presenza;
								String cfFormatted = listaStudenti[i].get(2);
								
								List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
								
								int j = 0;
								int count = 0;
								
								while(j < lezioniIdList.size()) {
									
									presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
									
									if(presenza != null) {
										
										if(presenza.equals("Presente")) {
											
											count++;
											
										}
										
									}
									
									j++;
								}

								if(nLezioni != 0) {
									
									fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
									
									if(count != 0) {
										
										int percentualeMinima = (count * 100) / nLezioni;
										
										fixedList[i].add(percentualeMinima+"%");
										
									}else {

										fixedList[i].add("0");
										
									}
									
								}else {
									fixedList[i].add("X");
									fixedList[i].add("X");
								}
								
								model.addRow(fixedList[i]);
								i++;

							}
							
					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
		}
		
		//NOME E DATA
		if(flagNome == 1 && flagCognome == 0 && flagCf == 0 && flagDate == 1) {

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {
					
					List<String>[] listaStudenti;
					listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByNomeEData(connectionDao.getConnection(), tmpNome.toUpperCase(), tmpDate);
					Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
					int i = 0;


					while(i < Arrays.asList(listaStudenti).size()) {
						
						fixedList[i] = new Vector();
						fixedList[i].add(listaStudenti[i].get(0));
						fixedList[i].add(listaStudenti[i].get(1));
						fixedList[i].add(listaStudenti[i].get(2));
						fixedList[i].add(listaStudenti[i].get(3));
						
						String nomeCorso = listaStudenti[i].get(3);
						Corso corsoCompleto = new Corso();
						corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
						
						int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
						
						
						String presenza;
						String cfFormatted = listaStudenti[i].get(2);
						
						List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
						
						int j = 0;
						int count = 0;
						
						while(j < lezioniIdList.size()) {
							
							presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
							
							if(presenza != null) {
								
								if(presenza.equals("Presente")) {
									
									count++;
									
								}
								
							}
							
							j++;
						}
						
						if(nLezioni != 0) {
							
							fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
							
							if(count != 0) {
								
								int percentualeMinima = (count * 100) / nLezioni;
								
								
								fixedList[i].add(percentualeMinima+"%");
								
							}else {
								
								fixedList[i].add("0");
								
							}
							
						}else {
							fixedList[i].add("X");
							fixedList[i].add("X");
						}
						
						model.addRow(fixedList[i]);
						i++;

					}
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
		}
		
		//NOME E CF
		if(flagNome == 1 && flagCognome == 0 && flagCf == 1 && flagDate == 0) {

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCf != null) {
							
							List<String>[] listaStudenti;
							listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByNomeECf(connectionDao.getConnection(), tmpNome.toUpperCase(), tmpCf.toUpperCase());
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								fixedList[i] = new Vector();
								fixedList[i].add(listaStudenti[i].get(0));
								fixedList[i].add(listaStudenti[i].get(1));
								fixedList[i].add(listaStudenti[i].get(2));
								fixedList[i].add(listaStudenti[i].get(3));
								
								String nomeCorso = listaStudenti[i].get(3);
								Corso corsoCompleto = new Corso();
								
								corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
								
								int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
								
								
								String presenza;
								String cfFormatted = listaStudenti[i].get(2);
								
								List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
								
								int j = 0;
								int count = 0;
								
								while(j < lezioniIdList.size()) {
									
									presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
									
									if(presenza != null) {
										
										if(presenza.equals("Presente")) {
											
											count++;
											
										}
										
									}
									
									j++;
								}
								
								if(nLezioni != 0) {
									
									fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
									
									if(count != 0) {
										
										int percentualeMinima = (count * 100) / nLezioni;
										
										
										fixedList[i].add(percentualeMinima+"%");
										
									}else {
										
										fixedList[i].add("0");
										
									}
									
								}else {
									fixedList[i].add("X");
									fixedList[i].add("X");
								}
								
								model.addRow(fixedList[i]);
								i++;

							}
							
					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
		}
		
		//NOME E COGNOME
		if(flagNome == 1 && flagCognome == 1 && flagCf == 0 && flagDate == 0) {

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCognome != null) {
						if(isWhatYouWant(tmpNome ,0)) {
							
							List<String>[] listaStudenti;
							listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByNomeECognome(connectionDao.getConnection(), tmpNome.toUpperCase(), tmpCognome.toUpperCase());
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									
									String nomeCorso = listaStudenti[i].get(3);
									Corso corsoCompleto = new Corso();
									corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
									
									int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
									
									
									String presenza;
									String cfFormatted = listaStudenti[i].get(2);
									
									List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
									
									int j = 0;
									int count = 0;
									
									while(j < lezioniIdList.size()) {
										
										presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
										
										if(presenza != null) {
											
											if(presenza.equals("Presente")) {
												
												count++;
												
											}
											
										}
										
										j++;
									}
									
									if(nLezioni != 0) {
										
										fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
										
										if(count != 0) {
											
											int percentualeMinima = (count * 100) / nLezioni;
											
											
											fixedList[i].add(percentualeMinima+"%");
											
										}else {
											
											fixedList[i].add("0");
											
										}
										
									}else {
										fixedList[i].add("X");
										fixedList[i].add("X");
									}
									
									model.addRow(fixedList[i]);
									
								}
								i++;

							}
							
							
						}else
							jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
		}
		
		//COGNOME DATA CF 
		if(flagNome == 0 && flagCognome == 1 && flagCf == 1 && flagDate == 1) {

			if(tmpCognome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCf != null) {
							
							List<String>[] listaStudenti;
							listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByCognomeDataECf(connectionDao.getConnection(), tmpCognome.toUpperCase(), tmpDate ,tmpCf.toUpperCase());
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									
									String nomeCorso = listaStudenti[i].get(3);
									Corso corsoCompleto = new Corso();
									corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
									
									int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
									
									
									String presenza;
									String cfFormatted = listaStudenti[i].get(2);
									
									List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
									
									int j = 0;
									int count = 0;
									
									while(j < lezioniIdList.size()) {
										
										presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
										
										if(presenza != null) {
											
											if(presenza.equals("Presente")) {
												
												count++;
												
											}
											
										}
										
										j++;
									}
									
									if(nLezioni != 0) {
										
										fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
										
										if(count != 0) {
											
											int percentualeMinima = (count * 100) / nLezioni;
											
											fixedList[i].add(percentualeMinima+"%");
											
										}else {
											
											fixedList[i].add("0");
											
										}
										
									}else {
										fixedList[i].add("X");
										fixedList[i].add("X");
									}
									
									model.addRow(fixedList[i]);
									
								}
								i++;

							}

					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
		}
		
		//NOME CF DATA
		if(flagNome == 1 && flagCognome == 0 && flagCf == 1 && flagDate == 1) {

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCf != null) {

							List<String>[] listaStudenti;
							listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByNomeDataECf(connectionDao.getConnection(), tmpNome.toUpperCase(), tmpDate ,tmpCf.toUpperCase());
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									
									String nomeCorso = listaStudenti[i].get(3);
									Corso corsoCompleto = new Corso();
									corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
									
									int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
									
									
									String presenza;
									String cfFormatted = listaStudenti[i].get(2);
									
									List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
									
									int j = 0;
									int count = 0;
									
									while(j < lezioniIdList.size()) {
										
										presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
										
										if(presenza != null) {
											
											if(presenza.equals("Presente")) {
												
												count++;
												
											}
											
										}
										
										j++;
									}
									
									if(nLezioni != 0) {
										
										fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
										
										if(count != 0) {
											
											int percentualeMinima = (count * 100) / nLezioni;
											
											fixedList[i].add(percentualeMinima+"%");
											
										}else {
											

											fixedList[i].add("0");
											
										}
										
									}else {
										fixedList[i].add("X");
										fixedList[i].add("X");
									}
									
									model.addRow(fixedList[i]);
									
								}
								i++;

							}

					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
		}
		
		//NOME COGNOME E DATA
		if(flagNome == 1 && flagCognome == 1 && flagCf == 0 && flagDate == 1) {

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCognome != null) {
						if(isWhatYouWant(tmpNome ,0)) {

							List<String>[] listaStudenti;
							listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByNomeDataECognome(connectionDao.getConnection(), tmpNome.toUpperCase(), tmpDate ,tmpCognome.toUpperCase());
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									
									String nomeCorso = listaStudenti[i].get(3);
									Corso corsoCompleto = new Corso();
									corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
									
									int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
									
									
									String presenza;
									String cfFormatted = listaStudenti[i].get(2);
									
									List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
									
									int j = 0;
									int count = 0;
									
									while(j < lezioniIdList.size()) {
										
										presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
										
										if(presenza != null) {
											
											if(presenza.equals("Presente")) {
												
												count++;
												
											}
											
										}
										
										j++;
									}
									
									if(nLezioni != 0) {
										
										fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
										
										if(count != 0) {
											
											int percentualeMinima = (count * 100) / nLezioni;
											
											fixedList[i].add(percentualeMinima+"%");
											
										}else {
											
											fixedList[i].add("0");
											
										}
										
									}else {
										fixedList[i].add("X");
										fixedList[i].add("X");
									}
									
									model.addRow(fixedList[i]);
									
								}
								i++;

							}
							
						}else
							jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
		}
		
		//NOME COGNOME CF
		if(flagNome == 1 && flagCognome == 1 && flagCf == 1 && flagDate == 0) {

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCognome != null) {
						if(isWhatYouWant(tmpNome ,0)) {

							if(tmpCf != null) {

									List<String>[] listaStudenti;
									listaStudenti = connectionDao.getStudenteDao().ricercaStudenteByNomeCfECognome(connectionDao.getConnection(), tmpNome.toUpperCase(), tmpCf.toUpperCase() ,tmpCognome.toUpperCase());
									Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
									int i = 0;


									while(i < Arrays.asList(listaStudenti).size()) {
										
										if(!listaStudenti[i].isEmpty()) {
											
											fixedList[i] = new Vector();
											fixedList[i].add(listaStudenti[i].get(0));
											fixedList[i].add(listaStudenti[i].get(1));
											fixedList[i].add(listaStudenti[i].get(2));
											fixedList[i].add(listaStudenti[i].get(3));
											
											String nomeCorso = listaStudenti[i].get(3);
											Corso corsoCompleto = new Corso();
											corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), nomeCorso);
											
											int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoCompleto.getCorsoId());
											
											String presenza;
											String cfFormatted = listaStudenti[i].get(2);
											
											List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoCompleto.getCorsoId());
											
											int j = 0;
											int count = 0;
											
											while(j < lezioniIdList.size()) {
												
												presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(j));
												
												if(presenza != null) {
													
													if(presenza.equals("Presente")) {
														
														count++;
														
													}
													
												}
												
												j++;
											}
											
											if(nLezioni != 0) {
												
												fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
												
												if(count != 0) {
													
													int percentualeMinima = (count * 100) / nLezioni;
													
													
													fixedList[i].add(percentualeMinima+"%");
													
												}else {
													
													fixedList[i].add("0");
													
												}
												
											}else {
												fixedList[i].add("X");
												fixedList[i].add("X");
											}
											
											model.addRow(fixedList[i]);
											
										}
										i++;

									}

							}else
								jpanelManagementCreaCorsoFrame(null ,null ,cf ,6);
							
						}else
							jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
					}else
						jpanelManagementCreaCorsoFrame(null ,null ,cognome ,4);
					
				}else
					jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
			}else
				jpanelManagementCreaCorsoFrame(null ,null ,nome ,0);
		}
		
		//TUTTO
		if(flagNome == 1 && flagCognome == 1 && flagCf == 1 && flagDate == 1) {

				JOptionPane.showMessageDialog(null, "Not Avaible", "ERROR", JOptionPane.ERROR_MESSAGE);
				
		}
		
		
	}
	
	//TODOx
	public int ricercaStudenti(JList corso ,JLabel labelCorso ,DefaultTableModel model ,JTable table) {
		
		
		if(corso.getSelectedValue() != null) {
			//effettua ricerca
			int nLezioni = 0;
			int j;
			int count;

			String tmpCorso = corso.getSelectedValue().toString();
			
			List[] listaStudenti = connectionDao.getIscrizioneDao().getStudentiByCorsoName(connectionDao.getConnection(), tmpCorso);
			
			Corso corsoFormattato = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), tmpCorso);	
			
			
			nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoFormattato.getCorsoId());
			
			
			Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
			int i = 0;

			List<Integer> listaIdLezioni = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(), corsoFormattato.getCorsoId());
			
			
			String formattedCf;
			
			while(i < Arrays.asList(listaStudenti).size()) {
				
				if(!listaStudenti[i].isEmpty()) {
					
					fixedList[i] = new Vector();
					//NOME COGNOME CF
					fixedList[i].add(listaStudenti[i].get(0));
					fixedList[i].add(listaStudenti[i].get(1));
					fixedList[i].add(listaStudenti[i].get(2));
					
					formattedCf = listaStudenti[i].get(2).toString().substring(1, listaStudenti[i].get(2).toString().length()-1);
					//N LEZIONE TOTALI

					
					//N PRESENZE
					j = 0;
					count = 0;
					while(j < listaIdLezioni.size()){

						String presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), formattedCf, listaIdLezioni.get(j));

						if(presenza != null) {
							
							if(presenza.equals("Presente")) {
								
								count++;
								
							}
							
						}
						j++;
					}
					
					if(nLezioni != 0 && count != 0) {
						
						int percentualePresenza = (count * 100) / nLezioni;
						
						
						if(nLezioni != 0) {
							fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
						}else
							fixedList[i].add("X");
						
						fixedList[i].add(percentualePresenza+"%");
						model.addRow(fixedList[i]);
						
					}else {
						
						if(nLezioni != 0) {
							
							fixedList[i].add(Integer.toString(count) + "/" + nLezioni);
							fixedList[i].add("0");
							
						}else {
							
							fixedList[i].add("X");
							fixedList[i].add("X");
							
						}
							
						model.addRow(fixedList[i]);
					}
					
				}
				i++;

			}
			
			return i;
			
		}else {
			jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(labelCorso), null, null, 7);
		
			return 0;
		}
	}
	
	public int inserisciLezione(String corso ,JTextField title ,JDateChooser dateChooser ,JSpinner spinnerIn ,JSpinner spinnerDur ,JTextPane area ,SimpleDateFormat hourForm ,int lezioneIdUpd ,int flag) {
		
		Corso corsoCompleto = connectionDao.getCorsoDao().getCorso(connectionDao.getConnection(), corso);
		
		//CONTROLLO SU NUMERI ISCRITTI
		int corsoId = corsoCompleto.getCorsoId();
		int minPartecipanti = corsoCompleto.getMinPartecipazione();
		int studentiIscritti = connectionDao.getIscrizioneDao().countStudentiIscritti(connectionDao.getConnection(), corsoId);
		
		if(minPartecipanti <= studentiIscritti) {
			
		String tmpTitle = title.getText();
		if(!tmpTitle.isEmpty()) {
			if(tmpTitle.length() < 40) {
				
				if(area.getText() != null) {
					if(area.getText().length() > 1280) {
						JOptionPane.showMessageDialog(null, "Descrizione troppo lunga", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
						return 0;
					}
				}
				
			if(dateChooser.getDate() != null) {					
				
				if(spinnerIn.getValue() != null) {
					
					Object tmpInizio = spinnerIn.getValue();
					String inizio = tmpInizio.toString();
					
					if(spinnerDur.getValue() != null) {
						
						Object tmpDurata = spinnerDur.getValue();
						String durata = tmpDurata.toString();
						
						//WORKin
						JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerIn ,"HH:mm");
						String orarioInizioLezione = de.getFormat().format(spinnerIn.getValue());
						String durataLezione = spinnerDur.getValue().toString();

						int oreMinuti = Integer.parseInt(durataLezione);
						
						if(Integer.parseInt(orarioInizioLezione.substring(0,2)) < 8 || Integer.parseInt(orarioInizioLezione.substring(0,2)) > 19) {
							
							if(Integer.parseInt(orarioInizioLezione.substring(0,2)) < 8) {
								
								JOptionPane.showMessageDialog(area, "Troppo presto per una lezione!.");
								return 0;
								
							}else {
								
								JOptionPane.showMessageDialog(area, "Troppo tardi per una lezione!.");
								return 0;
								
							}
								
						}else {
							//inserimento
							if(oreMinuti > 299) {
								
								JOptionPane.showMessageDialog(area, "Durata maggiore alle 4 ore e 59 minuti.Impossibile aggiungere.");
								return 0;
								
							}else
							if(oreMinuti < 45) {
								
								JOptionPane.showMessageDialog(area, "Durata minore ai 45 minuti.Impossibile aggiungere.");
								return 0;
								
							}else{
								//Inserimento
								Lezione lezione = new Lezione();
								
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
								lezione.setTitolo(tmpTitle);
								lezione.setDescrizione(area.getText());
								lezione.setOraInizio(orarioInizioLezione);
								lezione.setDurata(oreMinuti);
								lezione.setData(format.format(dateChooser.getDate()));
								lezione.setCorsoId(corsoId);
								
								if(flag == 0) {
									
									int permesso = connectionDao.getLezioneDao().gestioneDuplicati(connectionDao.getConnection(), lezione.getData(), lezione.getCorsoId());
									
									if(permesso == 1) {
										
										connectionDao.getLezioneDao().inserimentoLezione(connectionDao.getConnection(), lezione);
										
										JFrame tmpFrame = (JFrame) SwingUtilities.getRoot(dateChooser);
										tmpFrame.setVisible(false);
										new FrameDiScelta();
										
									}else {
										JOptionPane.showMessageDialog(null, "Lezione già presente per questo giorno", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
									}
									
								}else {
									int permesso = connectionDao.getLezioneDao().gestioneDuplicatiUpdate(connectionDao.getConnection(), lezione.getData() ,lezione.getCorsoId() ,lezioneIdUpd);
									
									if(permesso != 0) {
										
									     	connectionDao.getLezioneDao().updateLezione(connectionDao.getConnection(),lezione, lezioneIdUpd);
											JOptionPane.showMessageDialog(null, "Updated!", "Ok!", JOptionPane.INFORMATION_MESSAGE);

											return 1;

									}else {
										JOptionPane.showMessageDialog(null, "Lezione già presente per questo giorno", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
									}
								}
									
								
							}
						}
						
					}else {
						jpanelManagementCreaCorsoFrame(null, null, title, 7);
					return 0;
					}
				}else {
					jpanelManagementCreaCorsoFrame(null, null, title, 7);
					return 0;
				}
			}else {
				jpanelManagementCreaCorsoFrame(null, null, title, 7);
			return 0;
			}
			}else {
				JOptionPane.showMessageDialog(null, "Titolo troppo lungo!", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
			}
				
		}else{
			return 0;
		}
			
		}else {
			JOptionPane.showMessageDialog(null, "Pochi iscritti,non puoi creare lezioni.", "ERROR", JOptionPane.ERROR_MESSAGE);
		return 0;
		}
		return 0;
		
	}
	
	public void esporta(JFileChooser fileChooser ,String nomeCorso ,Vector[] vettoreStudenti ,DefaultTableModel model) {
		
		
		if(nomeCorso != null) {
			if(vettoreStudenti.length != 0) {
				
		int risposta = fileChooser.showSaveDialog(null);
		if(risposta == 0) {
			
			
			
		File file = fileChooser.getSelectedFile();
		
		//ANCHE LA LISTA DIVERSA DA NULL DA METTERE!!!!!
			
			File fileCorso = new File(file.toString() + "\\" + nomeCorso + "ListReport.txt");
			
			if(fileCorso.exists()) {
				
				int answer = JOptionPane.showConfirmDialog(null, "Il file esiste già.Sovrascrivere?", "Question", JOptionPane.OK_CANCEL_OPTION);
				
				if(answer == 0) {
					
					fileCorso.delete();
					fileCorso = new File(file.toString() + "\\" + nomeCorso + "ListReport.txt");
					
				try {
						
						FileWriter fileWrit = new FileWriter(fileCorso,true);
						 		 
						String cornice = "+---------------------------------+---------------------------------+-------------------------+-----------------+------------+";

						fileWrit.write(cornice);
						fileWrit.write("\n|Nome                             |Cognome                          |Cf                       |Promosso         |%Presenze   |\n");
						fileWrit.write(cornice);
						fileWrit.write("\n");
						
						//29 cognome e nome    25cf 17 promosso  12 presenza
						int i = 0;
						int j;
						
						//30
						//60
						char[] tmpNome = new char[200];
						tmpNome[0] = '|';
						
						tmpNome[67] = '|';
						
						String nome,cognome,cf,promozione,presenza;
						int lengthNome;
						int lengthCognome;

						
						while(i < vettoreStudenti.length) {
							
							nome = vettoreStudenti[i].get(0).toString().substring(1,vettoreStudenti[i].get(0).toString().length()-1);
							cognome = vettoreStudenti[i].get(1).toString().substring(1,vettoreStudenti[i].get(1).toString().length()-1);
							cf = vettoreStudenti[i].get(2).toString().substring(1,vettoreStudenti[i].get(2).toString().length()-1);
							
						
							promozione = model.getValueAt(i, 3).toString();
							presenza = model.getValueAt(i, 4).toString();

							j = 1;
							int t = 0;
							
							while(t < nome.length()) {
								
								tmpNome[j] = nome.charAt(t);
								
								j++;
								t++;
							}
							while(j < 34) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[34] = '|';
							
							j++;
							t = 0;
							
							while(t < cognome.length()) {
								
								tmpNome[j] = cognome.charAt(t);
								
								j++;
								t++;
							}
							while(j < 68) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[68] = '|';
							j++;
							t = 0;
							
							while(t < cf.length()) {
								
								tmpNome[j] = cf.charAt(t);
								
								j++;
								t++;
							}
							
							while(j < 94) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[94] = '|';
							j++;
							t = 0;
							
							while(t < promozione.length()) {
								
								tmpNome[j] = promozione.charAt(t);
								
								j++;
								t++;
							}
							
							while(j < 112) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[112] = '|';
							j++;
							t = 0;
							
							
							while(t < presenza.length()) {
								
								tmpNome[j] = presenza.charAt(t);
								
								j++;
								t++;
							}
							
							while(j < 125) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[125] = '|';
							

							fileWrit.write(tmpNome);
							fileWrit.write("\n");
							fileWrit.write(cornice);
							
							i++;
						}
						
						fileWrit.close();
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					
				}
				
				
			}else {

					try {
						
						FileWriter fileWrit = new FileWriter(fileCorso,true);
						 		 
						String cornice = "+---------------------------------+---------------------------------+-------------------------+-----------------+------------+";

						fileWrit.write(cornice);
						fileWrit.write("\n|Nome                             |Cognome                          |Cf                       |Promosso         |%Presenze   |\n");
						fileWrit.write(cornice);
						fileWrit.write("\n");
						//29 cognome e nome    25cf 17 promosso  12 presenza
						int i = 0;
						int j;
						
						//30
						//60
						char[] tmpNome = new char[200];
						tmpNome[0] = '|';
						
						tmpNome[67] = '|';
						
						String nome,cognome,cf,promozione,presenza;
						int lengthNome;
						int lengthCognome;

						
						while(i < vettoreStudenti.length) {
							
							nome = vettoreStudenti[i].get(0).toString().substring(1,vettoreStudenti[i].get(0).toString().length()-1);
							cognome = vettoreStudenti[i].get(1).toString().substring(1,vettoreStudenti[i].get(1).toString().length()-1);
							cf = vettoreStudenti[i].get(2).toString().substring(1,vettoreStudenti[i].get(2).toString().length()-1);
						
							
							
							
							promozione = model.getValueAt(i, 3).toString();
							presenza = model.getValueAt(i, 4).toString();

							j = 1;
							int t = 0;
							
							while(t < nome.length()) {
								
								tmpNome[j] = nome.charAt(t);
								
								j++;
								t++;
							}
							while(j < 34) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[34] = '|';
							
							j++;
							t = 0;
							
							while(t < cognome.length()) {
								
								tmpNome[j] = cognome.charAt(t);
								
								j++;
								t++;
							}
							while(j < 68) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[68] = '|';
							j++;
							t = 0;
							
							while(t < cf.length()) {
								
								tmpNome[j] = cf.charAt(t);
								
								j++;
								t++;
							}
							
							while(j < 94) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[94] = '|';
							j++;
							t = 0;
							
							while(t < promozione.length()) {
								
								tmpNome[j] = promozione.charAt(t);
								
								j++;
								t++;
							}
							
							while(j < 112) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[112] = '|';
							j++;
							t = 0;
							
							
							while(t < presenza.length()) {
								
								tmpNome[j] = presenza.charAt(t);
								
								j++;
								t++;
							}
							
							while(j < 125) {
								
								tmpNome[j] = ' ';
								
								j++;
								
							}
							
							tmpNome[125] = '|';

							fileWrit.write(tmpNome);
							fileWrit.write("\n");
							fileWrit.write(cornice);
							
							i++;
						}
						
						fileWrit.close();
						
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}

			}
			}else
				JOptionPane.showMessageDialog(null, "Non sono presenti studenti.", "ERROR", JOptionPane.ERROR_MESSAGE);
		
		}else
			JOptionPane.showMessageDialog(null, "Non hai selezionato nessun corso.", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

}

