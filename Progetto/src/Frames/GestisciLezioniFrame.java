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
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GestisciLezioniFrame extends JFrame {
	
	private ConnectionDao connectionDao;
	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	
	public GestisciLezioniFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestisciLezioniFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 518, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		connectionDao = new ConnectionDao();
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
		buttonGestisciPresenze.setBounds(160, 224, 178, 41);
		panel.add(buttonGestisciPresenze);
		
		JButton buttonVisualizzaLezioni = new JButton("Visualizza Lezioni");
		buttonVisualizzaLezioni.setForeground(Color.RED);
		buttonVisualizzaLezioni.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonVisualizzaLezioni.setBorder(new RoundBorderBotton(10));
		buttonVisualizzaLezioni.setBackground(Color.WHITE);
		buttonVisualizzaLezioni.setBounds(160, 293, 178, 41);
		panel.add(buttonVisualizzaLezioni);
		
		controller = new Controller();
		
		buttonCreaLezione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new CreaLezioneFrame();
				
			}
		});
		
		buttonGestisciPresenze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new GestisciPresenzeFrame();
				
			}
		});
		
		buttonVisualizzaLezioni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				new VisualizzaLezioniFrame();
				
			}
		});
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	try {
					connectionDao.getConnection().close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            	
            }
        });
		setVisible(true);
	}
}
