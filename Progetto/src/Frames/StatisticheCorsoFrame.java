package Frames;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatisticheCorsoFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private JTable table;
	private JTextField keyField;
	private JTextField categoryField;
	private JLabel lblCategory;
	private int checkCat = 0;
	private int checkK = 0;
	private Controller controller;
	
	public StatisticheCorsoFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\matti\\Desktop\\lastin.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		controller = new Controller();
		String[] columns = {"NomeCorso","N.Studenti","MediaPrs","MinimoPrs","MassimoPrs","RiempimentoM"};
		getContentPane().add(panel);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {{"Pippo","Baudo","Tiririr","Cacca","cacca","cacca"}
			},
			columns
		));
		JScrollPane scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		scrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTable.setBounds(10, 175, 481, 277);
		scrollPaneTable.setBackground(Color.WHITE);
		panel.add(scrollPaneTable);
		
		keyField = new JTextField();
		keyField.setEnabled(false);
		keyField.setBounds(67, 144, 119, 20);
		panel.add(keyField);
		keyField.setColumns(10);
		
		categoryField = new JTextField();
		categoryField.setEnabled(false);
		categoryField.setColumns(10);
		categoryField.setBounds(67, 113, 119, 20);
		panel.add(categoryField);
		
		JLabel keyLabel = new JLabel("Key");
		keyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keyLabel.setBounds(10, 147, 47, 17);
		panel.add(keyLabel);
		
		lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setBounds(0, 113, 57, 20);
		panel.add(lblCategory);
		
		JButton buttonRicerca = new JButton("Ricerca");
		buttonRicerca.setForeground(Color.RED);
		buttonRicerca.setBorder(new RoundBorderBotton(10));
		buttonRicerca.setBackground(Color.WHITE);
		buttonRicerca.setBounds(236, 125, 75, 26);
		panel.add(buttonRicerca);
		
		JCheckBox checkCategory = new JCheckBox("");
		checkCategory.setBackground(Color.white);
		checkCategory.setBounds(192, 113, 21, 17);
		panel.add(checkCategory);
		
		JCheckBox checkKey = new JCheckBox("");
		checkKey.setBackground(Color.white);
		checkKey.setBounds(192, 143, 21, 17);
		panel.add(checkKey);
		
		setVisible(true);
		
		checkCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkCat = controller.isEnbl(categoryField, checkCat ,null);
				
			}
			
		});
		
		checkKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkK = controller.isEnbl(keyField, checkK ,null);
				
			}
			
		});
		
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//dainseriresololaricerca
				
			}
		});
		
}
}