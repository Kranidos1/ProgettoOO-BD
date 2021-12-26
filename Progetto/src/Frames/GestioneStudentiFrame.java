package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Oggetti.DAO.ConnectionDao;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GestioneStudentiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	
	public GestioneStudentiFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestioneStudentiFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanel();
		panel.setBounds(0, 0, 501, 516);
		getContentPane().add(panel);
		
		JButton buttonRicercaStudente = new JButton("Ricerca Studente");
		buttonRicercaStudente.setForeground(Color.RED);
		buttonRicercaStudente.setBorder(new RoundBorderBotton(10));
		buttonRicercaStudente.setBackground(Color.WHITE);
		buttonRicercaStudente.setBounds(124, 254, 250, 37);
		panel.add(buttonRicercaStudente);
		
		JButton buttonVisualizzaStats = new JButton("Visualizza Statistiche Studenti");
		buttonVisualizzaStats.setForeground(Color.RED);
		buttonVisualizzaStats.setBorder(new RoundBorderBotton(10));
		buttonVisualizzaStats.setBackground(Color.WHITE);
		buttonVisualizzaStats.setBounds(124, 334, 250, 37);
		panel.add(buttonVisualizzaStats);
		
		JButton buttonBocciaPromuovi = new JButton("Boccia/Promuovi");
		buttonBocciaPromuovi.setForeground(Color.RED);
		buttonBocciaPromuovi.setBorder(new RoundBorderBotton(10));
		buttonBocciaPromuovi.setBackground(Color.WHITE);
		buttonBocciaPromuovi.setBounds(124, 415, 250, 37);
		panel.add(buttonBocciaPromuovi);
		
		JButton buttonIscrivi = new JButton("Iscrivi");
		buttonIscrivi.setForeground(Color.RED);
		buttonIscrivi.setBorder(new RoundBorderBotton(10));
		buttonIscrivi.setBackground(Color.WHITE);
		buttonIscrivi.setBounds(124, 175, 250, 37);
		panel.add(buttonIscrivi);
		setVisible(true);
		
		buttonIscrivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new IscriviStudenteFrame();
				
			}
		});
		
		buttonRicercaStudente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new RicercaStudenteFrame();
				
			}
		});
		
		buttonVisualizzaStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new VisualizzaStatisticheStudentiFrame();
				
			}
		});
		
		buttonBocciaPromuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new BocciaPromuoviFrame();
				
			}
		});
	}
	
	
}
