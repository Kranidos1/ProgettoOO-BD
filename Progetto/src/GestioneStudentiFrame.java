import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestioneStudentiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	
	public GestioneStudentiFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\matti\\Desktop\\lastin.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanel();
		panel.setBounds(0, 0, 501, 516);
		getContentPane().add(panel);
		
		JButton buttonIscrivi = new JButton("Iscrivi");
		buttonIscrivi.setForeground(Color.RED);
		buttonIscrivi.setBorder(new RoundBorderBotton(10));
		buttonIscrivi.setBackground(Color.WHITE);
		buttonIscrivi.setBounds(93, 156, 187, 37);
		panel.add(buttonIscrivi);
		
		JButton buttonVisualizzaStats = new JButton("Visualizza Statistiche Studente");
		buttonVisualizzaStats.setForeground(Color.RED);
		buttonVisualizzaStats.setBorder(new RoundBorderBotton(10));
		buttonVisualizzaStats.setBackground(Color.WHITE);
		buttonVisualizzaStats.setBounds(93, 236, 187, 37);
		panel.add(buttonVisualizzaStats);
		
		JButton buttonListaStudenti = new JButton("Boccia/Promuovi");
		buttonListaStudenti.setForeground(Color.RED);
		buttonListaStudenti.setBorder(new RoundBorderBotton(10));
		buttonListaStudenti.setBackground(Color.WHITE);
		buttonListaStudenti.setBounds(93, 316, 187, 37);
		panel.add(buttonListaStudenti);
		setVisible(true);
		
		buttonIscrivi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new IscriviStudenteFrame();
				
			}
		});
		
	}
}
