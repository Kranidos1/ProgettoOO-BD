package Frames;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet.ColorAttribute;

import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.CorsoETemaDaoImpl;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class StatisticheCorsoFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanelGrande panel;
	private JTable table;
	private JTextField keyField;
	private JLabel lblCategory;
	private int checkCat = 0;
	private int checkK = 0;
	private Controller controller;
	
	public StatisticheCorsoFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(StatisticheCorsoFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 640);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanelGrande();
		controller = new Controller();
		String[] columns = {"NomeCorso","N.Studenti","MediaPrs","MinimoPrs","MassimoPrs","RiempimentoM"};
		getContentPane().add(panel);
		
		DefaultTableModel modelTable = new DefaultTableModel(new Object[][] {},columns){
			public boolean isCellEditable(int row ,int column) {
				return false;
			}
		};
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(modelTable);
		
		
		JScrollPane scrollPaneTable = new JScrollPane(table);
		scrollPaneTable.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		scrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTable.setBounds(10, 260, 603, 277);
		scrollPaneTable.setBackground(Color.WHITE);
		panel.add(scrollPaneTable);
		
		keyField = new JTextField();
		keyField.setEnabled(false);
		keyField.setBounds(77, 215, 224, 20);
		panel.add(keyField);
		keyField.setColumns(10);
		
		JLabel keyLabel = new JLabel("Key");
		keyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keyLabel.setBounds(10, 217, 47, 17);
		panel.add(keyLabel);
		
		lblCategory = new JLabel("Category");
		lblCategory.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCategory.setBounds(10, 116, 57, 20);
		panel.add(lblCategory);
		
		JButton buttonRicerca = new JButton("Ricerca");
		buttonRicerca.setForeground(Color.RED);
		buttonRicerca.setBorder(new RoundBorderBotton(10));
		buttonRicerca.setBackground(Color.WHITE);
		buttonRicerca.setBounds(504, 155, 75, 41);
		panel.add(buttonRicerca);
		
		JCheckBox checkCategory = new JCheckBox("");
		checkCategory.setBackground(Color.white);
		checkCategory.setBounds(307, 164, 21, 17);
		panel.add(checkCategory);
		
		JCheckBox checkKey = new JCheckBox("");
		checkKey.setBackground(Color.white);
		checkKey.setBounds(307, 215, 21, 17);
		panel.add(checkKey);
		
		AreaTematicaDaoImpl temaDao = new AreaTematicaDaoImpl();
		
		
		DefaultListModel model = new DefaultListModel();
		JList lista = new JList(model);
		lista.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(77, 113, 224, 91);
		panel.add(scrollPane);
		
		LinkedList<String> temi =  new LinkedList<String>();
		temi = temaDao.getThemes(controller.getConnection());
		model.addAll(temi);
		
		
		setVisible(true);
		
		checkCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkCat = controller.isEnbl(null, checkCat ,null ,lista);
				
			}
			
		});
		
		checkKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkK = controller.isEnbl(keyField, checkK ,null ,null);
				
			}
			
		});
		
		
		//funziona tutto bisogna solo aggiungere valori calcolati.
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelTable.setRowCount(0);
				table.revalidate();
				CorsoETemaDaoImpl corsoTema = new CorsoETemaDaoImpl();
				CorsoDaoImpl corso = new CorsoDaoImpl();
				
				if(checkK == 0 && checkCat == 1) {
					
					//dainseriresololaricerca
					String categoria = new String();
					categoria = lista.getSelectedValue().toString();
					//TROVA GLI ID TRAMITE TEMA
					LinkedList<String> listaIdCorsi = corsoTema.ricercaCorsoByTheme(controller.getConnection() , categoria);
					//TROVA CORSI TRAMITE ID
					LinkedList<String> nomiCorsi = corso.getNomiById(controller.getConnection(), listaIdCorsi);
					
					//QUI AVVIENE AGGIUNTA ITERATIVA CON ANNESSI VALORI
					
				Vector[][] vettoreCorsi = new Vector[nomiCorsi.size()][6];
				int i = 0;
				
				while(i < nomiCorsi.size()) {
					
					vettoreCorsi[i][0] = new Vector();
					vettoreCorsi[i][0].add(nomiCorsi.get(i));
					vettoreCorsi[i][1] = new Vector();
					vettoreCorsi[i][2] = new Vector();
					vettoreCorsi[i][3] = new Vector();
					vettoreCorsi[i][4] = new Vector();
					vettoreCorsi[i][5] = new Vector();
					
					vettoreCorsi[i][1].add("N.Studenti");
					vettoreCorsi[i][2].add("Media");
					vettoreCorsi[i][3].add("Minimo");
					vettoreCorsi[i][4].add("Massimo");
					vettoreCorsi[i][5].add("riempimento");
					
					modelTable.addRow(vettoreCorsi[i]);
					
					i++;
					
				}
				
				
				
				
				}
				
				if(checkK == 1 && checkCat == 0) {
					
					//QUI AVVIENE AGGIUNTA ITERATIVA CON ANNESSI VALORI l'aggiunta avviene tramite vector,questo e' solo un esempio
					LinkedList<String> listaCorsi = corso.getNomiCorsiByKey(controller.getConnection(), keyField.getText());
					Vector[][] vettoreCorsi = new Vector[listaCorsi.size()][6];
					int i = 0;
					
					while(i < listaCorsi.size()) {
						
						vettoreCorsi[i][0] = new Vector();
						vettoreCorsi[i][0].add(listaCorsi.get(i));
						vettoreCorsi[i][1] = new Vector();
						vettoreCorsi[i][2] = new Vector();
						vettoreCorsi[i][3] = new Vector();
						vettoreCorsi[i][4] = new Vector();
						vettoreCorsi[i][5] = new Vector();
						
						vettoreCorsi[i][1].add("N.Studenti");
						vettoreCorsi[i][2].add("Media");
						vettoreCorsi[i][3].add("Minimo");
						vettoreCorsi[i][4].add("Massimo");
						vettoreCorsi[i][5].add("riempimento");
						
						modelTable.addRow(vettoreCorsi[i]);
						
						i++;
						
					}
					
				}
				
				if(checkK == 1 && checkCat == 1) {
					
					LinkedList<String> listaCorsi = corso.getCorsiTramiteKeyETema(controller.getConnection(), keyField.getText(), lista.getSelectedValue().toString());
					Vector[][] vettoreCorsi = new Vector[listaCorsi.size()][6];
					int i = 0;
					
					while(i < listaCorsi.size()) {
						
						vettoreCorsi[i][0] = new Vector();
						vettoreCorsi[i][0].add(listaCorsi.get(i));
						vettoreCorsi[i][1] = new Vector();
						vettoreCorsi[i][2] = new Vector();
						vettoreCorsi[i][3] = new Vector();
						vettoreCorsi[i][4] = new Vector();
						vettoreCorsi[i][5] = new Vector();
						
						vettoreCorsi[i][1].add("N.Studenti");
						vettoreCorsi[i][2].add("Media");
						vettoreCorsi[i][3].add("Minimo");
						vettoreCorsi[i][4].add("Massimo");
						vettoreCorsi[i][5].add("riempimento");
						
						modelTable.addRow(vettoreCorsi[i]);
						
						i++;
						
					}
					
				}
			
			}
		});
		
}
}
