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

import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.ConnectionDao;

import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class VisualizzaModificaTemiFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	private JTextField textFieldTema;
	private String tema;
	private String newTheme;
	private ConnectionDao connectionDao;
	
	public VisualizzaModificaTemiFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestioneCorsiFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		connectionDao = new ConnectionDao();
		connectionDao.setConnection(connectionDao.createConnection());
		
		controller = new Controller();
		
		DefaultListModel model = new DefaultListModel();
		JList listTemi = new JList(model);
		
		JScrollPane scrollPane = new JScrollPane(listTemi);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Temi"));
		scrollPane.setBounds(10, 112, 265, 340);
		panel.add(scrollPane);

		model.addAll(connectionDao.getAreaTematicaDao().getThemes(connectionDao.getConnection()));
		
		JButton buttonCerca = new JButton("Modifica!");

		buttonCerca.setForeground(Color.RED);
		buttonCerca.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonCerca.setBorder(new RoundBorderBotton(10));
		buttonCerca.setBackground(Color.WHITE);
		buttonCerca.setBounds(285, 184, 97, 25);
		panel.add(buttonCerca);
		
		textFieldTema = new JTextField();
		textFieldTema.setEditable(false);
		textFieldTema.setEnabled(false);
		textFieldTema.setBounds(298, 313, 193, 20);
		panel.add(textFieldTema);
		textFieldTema.setColumns(10);
		
		JLabel temaLabel = new JLabel("Tema");
		temaLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		temaLabel.setBounds(298, 293, 60, 20);
		panel.add(temaLabel);
		
		JButton buttonApplicaModifica = new JButton("Applica Modifica");
		buttonApplicaModifica.setEnabled(false);
		buttonApplicaModifica.setForeground(Color.RED);
		buttonApplicaModifica.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonApplicaModifica.setBorder(new RoundBorderBotton(10));
		buttonApplicaModifica.setBackground(Color.WHITE);
		buttonApplicaModifica.setBounds(322, 362, 156, 25);
		panel.add(buttonApplicaModifica);
		
		JButton buttonElimina = new JButton("Elimina");
		buttonElimina.setForeground(Color.RED);
		buttonElimina.setFont(new Font("Dialog", Font.PLAIN, 13));
		buttonElimina.setBorder(new RoundBorderBotton(10));
		buttonElimina.setBackground(Color.WHITE);
		buttonElimina.setBounds(285, 230, 97, 25);
		panel.add(buttonElimina);

		setVisible(true);
		
		
		buttonCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listTemi.getSelectedValue() != null) {
					
					tema = listTemi.getSelectedValue().toString();
					textFieldTema.setText(tema);
					textFieldTema.setEnabled(true);
					textFieldTema.setEditable(true);
					buttonApplicaModifica.setEnabled(true);
					buttonCerca.setEnabled(false);
					buttonElimina.setEnabled(false);
					
				}else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
				
			}
		});
		
		buttonElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listTemi.getSelectedValue() != null) {
					
					tema = listTemi.getSelectedValue().toString();
					connectionDao.getAreaTematicaDao().delete(connectionDao.getConnection(), tema);
					model.clear();
					model.addAll(connectionDao.getAreaTematicaDao().getThemes(connectionDao.getConnection()));
					
				}else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
				
			}
		});
		
		buttonApplicaModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Viene applicata la modifica
				if(!textFieldTema.getText().isEmpty()) {
					
					connectionDao.getAreaTematicaDao().update(connectionDao.getConnection(), tema, textFieldTema.getText().toString());
					model.clear();
					model.addAll(connectionDao.getAreaTematicaDao().getThemes(connectionDao.getConnection()));
					
					buttonApplicaModifica.setEnabled(false);
					textFieldTema.setText("");
					textFieldTema.setEditable(false);
					buttonCerca.setEnabled(true);
					buttonElimina.setEnabled(true);
					
				}else
					JOptionPane.showMessageDialog(null, "Empty Field Not Possible", "ERROR", JOptionPane.ERROR_MESSAGE);
				
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
	}
}
