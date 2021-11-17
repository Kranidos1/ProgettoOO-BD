import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JDateChooser;

public class Controller implements ControlloEOperazioniSuFrame {

	
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


	public boolean isEmptyArea(JTextArea input) {
		// TODO Auto-generated method stub
		String tmp = input.getText();
		if(tmp.isEmpty()) {
			return true;
		}else
			return false;
	}


	public boolean isEmptyField(JTextField input) {
		// TODO Auto-generated method stub
		String tmp = input.getText();
		if(tmp.isEmpty()) {
			return true;
		}else
			return false;
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
					//TODO INSERIMENTO EFFETTIVO NEL DB
					System.out.println(theme);
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

	
	public void insertCorsoDb(JFrame fram ,JTextField nome ,JTextField max ,JTextField min ,JTextArea areaDescrizione ,DefaultListModel<String> model) {
		String name,maxString,minString;
		boolean a,b,c,d,z,v,u;
		a = isEmptyField(nome);
		name = nome.getText();
		z = isWhatYouWant(name,0);
		
		b = isEmptyField(max);
		maxString = max.getText();
		v = isWhatYouWant(maxString,1);
		
		c = isEmptyField(min);
		minString = min.getText();
		u = isWhatYouWant(minString,1);
		
		d = isEmptyArea(areaDescrizione);
		
		if(a == false && z == true) {
			if(b == false && v == true) {
				if(c == false && u == true) {
					if(d == false) {
						//TODO SALVATAGGIO EFFETTIVO INSERIMENTO DB ricorda di estrarre stringhe dall'area tematica e aggiungerle una a una nell'oggetto desiderato.
						//INSERIMENTO ELEMENTI LIST
						ArrayList<String> tmp = new ArrayList<String>();
						int i = 0;
						int size = model.getSize();
						
						//RIEMPIMENTO TEMI SELEZIONATI
						while(i < size) {
							tmp.add(model.getElementAt(i).toString());
							i++;
						}
						//EFFETTIVA AGGIUNTA TODO
						
					}else
						jpanelManagementCreaCorsoFrame(fram,areaDescrizione ,null ,3);
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
	

	public void controlloInserimentoStudente(JTextField nomeField , JTextField cognomeField ,JTextField corsoField ,JDateChooser dateChooser ,JLabel cfLab ,JTextField cfField){
		
		String tmpNome = nomeField.getText();
		String tmpCorso = corsoField.getText();
		String tmpCognome = cognomeField.getText();

		if(!isEmptyField(corsoField)) {
			if(isWhatYouWant(tmpCorso, 0)) {
				
				if(!isEmptyField(nomeField)) {
					if(isWhatYouWant(tmpNome, 0)) {
						
						if(!isEmptyField(cognomeField)) {
							if(isWhatYouWant(tmpCognome, 0)) {
								
								
								Date date;
								String dbDate;
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								date = dateChooser.getDate();
								dbDate = sdf.format(date);
										//controllo data ma prima cf
										if(controlloCF(cfField, cfLab) == 1) {
											//input corretto
											//Inserimento
											/////INSERIMENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO dbDate
											
											
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
				jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, corsoField, 3);
		}else
			jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(cfLab), null, null, 7);
		

	}
	
	public int isEnbl(JTextField field ,int value) {
		
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
			
	}
	
	public void ricercaStudente(JTextField nome ,JTextField cognome ,JTextField cf ,int flagNome ,int flagCognome ,int flagCf ,JLabel label) {
		
		String tmpNome = nome.getText();
		String tmpCognome = cognome.getText();
		String tmpCf = cf.getText();
		
			
		if(flagNome == 1 && flagCognome == 1 && flagCf == 1 ) {
			//TUTTO
			
			//controlla anche se vuoto
			if(controlloCF(cf ,label) == 1) {
				
				if(!tmpNome.isEmpty()) {
					if(isWhatYouWant(tmpNome, 0)) {
						
						if(!tmpCognome.isEmpty()) {
							if(isWhatYouWant(tmpCognome, 0)) {
								//RICERCA
								
							}else
								jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
						}else
							jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
						
					}else
						jpanelManagementCreaCorsoFrame(null, null, nome, 0);
				}else
					jpanelManagementCreaCorsoFrame(null, null, nome, 0);
				
			}else
				jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(label), null, cf, 6);
			
		}
		
		if(flagNome == 1 && flagCognome == 1 && flagCf == 0 ) {
			//NOME E COGNOME
			
			if(!tmpNome.isEmpty()) {
				if(isWhatYouWant(tmpNome, 0)) {
					
					if(!tmpCognome.isEmpty()) {
						if(isWhatYouWant(tmpCognome, 0)) {
							//RICERCA
							
						}else
							jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
					}else
						jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
					
				}else
					jpanelManagementCreaCorsoFrame(null, null, nome, 0);
			}else
				jpanelManagementCreaCorsoFrame(null, null, nome, 0);
			
		}
		
		if(flagNome == 1 && flagCognome == 0 && flagCf == 1 ) {
			//NOME E CF
			
			//controlla anche se vuoto cf
			if(controlloCF(cf ,label) == 1) {
				
				if(!tmpNome.isEmpty()) {
					if(isWhatYouWant(tmpNome, 0)) {
						//RICERCA
						
					}else
						jpanelManagementCreaCorsoFrame(null, null, nome, 0);
				}else
					jpanelManagementCreaCorsoFrame(null, null, nome, 0);
				
			}else
				jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(label), null, cf, 6);
		}
		
		if(flagNome == 1 && flagCognome == 0 && flagCf == 0 ) {
			//SOLO NOME
			
			if(!tmpNome.isEmpty()) {
				if(isWhatYouWant(tmpNome, 0)) {
					//RICERCA NOME
					
				}else
					jpanelManagementCreaCorsoFrame(null, null, nome, 0);
			}else
				jpanelManagementCreaCorsoFrame(null, null, nome, 0);
			}
		
		if(flagNome == 0 && flagCognome == 1 && flagCf == 1 ) {
			//COGNOME E CF
			//controlla anche se vuoto
			if(controlloCF(cf ,label) == 1) {
			
				if(!tmpCognome.isEmpty()) {
					if(isWhatYouWant(tmpCognome, 0)) {
						//RICERCA COGNOME E CF
						
					}else
						jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
				}else
					jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
				
			}else
				jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(label), null, cf, 6);
		}
		
		
		if(flagNome == 0 && flagCognome == 1 && flagCf == 0 ) {
			//SOLO COGNOME
			if(!tmpCognome.isEmpty()) {
				if(isWhatYouWant(tmpCognome, 0)) {
					//RICERCA COGNOME
					
				}else
					jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
			}else
				jpanelManagementCreaCorsoFrame(null, null, cognome, 4);
		}
		
		if(flagNome == 0 && flagCognome == 0 && flagCf == 1 ) {
			//SOLO CF
			//controlla anche se vuoto
			if(controlloCF(cf ,label) == 1) {
				
			}else
				jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(label), null, cf, 6);
		}
		
		if(flagNome == 0 && flagCognome == 0 && flagCf == 0 ) {
			//RICERCA NULLA SOLO ERRORE
			jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(label), null, null, 7);
			
		}
	}
	
	//TODO
	public void ricercaStudenti(JTextField corso ,JLabel labelCorso) {
		String tmpCorso = corso.getText();
		if(!tmpCorso.isEmpty()) {
			//effettua ricerca
			
		}else
			jpanelManagementCreaCorsoFrame((JFrame) SwingUtilities.getRoot(labelCorso), null, null, 7);
	}
}

