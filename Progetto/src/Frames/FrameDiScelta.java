package Frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;

public class FrameDiScelta extends JFrame{
	
	private GeneralPanel panel;
	private boolean control;
	private int flag = 0;
	private GestioneCorsiFrame gestioneCorsi;
	
	public FrameDiScelta(){
		//RIGA 16 24 FRAME GENERICO
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrameDiScelta.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		setVisible(true);
		//stop frame generico
		
		JButton buttonGestioneCorsi = new JButton("Gestione Corsi");
		buttonGestioneCorsi.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestioneCorsi.setBounds(10, 263, 198, 53);
		buttonGestioneCorsi.setBorder(new RoundBorderBotton(10));
		buttonGestioneCorsi.setForeground(Color.BLACK);
		buttonGestioneCorsi.setBackground(Color.WHITE);
		panel.add(buttonGestioneCorsi);
		
		JButton buttonGestioneLezioni = new JButton("Gestione Lezioni");
		buttonGestioneLezioni.setForeground(Color.BLACK);
		buttonGestioneLezioni.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestioneLezioni.setBorder(new RoundBorderBotton(10));
		buttonGestioneLezioni.setBackground(Color.WHITE);
		buttonGestioneLezioni.setBounds(293, 263, 198, 53);
		panel.add(buttonGestioneLezioni);
		
		JButton buttonGestioneStudenti = new JButton("Gestione Studenti");
		buttonGestioneStudenti.setForeground(Color.RED);
		buttonGestioneStudenti.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestioneStudenti.setBorder(new RoundBorderBotton(10));
		buttonGestioneStudenti.setBackground(Color.WHITE);
		buttonGestioneStudenti.setBounds(149, 353, 198, 53);
		panel.add(buttonGestioneStudenti);
		
		JButton buttonGestioneTemi = new JButton("Gestione Temi");
		buttonGestioneTemi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
				GestioneTemiFrame frame = new GestioneTemiFrame();
				
			}			
			
		});
		
		buttonGestioneTemi.setForeground(Color.RED);
		buttonGestioneTemi.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestioneTemi.setBorder(new RoundBorderBotton(10));
		buttonGestioneTemi.setBackground(Color.WHITE);
		buttonGestioneTemi.setBounds(149, 175, 198, 53);
		panel.add(buttonGestioneTemi);
		
		buttonGestioneCorsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gestioneCorsi = new GestioneCorsiFrame();
			}
		});
		
		buttonGestioneStudenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new GestioneStudentiFrame();
				
			}
		});
		
		buttonGestioneLezioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new GestisciLezioniFrame();
				
			}
		});
		
	}
}
