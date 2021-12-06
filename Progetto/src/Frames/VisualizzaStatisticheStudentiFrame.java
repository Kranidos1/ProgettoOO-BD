package Frames;

import java.awt.BorderLayout;

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
		controller = new Controller();
		JTable tableStats = new JTable();
		DefaultTableModel modelTable = new DefaultTableModel(new Object[][] {},new String[] {"Nome", "Cognome", "CF", "N.Lezioni", "%Presenze"}){
			public boolean isCellEditable(int row ,int column) {
				return false;
			}
		};
		
		tableStats.setModel(modelTable);
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
				
				controller.ricercaStudenti(list ,corsoLabel ,modelTable);
				
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
