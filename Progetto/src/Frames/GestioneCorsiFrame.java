package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class GestioneCorsiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private CreaCorsoFrame creaCorso;
	private StatisticheCorsoFrame statsCorso;
	
	public GestioneCorsiFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestioneCorsiFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		panel.setLocation(0, 0);
		getContentPane().add(panel);
		
		JButton creaCorsoButton = new JButton("Crea Corso");
		creaCorsoButton.setForeground(Color.RED);
		creaCorsoButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		creaCorsoButton.setBorder(new RoundBorderBotton(10));
		creaCorsoButton.setBackground(Color.WHITE);
		creaCorsoButton.setBounds(153, 166, 178, 41);
		panel.add(creaCorsoButton);
		
		JButton visualizzaStatsButton = new JButton("Visualizza Statistiche");
		visualizzaStatsButton.setForeground(Color.RED);
		visualizzaStatsButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		visualizzaStatsButton.setBorder(new RoundBorderBotton(10));
		visualizzaStatsButton.setBackground(Color.WHITE);
		visualizzaStatsButton.setBounds(153, 231, 178, 41);
		panel.add(visualizzaStatsButton);
		
		JButton modificaCorsiButton = new JButton("Elimina/Modifica Corsi");
		modificaCorsiButton.setForeground(Color.RED);
		modificaCorsiButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		modificaCorsiButton.setBorder(new RoundBorderBotton(10));
		modificaCorsiButton.setBackground(Color.WHITE);
		modificaCorsiButton.setBounds(153, 290, 178, 41);
		panel.add(modificaCorsiButton);
		
		JButton buttonDeterminaFineCorso = new JButton("Determina fine corso");
		buttonDeterminaFineCorso.setForeground(Color.RED);
		buttonDeterminaFineCorso.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonDeterminaFineCorso.setBorder(new RoundBorderBotton(10));
		buttonDeterminaFineCorso.setBackground(Color.WHITE);
		buttonDeterminaFineCorso.setBounds(153, 352, 178, 41);
		panel.add(buttonDeterminaFineCorso);
		
		JButton buttonCorsiArchiviati = new JButton("Corsi Archiviati");
		buttonCorsiArchiviati.setForeground(Color.RED);
		buttonCorsiArchiviati.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonCorsiArchiviati.setBorder(new RoundBorderBotton(10));
		buttonCorsiArchiviati.setBackground(Color.WHITE);
		buttonCorsiArchiviati.setBounds(153, 411, 178, 41);
		panel.add(buttonCorsiArchiviati);
		
		creaCorsoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				creaCorso = new CreaCorsoFrame();
				
			}
		});
		
		visualizzaStatsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				statsCorso = new StatisticheCorsoFrame();
			}
		});
		
		modificaCorsiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				EmCorsoFrame nuovoFrame = new EmCorsoFrame();
				
			}
		});
		
		buttonDeterminaFineCorso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new GestioneFineCorsiFrame();
				
			}
		});
		
		buttonCorsiArchiviati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		setVisible(true);
	}
}
