package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestisciLezioniFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	
	public GestisciLezioniFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		JButton buttonCreaLezione = new JButton("Crea Lezione");
		buttonCreaLezione.setForeground(Color.RED);
		buttonCreaLezione.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonCreaLezione.setBorder(new RoundBorderBotton(10));
		buttonCreaLezione.setBackground(Color.WHITE);
		buttonCreaLezione.setBounds(160, 156, 178, 41);
		panel.add(buttonCreaLezione);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 133, 501, 12);
		panel.add(horizontalStrut);
		
		JButton buttonGestisciPresenze = new JButton("Gestisci Presenze");
		buttonGestisciPresenze.setForeground(Color.RED);
		buttonGestisciPresenze.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		buttonGestisciPresenze.setBorder(new RoundBorderBotton(10));
		buttonGestisciPresenze.setBackground(Color.WHITE);
		buttonGestisciPresenze.setBounds(160, 249, 178, 41);
		panel.add(buttonGestisciPresenze);
		
		controller = new Controller();
		
		buttonCreaLezione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new CreaLezione();
				
			}
		});
		
		buttonGestisciPresenze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new GestisciPresenzeFrame();
				
			}
		});
		
		setVisible(true);
	}
}
