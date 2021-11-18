import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class RicercaStudente extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField cfField;
	private JScrollPane scrollPane;
	private JButton buttonRicerca;
	int flagNome = 0;
	int flagCf = 0;
	int flagCognome = 0;
	private Controller controller;
	
	public RicercaStudente() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\matti\\Desktop\\lastin.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanel();
		panel.setBounds(0, 0, 501, 516);
		getContentPane().add(panel);
		
		controller = new Controller();
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomeLabel.setBounds(52, 144, 46, 20);
		panel.add(nomeLabel);
		
		nomeField = new JTextField();
		nomeField.setEnabled(false);
		nomeField.setBounds(108, 144, 137, 20);
		panel.add(nomeField);
		nomeField.setColumns(10);
		
		cognomeField = new JTextField();
		cognomeField.setEnabled(false);
		cognomeField.setColumns(10);
		cognomeField.setBounds(108, 187, 137, 20);
		panel.add(cognomeField);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeLabel.setBounds(38, 187, 60, 20);
		panel.add(cognomeLabel);
		
		cfField = new JTextField();
		cfField.setEnabled(false);
		cfField.setColumns(10);
		cfField.setBounds(108, 231, 137, 20);
		panel.add(cfField);
		
		JLabel cfLabel = new JLabel("CF");
		cfLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cfLabel.setBounds(52, 231, 46, 20);
		panel.add(cfLabel);
		
		JTable tableStats = new JTable();
		tableStats.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome" ,"Cognome" ,"CF", "Corso","N.Lezioni" ,"Presenze" ,"Assenze  "
			}
		));
		
		scrollPane = new JScrollPane(tableStats);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 269, 501, 183);
		panel.add(scrollPane);
		
		buttonRicerca = new JButton("Ricerca");
		buttonRicerca.setForeground(Color.RED);
		buttonRicerca.setBorder(new RoundBorderBotton(10));
		buttonRicerca.setBackground(Color.WHITE);
		buttonRicerca.setBounds(319, 185, 97, 26);
		panel.add(buttonRicerca);
		
		JCheckBox nomeCheck = new JCheckBox("");
		nomeCheck.setBackground(Color.white);
		nomeCheck.setHorizontalAlignment(SwingConstants.CENTER);
		nomeCheck.setBounds(251, 142, 15, 22);
		panel.add(nomeCheck);
		
		JCheckBox cognomeCheck = new JCheckBox("");
		cognomeCheck.setBackground(Color.white);
		cognomeCheck.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeCheck.setBounds(251, 187, 15, 22);
		panel.add(cognomeCheck);
		
		JCheckBox cfCheck = new JCheckBox("");
		cfCheck.setBackground(Color.white);
		cfCheck.setHorizontalAlignment(SwingConstants.CENTER);
		cfCheck.setBounds(251, 229, 15, 22);
		panel.add(cfCheck);
		
		JButton buttonElimina = new JButton("Elimina Studente");
		buttonElimina.setForeground(Color.RED);
		buttonElimina.setBorder(new RoundBorderBotton(10));
		buttonElimina.setBackground(Color.WHITE);
		buttonElimina.setBounds(52, 463, 126, 26);
		panel.add(buttonElimina);
		setVisible(true);
		
		nomeCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				flagNome = controller.isEnbl(nomeField, flagNome);
				
			}
			
		});
		
		cognomeCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				flagCognome = controller.isEnbl(cognomeField, flagCognome);
				
			}
			
		});
		
		cfCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				flagCf = controller.isEnbl(cfField, flagCf);
				
			}
			
		});
		
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.ricercaStudente(nomeField, cognomeField, cfField, flagNome, flagCognome, flagCf, cfLabel);
				
			}
		});
	}
}
