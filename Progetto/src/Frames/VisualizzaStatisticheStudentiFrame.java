package Frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import Oggetti.AreaTematica;
import Oggetti.Corso;
import Oggetti.Lezione;
import Oggetti.Studente;
import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;

import Oggetti.DAO.CorsoETemaDaoImpl;
import Oggetti.DAO.IscrizioneDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;
import Oggetti.DAO.PresenzaDaoImpl;
import Oggetti.DAO.StudenteDaoImpl;
import java.awt.Component;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;

import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class VisualizzaStatisticheStudentiFrame extends JFrame {
	
	private ConnectionDao connectionDao;
	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	
	public VisualizzaStatisticheStudentiFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VisualizzaStatisticheStudentiFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanel();
		panel.setBounds(0, 0, 501, 516);
		getContentPane().add(panel);
		
		connectionDao = new ConnectionDao();
		connectionDao.setConnection(connectionDao.createConnection());
		
		controller = new Controller();
		DefaultTableModel modelTable = new DefaultTableModel(new Object[][] {},new String[] {"Nome", "Cognome", "CF", "N.Lezioni", "%Presenze"}){
			public boolean isCellEditable(int row ,int column) {
				return false;
			}
		};
		
		JTable tableStats = new JTable(modelTable) {
			
			@Override
			public Component prepareRenderer(TableCellRenderer r,int row , int column) {
				
				int value = 0;
				
				if(modelTable.getValueAt(row, 4).toString().length() == 2){
					
					value = 1;
					
				}
				
				if(modelTable.getValueAt(row, 4).toString().length() == 3){
					
					value = Integer.parseInt(modelTable.getValueAt(row, 4).toString().substring(0, 2));
					
				}
				
				if(modelTable.getValueAt(row, 4).toString().length() == 4){
					
					value = Integer.parseInt(modelTable.getValueAt(row, 4).toString().substring(0, 3));
					
				}
				
				
				Component c = super.prepareRenderer(r, row, column);
				
				if( value < 60) {
					
					c.setBackground(Color.RED);
					
				}else
					c.setBackground(Color.green);
				
				return c;
				
			}
		};
		
		tableStats.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableStats.getColumnModel().getColumn(3).setPreferredWidth(75);
		tableStats.getColumnModel().getColumn(4).setPreferredWidth(75);

		
		JScrollPane scrollPaneTable = new JScrollPane(tableStats);
		scrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTable.setBounds(0, 215, 501, 246);
		panel.add(scrollPaneTable);
		scrollPaneTable.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		
		JLabel corsoLabel = new JLabel("Corso");
		corsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		corsoLabel.setBounds(10, 121, 54, 20);
		panel.add(corsoLabel);
		
		JButton buttonRicerca = new JButton("Ricerca");
		buttonRicerca.setForeground(Color.RED);
		buttonRicerca.setBorder(new RoundBorderBotton(10));
		buttonRicerca.setBackground(Color.WHITE);
		buttonRicerca.setBounds(289, 154, 68, 20);
		panel.add(buttonRicerca);
		
		DefaultListModel modelList = new DefaultListModel();
		JList list = new JList(modelList);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		scrollPane.setBounds(74, 121, 180, 83);
		panel.add(scrollPane);
		

		modelList.addAll(connectionDao.getCorsoDao().getNomiCorsi(connectionDao.getConnection()));
		
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelTable.setRowCount(0);
				tableStats.revalidate();
				
				controller.ricercaStudenti(list ,corsoLabel ,modelTable ,tableStats);
				
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
