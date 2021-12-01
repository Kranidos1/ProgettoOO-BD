package Frames;

import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.toedter.calendar.JDateChooser;

import Oggetti.AreaTematica;
import Oggetti.Corso;
import Oggetti.Studente;
import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.CorsoDaoImpl;

import Oggetti.DAO.CorsoETemaDaoImpl;
import Oggetti.DAO.IscrizioneDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;
import Oggetti.DAO.PresenzaDaoImpl;
import Oggetti.DAO.StudenteDaoImpl;

public class Controller implements ControlloEOperazioniSuFrame {
		
	private int j = 0;
		private Connection connection;
		
		public Connection getConnection() {
			return connection;
		}



		public void setConnection(Connection connection) {
			this.connection = connection;
		}



		public Controller() {
			
			String jdbcURL = "jdbc:postgresql://localhost:5432/Progetto";
			String username = "postgres";
			String password = "Pippo200-";
					
			try {
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				connection = DriverManager.getConnection(jdbcURL,username,password);
				//CONNESSIONE RIUSCITA!

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

	

	public void newTheme(JLabel label) {
		String theme = JOptionPane.showInputDialog("New Theme");
		//TODO Funzione add theme nel db
		//CONTROLLO SE LA PAGINA E' CHIUSA O SE VIENE DATO UN INPUT VUOTO
		boolean control;
		int flag = 0;
		JFrame tmpFrame = (JFrame) SwingUtilities.getRoot(label);
		if(theme != null) {
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
					AreaTematicaDaoImpl areaTematicaDaoImpl = new AreaTematicaDaoImpl();
					areaTematicaDaoImpl.inserimento(tema ,connection);
					
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

	
	public void insertCorsoDb(JFrame fram ,JTextField nome ,JTextField max ,JTextField min ,JTextArea areaDescrizione ,DefaultListModel<String> model ,int flag ,String corsoId) {
		
		//FLAG 0 PER INSERIMENTO DA 0 1 PER UPDATE
		CorsoDaoImpl corsoDaoImpl = new CorsoDaoImpl();
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
			if(b == false && v == true) {
				if(c == false && u == true) {
		
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
							corsoDaoImpl.inserimento(corso, connection);
							
							
							CorsoETemaDaoImpl associazione = new CorsoETemaDaoImpl();
							
							
							Timer timer = new Timer(1000,new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub

									while(j < tmp.size()) {
										
										associazione.inserimento(connection, corsoDaoImpl.getNextCorsoId(connection), tmp.get(j));
										j ++;
										
									}
									
								}		
							});	
							timer.setRepeats(false);
							timer.start();
						}else
							if(flag == 1) {
								
								corsoDaoImpl.updateCorso(connection, corsoId, corso.getNome(), corso.getDescrizione(), Integer.toString(corso.getMaxPartecipanti()), Integer.toString(corso.getMinPartecipazione()));
								
							}
						
					}else
						jpanelManagementCreaCorsoFrame(fram,null,null,9);
						
				}else
					jpanelManagementCreaCorsoFrame(fram,null,min,2);
			}else
				jpanelManagementCreaCorsoFrame(fram,null,max,1);
				
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

				AreaTematicaDaoImpl areaTematicaDaoImpl = new AreaTematicaDaoImpl();
				areaTematicaDaoImpl.inserimento(tema ,connection);
				
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

		if(!list.getSelectedValue().toString().isEmpty()) {
			
			String tmpCorso = list.getSelectedValue().toString();
			
				if(!tmpNome.isEmpty()) {
					if(isWhatYouWant(tmpNome, 0)) {
						
						if(!tmpCognome.isEmpty()) {
							if(isWhatYouWant(tmpCognome, 0)) {
								
								
								Date date;
								String dbDate;
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								date = dateChooser.getDate();
//								dbDate = sdf.format(date);
										//controllo data ma prima cf
										if(controlloCF(cfField, cfLab) == 1) {
											//PRENDI ANCHE DATA ATTUALE
											Date dataAttuale = new Date();
											String AttualeData = sdf.format(date).toString();
											//TODO 
											//Inserimento
											/////INSERIMENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO dbDate
											
											StudenteDaoImpl stud = new StudenteDaoImpl();
											Studente studente = new Studente();
											CorsoDaoImpl corso = new CorsoDaoImpl();
											
											int corsoId = corso.trovaCorsoId(connection, tmpCorso);
											
											studente.setCF(cfField.getText().toString());
											studente.setNome(tmpNome);

											studente.setCognome(tmpCognome);
											studente.setData(sdf.format(date));
											studente.setDataIscrizione(sdf.format(dataAttuale));
											stud.inserimento(connection, studente);
											
											IscrizioneDaoImpl associazione = new IscrizioneDaoImpl();
											int procedere = associazione.controlloDuplicati(connection, studente.getCF(), corsoId);
											
											if(procedere == 1) {
												
												associazione.inserimento(connection, corsoId, studente.getCF() ,studente.getDataIscrizione());
												
											}else
												JOptionPane.showMessageDialog((JFrame) SwingUtilities.getRoot(cfLab) ,"Studente gia' presente nei database per questo corso.", "PSQL ERROR", JOptionPane.ERROR_MESSAGE);
											

											
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
	
	public void ricercaStudente(JTextField nome ,JTextField cognome ,JTextField cf ,JDateChooser dataDateChooser ,int flagNome ,int flagCognome ,int flagCf ,int flagDate ,JLabel label ,DefaultTableModel model) {
		
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
			
			StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
			Vector[][] listaStudenti = studenteRicerca.ricercaStudenteByDataIscrizione(connection, tmpDate);
			
			int sizeLista = Arrays.asList(listaStudenti).size();
			int i = 0;
			
			while(i < sizeLista) {
				
				model.addRow(listaStudenti[i]);
				i++;
				
			}
			
		}
		
		//SOLO CF
		if(flagNome == 0 && flagCognome == 0 && flagCf == 1 && flagDate == 0) {
			//erroreerororororororo
			if(tmpCf != null) {
					//ricerca per cf
					
					StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
					Vector[][] listaStudenti = studenteRicerca.ricercaStudenteByCf(connection, tmpCf);
					
					int sizeLista = Arrays.asList(listaStudenti).size();
					int i = 0;
					
					while(i < sizeLista) {
						
						model.addRow(listaStudenti[i]);
						i++;
						
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
					
					StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
					Vector[][] listaStudenti = studenteRicerca.ricercaStudenteByCognome(connection, tmpCognome);
					
					int sizeLista = Arrays.asList(listaStudenti).size();
					int i = 0;
					
					while(i < sizeLista) {
						
						model.addRow(listaStudenti[i]);
						i++;
						
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
					StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
					Vector[][] listaStudenti = studenteRicerca.ricercaStudenteByName(connection, tmpNome);
					
					int sizeLista = Arrays.asList(listaStudenti).size();
					int i = 0;
					
					while(i < sizeLista) {
						
						model.addRow(listaStudenti[i]);
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
					
					StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
					List<String>[] listaStudenti;
					listaStudenti = studenteRicerca.ricercaStudenteByCfEData(connection, tmpCf, tmpDate);
					Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
					int i = 0;


					while(i < Arrays.asList(listaStudenti).size()) {
						
						if(!listaStudenti[i].isEmpty()) {
							
							fixedList[i] = new Vector();
							fixedList[i].add(listaStudenti[i].get(0));
							fixedList[i].add(listaStudenti[i].get(1));
							fixedList[i].add(listaStudenti[i].get(2));
							fixedList[i].add(listaStudenti[i].get(3));
							fixedList[i].add(listaStudenti[i].get(4));
							fixedList[i].add(listaStudenti[i].get(5));
							fixedList[i].add(listaStudenti[i].get(6));
							model.addRow(fixedList[i]);
							
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

					StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
					List<String>[] listaStudenti;
					listaStudenti = studenteRicerca.ricercaStudenteByCognomeEData(connection, tmpCognome, tmpDate);
					Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
					int i = 0;


					while(i < Arrays.asList(listaStudenti).size()) {
						
						if(!listaStudenti[i].isEmpty()) {
							
							fixedList[i] = new Vector();
							fixedList[i].add(listaStudenti[i].get(0));
							fixedList[i].add(listaStudenti[i].get(1));
							fixedList[i].add(listaStudenti[i].get(2));
							fixedList[i].add(listaStudenti[i].get(3));
							fixedList[i].add(listaStudenti[i].get(4));
							fixedList[i].add(listaStudenti[i].get(5));
							fixedList[i].add(listaStudenti[i].get(6));
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
							
							StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
							List<String>[] listaStudenti;
							listaStudenti = studenteRicerca.ricercaStudenteByCognomeECf(connection, tmpCognome, tmpCf);
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								fixedList[i] = new Vector();
								fixedList[i].add(listaStudenti[i].get(0));
								fixedList[i].add(listaStudenti[i].get(1));
								fixedList[i].add(listaStudenti[i].get(2));
								fixedList[i].add(listaStudenti[i].get(3));
								fixedList[i].add(listaStudenti[i].get(4));
								fixedList[i].add(listaStudenti[i].get(5));
								fixedList[i].add(listaStudenti[i].get(6));
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
					
					StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
					List<String>[] listaStudenti;
					listaStudenti = studenteRicerca.ricercaStudenteByNomeEData(connection, tmpNome, tmpDate);
					Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
					int i = 0;


					while(i < Arrays.asList(listaStudenti).size()) {
						
						fixedList[i] = new Vector();
						fixedList[i].add(listaStudenti[i].get(0));
						fixedList[i].add(listaStudenti[i].get(1));
						fixedList[i].add(listaStudenti[i].get(2));
						fixedList[i].add(listaStudenti[i].get(3));
						fixedList[i].add(listaStudenti[i].get(4));
						fixedList[i].add(listaStudenti[i].get(5));
						fixedList[i].add(listaStudenti[i].get(6));
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
							
							StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
							List<String>[] listaStudenti;
							listaStudenti = studenteRicerca.ricercaStudenteByNomeECf(connection, tmpNome, tmpCf);
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								fixedList[i] = new Vector();
								fixedList[i].add(listaStudenti[i].get(0));
								fixedList[i].add(listaStudenti[i].get(1));
								fixedList[i].add(listaStudenti[i].get(2));
								fixedList[i].add(listaStudenti[i].get(3));
								fixedList[i].add(listaStudenti[i].get(4));
								fixedList[i].add(listaStudenti[i].get(5));
								fixedList[i].add(listaStudenti[i].get(6));
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
							
							StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
							List<String>[] listaStudenti;
							listaStudenti = studenteRicerca.ricercaStudenteByNomeECognome(connection, tmpNome, tmpCognome);
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									fixedList[i].add(listaStudenti[i].get(4));
									fixedList[i].add(listaStudenti[i].get(5));
									fixedList[i].add(listaStudenti[i].get(6));
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
							
							StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
							List<String>[] listaStudenti;
							listaStudenti = studenteRicerca.ricercaStudenteByCognomeDataECf(connection, tmpCognome, tmpDate ,tmpCf);
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									fixedList[i].add(listaStudenti[i].get(4));
									fixedList[i].add(listaStudenti[i].get(5));
									fixedList[i].add(listaStudenti[i].get(6));
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

							
							StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
							List<String>[] listaStudenti;
							listaStudenti = studenteRicerca.ricercaStudenteByNomeDataECf(connection, tmpNome, tmpDate ,tmpCf);
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									fixedList[i].add(listaStudenti[i].get(4));
									fixedList[i].add(listaStudenti[i].get(5));
									fixedList[i].add(listaStudenti[i].get(6));
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
							
							StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
							List<String>[] listaStudenti;
							listaStudenti = studenteRicerca.ricercaStudenteByNomeDataECognome(connection, tmpNome, tmpDate ,tmpCognome);
							Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
							int i = 0;


							while(i < Arrays.asList(listaStudenti).size()) {
								
								if(!listaStudenti[i].isEmpty()) {
									
									fixedList[i] = new Vector();
									fixedList[i].add(listaStudenti[i].get(0));
									fixedList[i].add(listaStudenti[i].get(1));
									fixedList[i].add(listaStudenti[i].get(2));
									fixedList[i].add(listaStudenti[i].get(3));
									fixedList[i].add(listaStudenti[i].get(4));
									fixedList[i].add(listaStudenti[i].get(5));
									fixedList[i].add(listaStudenti[i].get(6));
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

									StudenteDaoImpl studenteRicerca = new StudenteDaoImpl();
									List<String>[] listaStudenti;
									listaStudenti = studenteRicerca.ricercaStudenteByNomeCfECognome(connection, tmpNome, tmpCf ,tmpCognome);
									Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
									int i = 0;


									while(i < Arrays.asList(listaStudenti).size()) {
										
										if(!listaStudenti[i].isEmpty()) {
											
											fixedList[i] = new Vector();
											fixedList[i].add(listaStudenti[i].get(0));
											fixedList[i].add(listaStudenti[i].get(1));
											fixedList[i].add(listaStudenti[i].get(2));
											fixedList[i].add(listaStudenti[i].get(3));
											fixedList[i].add(listaStudenti[i].get(4));
											fixedList[i].add(listaStudenti[i].get(5));
											fixedList[i].add(listaStudenti[i].get(6));
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

			if(tmpNome != null) {
				if(isWhatYouWant(tmpNome ,0)) {

					if(tmpCognome != null) {
						if(isWhatYouWant(tmpNome ,0)) {

							if(tmpCf != null) {

									//ricerca per cf
								//TODO RICERCA TUTTO
								
									
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
		
	}
	
	//TODOx
	public void ricercaStudenti(JList corso ,JLabel labelCorso ,DefaultTableModel model) {
		
		if(corso.getSelectedValue() != null) {
			//effettua ricerca
			String tmpCorso = corso.getSelectedValue().toString();
			IscrizioneDaoImpl associazione = new IscrizioneDaoImpl();
			List[] listaStudenti = associazione.getStudentiByCorsoName(connection, tmpCorso);
			
			Vector[] fixedList = new Vector[Arrays.asList(listaStudenti).size()];
			int i = 0;


			while(i < Arrays.asList(listaStudenti).size()) {
				
				if(!listaStudenti[i].isEmpty()) {
					
					fixedList[i] = new Vector();
					fixedList[i].add(listaStudenti[i].get(0));
					fixedList[i].add(listaStudenti[i].get(1));
					fixedList[i].add(listaStudenti[i].get(2));
					fixedList[i].add(listaStudenti[i].get(3));
					fixedList[i].add(listaStudenti[i].get(4));
					fixedList[i].add(listaStudenti[i].get(5));
					model.addRow(fixedList[i]);
					
				}
				i++;

			}
			
			
		}else
			jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(labelCorso), null, null, 7);
	}
	
	public void inserisciLezione(String corso ,JTextField title ,JDateChooser dateChooser ,JSpinner spinnerIn ,JSpinner spinnerDur ,JTextPane area ,SimpleDateFormat formDate ,SimpleDateFormat hourForm) {
		
		String tmpTitle = title.getText();
		if(!tmpTitle.isEmpty()) {
			
			if(dateChooser.getDate() != null) {					
				
				if(spinnerIn.getValue() != null) {
					
					Object tmpInizio = spinnerIn.getValue();
					String inizio = tmpInizio.toString();
					
					if(spinnerDur.getValue() != null) {
						
						Object tmpDurata = spinnerDur.getValue();
						String durata = tmpDurata.toString();
						
						//WORK
						JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerIn ,"HH:mm");
						String orarioInizioLezione = de.getFormat().format(spinnerIn.getValue());
						String durataLezione = de.getFormat().format(spinnerDur.getValue());
						String ora = durataLezione.substring(0, 2);
						int oreMinuti = Integer.parseInt(ora) * 60;
						int minuti = Integer.parseInt(durataLezione.substring(3,5));
						oreMinuti += minuti;
						
						if(Integer.parseInt(orarioInizioLezione.substring(0,2)) < 8 || Integer.parseInt(orarioInizioLezione.substring(0,2)) > 19) {
							
							if(Integer.parseInt(orarioInizioLezione.substring(0,2)) < 8) {
								
								JOptionPane.showMessageDialog(area, "Troppo presto per una lezione!.");
								
							}else
								JOptionPane.showMessageDialog(area, "Troppo tardi per una lezione!.");
								
						}else {
							//inserimento
							if(oreMinuti > 299) {
								
								JOptionPane.showMessageDialog(area, "Durata maggiore alle 4 ore e 59 minuti.Impossibile aggiungere.");
								
							}else{
								//Inserimento
								CorsoDaoImpl corsoDao = new CorsoDaoImpl();
								LezioneDaoImpl lezioneDao = new LezioneDaoImpl();
								Lezione lezione = new Lezione();
								
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
								lezione.setTitolo(tmpTitle);
								lezione.setDescrizione(area.getText());
								lezione.setOraInizio(orarioInizioLezione);
								lezione.setDurata(oreMinuti);
								lezione.setData(format.format(dateChooser.getDate()));
								lezione.setCorsoId(corsoDao.trovaCorsoId(connection, corso));
								
								int permesso = lezioneDao.gestioneDuplicati(connection, lezione.getData(), lezione.getCorsoId());
								
								if(permesso == 1) {
									
									lezioneDao.inserimentoLezione(connection, lezione);
									int lezioneId = lezioneDao.recuperaIdUltimaInserita(connection);
									
									PresenzaDaoImpl presenzaDao = new PresenzaDaoImpl();
									presenzaDao.inserimentoAssociazioneConStudenti(connection, lezione.getCorsoId(), lezioneId);
									
								}else
									JOptionPane.showMessageDialog(null, "Lezione già presente per questo giorno", "Lezione_ERROR", JOptionPane.ERROR_MESSAGE);
								
							}
						}
						
					}else
						jpanelManagementCreaCorsoFrame(null, null, title, 7);
					
				}else
					jpanelManagementCreaCorsoFrame(null, null, title, 7);
				
			}else
				jpanelManagementCreaCorsoFrame(null, null, title, 7);
			
		}else
			jpanelManagementCreaCorsoFrame(null, null, title, 7);
		
	}
	
}

