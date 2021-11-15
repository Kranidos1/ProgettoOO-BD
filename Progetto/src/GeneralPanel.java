import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.Timer;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Frame;
import java.awt.Insets;

public class GeneralPanel extends JPanel {
;
	private Timer timer;
	private Date data;
	private String stringDate;
	public JLabel labelHourDate;
	private CreaCorsoFrame frame;
	private	JMenuItem corsoMenuItem;
	private boolean control;
	private int flag = 0;
	private JFrame fram;
	private FrameDiScelta frameHome;
	private Controller controller;
	private StatisticheCorsoFrame frameStats;
	
	public GeneralPanel() {

		setBackground(Color.white);
		setLayout(null);
		setSize(501, 516);
		
		controller = new Controller();
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 0, 1));
		menuBar.setBounds(0, 0, 501, 22);
		menuBar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		fileMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		menuBar.add(fileMenu);
		
		JMenuItem exitMenu = new JMenuItem("Exit");
		fileMenu.add(exitMenu);
		
		JMenu navigaMenu = new JMenu("Naviga");
		navigaMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		menuBar.add(navigaMenu);
		
		JMenuItem homeMenuItem = new JMenuItem("Home");

		navigaMenu.add(homeMenuItem);
		
		JMenu creaMenu = new JMenu("Crea");
		creaMenu.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.black));
		navigaMenu.add(creaMenu);
		
		corsoMenuItem = new JMenuItem("Corso");
		creaMenu.add(corsoMenuItem);
		corsoMenuItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		
		JMenuItem areaTematicaMenuItem = new JMenuItem("AreaTematica");
		creaMenu.add(areaTematicaMenuItem);

		
		JMenu gestisciStudentiMenu = new JMenu("Gestisci Studenti");
		navigaMenu.add(gestisciStudentiMenu);
		
		JMenuItem iscriviMenuItem = new JMenuItem("Iscrivi");
		iscriviMenuItem.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
		gestisciStudentiMenu.add(iscriviMenuItem);

		
		JMenuItem ricercaMenuItem = new JMenuItem("Ricerca");
		gestisciStudentiMenu.add(ricercaMenuItem);
		
		JMenu GestisciCorsoMenu = new JMenu("Gestisci Corso");
		GestisciCorsoMenu.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		navigaMenu.add(GestisciCorsoMenu);
		
		JMenuItem visualizzaStatisticheMenuItem = new JMenuItem("Visualizza Statistiche");
		GestisciCorsoMenu.add(visualizzaStatisticheMenuItem);
		
		JMenuItem modificaMenuItem = new JMenuItem("Modifica/Elimina Corso");
		GestisciCorsoMenu.add(modificaMenuItem);

		
		JLabel labelTitle = new JLabel("GRU-Solution");
		labelTitle.setForeground(Color.BLACK);
		labelTitle.setFont(new Font("Kimberley Bl", Font.PLAIN, 35));
		labelTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		labelTitle.setBounds(80, 57, 227, 53);
		add(labelTitle);
		
		JLabel labelProgramImage = new JLabel("");
		labelProgramImage.setBounds(317, 33, 109, 114);
		
		ImageIcon image = new ImageIcon("C:\\Users\\matti\\Desktop\\programImage.gif"
				+ "");
		labelProgramImage.setIcon(image);
		add(labelProgramImage);
		
		labelHourDate = new JLabel("");
		labelHourDate.setHorizontalAlignment(SwingConstants.CENTER);
		labelHourDate.setFont(new Font("Arial Black", Font.PLAIN, 17));
		labelHourDate.setBounds(236, 463, 265, 53);
		labelHourDate.setBorder(BorderFactory.createMatteBorder(2, 2, 0, 0, Color.black));
		add(labelHourDate);
		//Raggira lo start del timer dopo un secondo
		data = new Date();
		stringDate = data.toLocaleString();
		labelHourDate.setText(stringDate);
		
		areaTematicaMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//AGGIUNGE AREATEMATICA
				controller.newTheme(labelHourDate);
				
			}
		});
		
		corsoMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//PRENDE IL FRAME PADRE
				fram = (JFrame) SwingUtilities.getRoot(labelHourDate);
				fram.setVisible(false);
				
				frame = new CreaCorsoFrame();
				frame.setVisible(true);
			}
			
		});
		
		homeMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fram = (JFrame) SwingUtilities.getRoot(labelHourDate);
				fram.setVisible(false);
				frameHome = new FrameDiScelta();
				
			}
			
		});
		
		exitMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		setTimer();
		
		visualizzaStatisticheMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fram = (JFrame) SwingUtilities.getRoot(labelHourDate);
				fram.setVisible(false);
				frameStats = new StatisticheCorsoFrame();
				frameStats.setVisible(true);
				
			}
			
		});
		
	}
	
	
	
	public void setTimer() {
		timer = new Timer(1000,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				data = new Date();
				stringDate = data.toLocaleString();
				labelHourDate.setText(stringDate);
			}		
		});	
		timer.setRepeats(true);
		timer.start();
	}
}
