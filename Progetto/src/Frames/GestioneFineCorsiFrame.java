package Frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import java.awt.*
;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;public class GestioneFineCorsiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	
	public GestioneFineCorsiFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestioneCorsiFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		panel.labelHourDate.setBounds(236, 463, 265, 53);
		panel.setLocation(0, 0);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		controller = new Controller();
		
		DefaultListModel model = new DefaultListModel();
		JList listaCorsi = new JList(model);
		JScrollPane scrollPane = new JScrollPane(listaCorsi);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black),"Corsi"));
		scrollPane.setBounds(10, 135, 249, 317);
		panel.add(scrollPane);
		
		JButton buttonFinito = new JButton("Fine Corso");
		buttonFinito.setForeground(Color.RED);
		buttonFinito.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonFinito.setBorder(new RoundBorderBotton(10));
		buttonFinito.setBackground(Color.WHITE);
		buttonFinito.setBounds(285, 278, 128, 33);
		panel.add(buttonFinito);
		
		CorsoDaoImpl corsoDao = new CorsoDaoImpl();
		model.addAll(corsoDao.getNomiCorsi(controller.getConnection()));
		
		LezioneDaoImpl lezioneDao = new LezioneDaoImpl();
		buttonFinito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int answer = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getRoot(buttonFinito), "Sicuro di voler terminare il corso?", "Confirm", JOptionPane.YES_NO_OPTION);
				
				//RAPPRESENTA IL SI
				if(answer == 0) {
					
					if(listaCorsi.getSelectedValue() != null) {
						
						String corso = listaCorsi.getSelectedValue().toString();
						int row = listaCorsi.getSelectedIndex();
						
						int corsoId = corsoDao.trovaCorsoId(controller.getConnection(), corso);
						//ricordati check su numero lezioni e se tutte le lezioni sono state gestite
						int numLezioniFalse = lezioneDao.countCheckFalse(controller.getConnection(), corsoId);
						int numLezioni = lezioneDao.countLezioni(controller.getConnection(), corsoId);
						
						int pass = numLezioni - numLezioniFalse;
						
						if(numLezioni != 0) {
							
							if(numLezioni <= 6) {
								
								if(pass == numLezioni) {
									
									//UPDATE CHECK
									corsoDao.updateCheckCorso(controller.getConnection(), corsoId);
									JOptionPane.showMessageDialog(null, "Corso terminato correttamente!");
									model.remove(row);
									listaCorsi.revalidate();
									
								}else
									JOptionPane.showMessageDialog(null, "Mancano delle lezioni da gestire!", "ERROR", JOptionPane.ERROR_MESSAGE)
									;
							}else
								JOptionPane.showMessageDialog(null, "Ci sono meno di 6 lezioni per questo corso.Non e' possibile terminarlo!", "ERROR", JOptionPane.ERROR_MESSAGE);
							
						}else
							JOptionPane.showMessageDialog(null, "Non ci sono state lezioni per questo Corso.Non e' possibile terminarlo.", "ERROR", JOptionPane.ERROR_MESSAGE);
						
					}else
						controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
				} 
			}
		});
		
		setVisible(true);
	}
}
