package Frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

public class GestisciPresenzeFrame extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTextField corsoField;
	private JTable table;
	private GeneralPanelGrande panel;
	private JTextField keyField;
	private int flagCorso = 0;
	private int flagKey = 0;
	private int flagTheme = 0;
	private JTextField themeField;
	
	public GestisciPresenzeFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 640);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanelGrande();
		getContentPane().add(panel);
		
		controller = new Controller();
		JPanel primoStepPanel = new JPanel();
		primoStepPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Primo Step"));
		primoStepPanel.setBackground(Color.white);
		primoStepPanel.setBounds(10, 110, 200, 427);
		panel.add(primoStepPanel);
		primoStepPanel.setLayout(null);
		
		DefaultListModel model = new DefaultListModel();
		JList listCorsi = new JList(model);
		listCorsi.setBounds(10, 174, 181, 242);
		primoStepPanel.add(listCorsi);
		listCorsi.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Corsi"));
		
		corsoField = new JTextField();
		corsoField.setEnabled(false);
		corsoField.setBounds(49, 41, 132, 20);
		primoStepPanel.add(corsoField);
		corsoField.setColumns(10);
		
		JLabel corsoLabel = new JLabel("Corso");
		corsoLabel.setBounds(10, 40, 36, 22);
		primoStepPanel.add(corsoLabel);
		corsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton cercaCorsoButton = new JButton("Cerca Corso");
		cercaCorsoButton.setBounds(71, 153, 95, 20);
		primoStepPanel.add(cercaCorsoButton);
		cercaCorsoButton.setForeground(Color.RED);
		cercaCorsoButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cercaCorsoButton.setBorder(new RoundBorderBotton(10));
		cercaCorsoButton.setBackground(Color.WHITE);
		
		keyField = new JTextField();
		keyField.setEnabled(false);
		keyField.setColumns(10);
		keyField.setBounds(49, 84, 132, 20);
		primoStepPanel.add(keyField);
		
		JLabel keyLabel = new JLabel("Key");
		keyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keyLabel.setBounds(10, 83, 36, 22);
		primoStepPanel.add(keyLabel);
		
		JCheckBox checkCorso = new JCheckBox("");
		checkCorso.setBounds(173, 18, 21, 16);
		primoStepPanel.add(checkCorso);
		checkCorso.setBackground(Color.white);
		
		JCheckBox checkKey = new JCheckBox("");
		checkKey.setBackground(Color.WHITE);
		checkKey.setBounds(173, 64, 21, 16);
		primoStepPanel.add(checkKey);
		
		themeField = new JTextField();
		themeField.setEnabled(false);
		themeField.setColumns(10);
		themeField.setBounds(49, 126, 132, 20);
		primoStepPanel.add(themeField);
		
		JLabel lblTheme = new JLabel("Theme");
		lblTheme.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTheme.setBounds(0, 125, 46, 22);
		primoStepPanel.add(lblTheme);
		
		JCheckBox checkTheme = new JCheckBox("");
		checkTheme.setBackground(Color.WHITE);
		checkTheme.setBounds(173, 107, 21, 16);
		primoStepPanel.add(checkTheme);
		
		JPanel secondoStepPanel = new JPanel();
		secondoStepPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Secondo Step"));
		secondoStepPanel.setBackground(Color.white);
		secondoStepPanel.setBounds(220, 123, 393, 414);
		panel.add(secondoStepPanel);
		secondoStepPanel.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd");
		Date dataAttuale = new Date();
		String data = formatDate.format(dataAttuale);
		Date actualDate;
		
		try {
			
			actualDate = formatDate.parse(data);
			dateChooser.setMaxSelectableDate(actualDate);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		dateChooser.setBounds(10, 40, 158, 20);
		secondoStepPanel.add(dateChooser);
		
		JButton btnCercaLezione = new JButton("Cerca Lezione");
		btnCercaLezione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCercaLezione.setForeground(Color.RED);
		btnCercaLezione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCercaLezione.setBorder(new RoundBorderBotton(10));
		btnCercaLezione.setBackground(Color.WHITE);
		btnCercaLezione.setBounds(178, 40, 95, 20);
		secondoStepPanel.add(btnCercaLezione);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CF", "%Presenze","N.Lezioni"
			}
		));
		table.setBounds(108, 474, 1, 1);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 71, 373, 272);
		secondoStepPanel.add(scrollPane);
		
		JButton buttonPresente = new JButton("Presente");
		buttonPresente.setForeground(Color.RED);
		buttonPresente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonPresente.setBorder(new RoundBorderBotton(10));
		buttonPresente.setBackground(Color.WHITE);
		buttonPresente.setBounds(71, 354, 114, 32);
		secondoStepPanel.add(buttonPresente);
		
		JButton buttonAssente = new JButton("Assente");
		buttonAssente.setForeground(Color.RED);
		buttonAssente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonAssente.setBorder(new RoundBorderBotton(10));
		buttonAssente.setBackground(Color.WHITE);
		buttonAssente.setBounds(223, 354, 114, 32);
		secondoStepPanel.add(buttonAssente);
		
		JLabel dataLabel = new JLabel("Data");
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setBounds(10, 22, 47, 14);
		secondoStepPanel.add(dataLabel);
		
		JLabel corsoTrovatoLabel = new JLabel("Corso");
		corsoTrovatoLabel.setFont(new Font("Impact", Font.PLAIN, 13));
		corsoTrovatoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		corsoTrovatoLabel.setBounds(223, 11, 142, 18);
		secondoStepPanel.add(corsoTrovatoLabel);
		
		
		checkCorso.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagCorso = controller.isEnbl(corsoField, flagCorso, null ,null);
				
			}
			
		});
		
		checkKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagKey = controller.isEnbl(keyField, flagKey, null ,null);
				
			}
			
		});
		
		checkTheme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagTheme = controller.isEnbl(themeField, flagTheme, null ,null);
				
			}
			
		});
		
		cercaCorsoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.cercaCorso(themeField, corsoField, keyField, flagTheme, flagCorso, flagKey, dateChooser, model);
				
			}
		});
		
		//USALO DOPO RIEMPIMENTO LISTA
		listCorsi.addMouseListener(new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList) evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = listCorsi.locationToIndex(evt.getPoint());
		            //PRENDE OGGETTO CLICCATO DUE VOLTE
		            corsoTrovatoLabel.setText(listCorsi.getSelectedValue().toString());
		            listCorsi.setEnabled(false);
		        }
		    }
		    
		});
		
		setVisible(true);
	}
}
