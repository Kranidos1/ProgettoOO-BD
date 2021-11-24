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

public class GestioneCorsiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private CreaCorsoFrame creaCorso;
	private StatisticheCorsoFrame statsCorso;
	
	public GestioneCorsiFrame() {
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		JButton creaCorsoButton = new JButton("Crea Corso");
		creaCorsoButton.setForeground(Color.RED);
		creaCorsoButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		creaCorsoButton.setBorder(new RoundBorderBotton(10));
		creaCorsoButton.setBackground(Color.WHITE);
		creaCorsoButton.setBounds(153, 206, 178, 41);
		panel.add(creaCorsoButton);
		
		JButton visualizzaStatsButton = new JButton("Visualizza Statistiche");
		visualizzaStatsButton.setForeground(Color.RED);
		visualizzaStatsButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		visualizzaStatsButton.setBorder(new RoundBorderBotton(10));
		visualizzaStatsButton.setBackground(Color.WHITE);
		visualizzaStatsButton.setBounds(153, 279, 178, 41);
		panel.add(visualizzaStatsButton);
		
		JButton modificaCorsiButton = new JButton("Elimina/Modifica Corsi");
		modificaCorsiButton.setForeground(Color.RED);
		modificaCorsiButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		modificaCorsiButton.setBorder(new RoundBorderBotton(10));
		modificaCorsiButton.setBackground(Color.WHITE);
		modificaCorsiButton.setBounds(153, 358, 178, 41);
		panel.add(modificaCorsiButton);
		
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
		
		setVisible(true);
	}
}
