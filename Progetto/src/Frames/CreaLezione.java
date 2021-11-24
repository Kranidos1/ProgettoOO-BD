package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import javax.swing.SpinnerModel;
import java.awt.TextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreaLezione extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private JTextField titoloField;
	private JTextField textField;
	private Controller controller;
	
	public CreaLezione() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		panel.setLocation(0, 11);
		getContentPane().add(panel);
		
		controller = new Controller();
		
		JLabel titoloLabel = new JLabel("Titolo");
		titoloLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titoloLabel.setBounds(10, 142, 58, 21);
		panel.add(titoloLabel);
		
		titoloField = new JTextField();
		titoloField.setBounds(77, 142, 181, 21);
		panel.add(titoloField);
		titoloField.setColumns(10);
		
		JLabel dataLabel = new JLabel("Data");
		dataLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dataLabel.setBounds(22, 180, 46, 21);
		panel.add(dataLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(77, 181, 201, 20);
		
		Date dataAttuale = new Date();
		
		int anno = dataAttuale.getYear();
		int day = dataAttuale.getDate();
		int month = dataAttuale.getMonth();
		
		Integer dayMin = day + 1;
		Integer annoMin = anno + 1900;
		Integer monthMin = month + 1;
		
		String yearMin = annoMin.toString();
		String giornoMin = dayMin.toString();
		String meseMin = monthMin.toString();
		
		String datMin;
		Date dataMin;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat hourForm = new SimpleDateFormat("kk:mm");
		datMin = yearMin + "." + meseMin + "."+ giornoMin;;
		
		try {

			dataMin = format.parse(datMin);
			dateChooser.setMinSelectableDate(dataMin);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateChooser.setLocale(getLocale());
		dateChooser.setDateFormatString("yyyy.MM.dd");
		panel.add(dateChooser);
		
		JPanel panelInizio = new JPanel();
		panelInizio.setBackground(Color.WHITE);
		panelInizio.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black), "Inizio"));
		panelInizio.setBounds(50, 212, 85, 63);
		panel.add(panelInizio);
		panelInizio.setLayout(null);
		
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.HOUR_OF_DAY);
		JSpinner spinnerInizio = new JSpinner(new SpinnerDateModel(new Date(1637337589030L), null, null, Calendar.HOUR_OF_DAY));
		
		spinnerInizio.setBounds(10, 21, 65, 31);
		panelInizio.add(spinnerInizio);
		spinnerInizio.setEditor(new JSpinner.DateEditor(spinnerInizio, "kk:mm"));
		
		JPanel panelDurata = new JPanel();
		panelDurata.setLayout(null);
		panelDurata.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black), "Durata"));
		panelDurata.setBackground(Color.WHITE);
		panelDurata.setBounds(173, 212, 85, 63);
		panel.add(panelDurata);
		
		JSpinner spinnerDurata = new JSpinner(new SpinnerDateModel(new Date(1637337589030L), null, null, Calendar.HOUR_OF_DAY));
		spinnerDurata.setBounds(10, 21, 65, 31);
		spinnerDurata.setEditor(new JSpinner.DateEditor(spinnerDurata, "kk:mm"));
		panelDurata.add(spinnerDurata);
		
		JTextPane textArea = new JTextPane();
		textArea.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		textArea.setBounds(10, 286, 481, 166);
		panel.add(textArea);
		
		JButton buttonSave = new JButton("Save");
		buttonSave.setForeground(Color.RED);
		buttonSave.setBorder(new RoundBorderBotton(10));
		buttonSave.setBackground(Color.WHITE);
		buttonSave.setBounds(296, 153, 97, 37);
		panel.add(buttonSave);
		SimpleDateFormat form = new SimpleDateFormat("kk:mm");
		Object pippo = spinnerInizio.getValue();
		String boh = form.format(pippo);
	
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.inserisciCorso(titoloField, dateChooser, spinnerInizio, spinnerDurata, textArea, form, hourForm);
				
			}
		});

		
		
		setVisible(true);
	}
}
