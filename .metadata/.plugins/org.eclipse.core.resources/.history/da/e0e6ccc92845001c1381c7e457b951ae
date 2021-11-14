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

public class FrameDiScelta extends JFrame{
	
	private GeneralPanel panel;
	private boolean control;
	private int flag = 0;
	private Controller controller;
	
	public FrameDiScelta(){
		//RIGA 16 24 FRAME GENERICO
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\matti\\Desktop\\lastin.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		setVisible(true);
		//stop frame generico
		controller = new Controller();
		
		JButton buttonGestioneCorsi = new JButton("Gestione Corsi");
		buttonGestioneCorsi.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestioneCorsi.setBounds(10, 226, 160, 32);
		buttonGestioneCorsi.setBorder(new RoundBorderBotton(10));
		buttonGestioneCorsi.setForeground(Color.BLACK);
		buttonGestioneCorsi.setBackground(Color.WHITE);
		panel.add(buttonGestioneCorsi);
		
		JButton buttonGestioneLezioni = new JButton("Gestione Lezioni");
		buttonGestioneLezioni.setForeground(Color.BLACK);
		buttonGestioneLezioni.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestioneLezioni.setBorder(new RoundBorderBotton(10));
		buttonGestioneLezioni.setBackground(Color.WHITE);
		buttonGestioneLezioni.setBounds(331, 226, 160, 32);
		panel.add(buttonGestioneLezioni);
		
		JButton btnGestioneStudenti = new JButton("Gestione Studenti");
		btnGestioneStudenti.setForeground(Color.RED);
		btnGestioneStudenti.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		btnGestioneStudenti.setBorder(new RoundBorderBotton(10));
		btnGestioneStudenti.setBackground(Color.WHITE);
		btnGestioneStudenti.setBounds(165, 283, 160, 32);
		panel.add(btnGestioneStudenti);
		
		JButton buttonAreaTematica = new JButton("Crea Area Tematica");
		buttonAreaTematica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.newTheme(panel.labelHourDate);
			}			
			
		});
		
		buttonAreaTematica.setForeground(Color.RED);
		buttonAreaTematica.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonAreaTematica.setBorder(new RoundBorderBotton(10));
		buttonAreaTematica.setBackground(Color.WHITE);
		buttonAreaTematica.setBounds(165, 168, 160, 32);
		panel.add(buttonAreaTematica);
		

	}
}
