import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Controller implements ControlloStringhe {

	@Override
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

	@Override
	public boolean isEmptyArea(JTextArea input) {
		// TODO Auto-generated method stub
		String tmp = input.getText();
		if(tmp.isEmpty()) {
			return true;
		}else
			return false;
	}

	@Override
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
		
		//SI RIFERISCE A FLAG 0 = a, 1 = b , 2 = c , 3 = d , 4 = f
		switch(flag) {
		case 0:
			JOptionPane.showMessageDialog(fram, "Invalid Name", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 1:
			
			JOptionPane.showMessageDialog(fram, "Invalid NumberMax Format", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 2:
			
			JOptionPane.showMessageDialog(fram, "Invalid NumberMin Format", "Invalid input", JOptionPane.ERROR_MESSAGE);
			inputField.setText("");
			break;
		case 3:
			
			JOptionPane.showMessageDialog(fram, "Empty Description.Invalid Description Format", "Invalid input", JOptionPane.ERROR_MESSAGE);
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
	
	public void insertCorsoDb(JFrame fram ,JTextField nome ,JTextField max ,JTextField min ,JTextArea areaDescrizione ,JTextArea areaTemi) {
		String name,maxString,minString;
		boolean a,b,c,d,f,g,z,v,u;
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
						//CONTROLO SU LISTA DI SELECTED THEME
						
						
					}else
						jpanelManagementCreaCorsoFrame(fram,areaDescrizione ,null ,3);
				}else
					jpanelManagementCreaCorsoFrame(fram,null,min,2);
			}else
				jpanelManagementCreaCorsoFrame(fram,null,max,1);
				
		}else
			jpanelManagementCreaCorsoFrame(fram,null ,nome ,0);
		
	}
	
	public void insertNewThemeFromField(JFrame fram ,JTextField field ,JTextArea area) {
		
		String theme = field.getText();
		boolean control , onlychar;
		//controllo field theme vuoto e anche numerico
		control = theme.isEmpty();
		
		if(control == true) {
			
			JOptionPane.showMessageDialog(fram, "Empty Description,write something.", "Invalid input", JOptionPane.INFORMATION_MESSAGE);
			
		}else {
			
			onlychar = isWhatYouWant(theme, 0);
			
			if(onlychar == true) {
				
				area.setText(theme);
				field.setEnabled(false);	
				
			}else {
				
				JOptionPane.showMessageDialog(fram, "Invalid input,only char admitted.", "Invalid input", JOptionPane.ERROR_MESSAGE);
				field.setText("");
				
			}		
		}
	}
}

