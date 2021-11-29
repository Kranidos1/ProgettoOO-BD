package Frames;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public interface ControlloEOperazioniSuFrame {
	
	//return true
	public boolean isWhatYouWant(String input , int flag);
	public void newTheme(JLabel label);
	// 0 nome   1 max  2 min 3 descrizione 4 cognome 5 data 6 cf 7 generico 8 maggiorenne
	public void jpanelManagementCreaCorsoFrame(JFrame fram ,JTextArea inputArea ,JTextField inputField ,int flag) ;
	public int textEnableDisable(JButton button ,JFrame fram ,int count ,JTextField field ,JPanel pabel);
	public void insertCorsoDb(JFrame fram ,JTextField nome ,JTextField max ,JTextField min ,JTextArea areaDescrizione ,DefaultListModel<String> model ,int flag ,String corsoId);
	public void insertNewThemeFromField(JFrame fram ,JTextField field ,DefaultListModel<String> model);
	public void insertInListAndControl(DefaultListModel<String> model ,String control ,JFrame fram);
	public int controlloCF(JTextField cfTextField ,JLabel cfLabel);
	public void controlloInserimentoStudente(JTextField nomeField , JTextField cognomeField ,JList list ,JDateChooser dateChooser ,JLabel cfLab ,JTextField cfField);
	//TextField generico con controllocheckbox e ricerca
	public int isEnbl(JTextField field ,int value ,JDateChooser date ,JList lista);
	
}
