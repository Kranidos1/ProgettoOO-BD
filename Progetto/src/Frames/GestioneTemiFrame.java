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

public class GestioneTemiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	
	public GestioneTemiFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestioneCorsiFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		controller = new Controller();
		
		JButton buttonAreaTematica = new JButton("Crea Area Tematica");
		buttonAreaTematica.setForeground(Color.RED);
		buttonAreaTematica.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonAreaTematica.setBorder(new RoundBorderBotton(10));
		buttonAreaTematica.setBackground(Color.WHITE);
		buttonAreaTematica.setBounds(152, 171, 198, 53);
		panel.add(buttonAreaTematica);
		
		JButton buttonVisuallizzaTemi = new JButton("Visualliza/Modifica Temi");
		buttonVisuallizzaTemi.setForeground(Color.RED);
		buttonVisuallizzaTemi.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonVisuallizzaTemi.setBorder(new RoundBorderBotton(10));
		buttonVisuallizzaTemi.setBackground(Color.WHITE);
		buttonVisuallizzaTemi.setBounds(152, 263, 198, 53);
		panel.add(buttonVisuallizzaTemi);
		setVisible(true);
		
		buttonAreaTematica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.newTheme(panel.labelHourDate);
				
			}
		});
		
		buttonVisuallizzaTemi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//passa al frame
				setVisible(false);
				
				VisualizzaModificaTemiFrame frame = new VisualizzaModificaTemiFrame();
				
			}
		});
	}
}
