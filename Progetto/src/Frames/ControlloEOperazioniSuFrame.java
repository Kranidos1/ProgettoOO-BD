package Frames;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public interface ControlloEOperazioniSuFrame {
	
	//return true
	public boolean isWhatYouWant(String input , int flag);
	public void newTheme(JLabel label);
	// 0 nome   1 max  2 min 3 descrizione 4 cognome 5 data 6 cf 7 generico 8 maggiorenne 9 max < min 10 nessuna selezione
	public void jpanelManagementCreaCorsoFrame(JFrame fram ,JTextArea inputArea ,JTextField inputField ,int flag) ;
	public int textEnableDisable(JButton button ,JFrame fram ,int count ,JTextField field ,JPanel pabel);
	//flag 0 insert flag 1 update
	public void insertCorsoDb(JFrame fram ,JTextField nome ,JTextField max ,JTextField min ,JTextArea areaDescrizione ,DefaultListModel<String> model ,int flag ,String corsoId);
	//flag 0 insert flag 1 update
	public int inserisciLezione(String corso ,JTextField title ,JDateChooser dateChooser ,JSpinner spinnerIn ,JSpinner spinnerDur ,JTextPane area ,SimpleDateFormat hourForm ,int lezioneIdUpd ,int flag);
	public void insertNewThemeFromField(JFrame fram ,JTextField field ,DefaultListModel<String> model);
	public void insertInListAndControl(DefaultListModel<String> model ,String control ,JFrame fram);
	public int controlloCF(JTextField cfTextField ,JLabel cfLabel);
	public void controlloInserimentoStudente(JTextField nomeField , JTextField cognomeField ,JList list ,JDateChooser dateChooser ,JLabel cfLab ,JTextField cfField);
	//TextField generico con controllocheckbox e ricerca
	public int isEnbl(JTextField field ,int value ,JDateChooser date ,JList lista);
	public void ricercaStudente(JTextField nome ,JTextField cognome ,JTextField cf ,JDateChooser dataDateChooser ,int flagNome ,int flagCognome ,int flagCf ,int flagDate ,JLabel label ,DefaultTableModel model);
	//torna numero di studenti
	public int ricercaStudenti(JList corso ,JLabel labelCorso ,DefaultTableModel model);
}
