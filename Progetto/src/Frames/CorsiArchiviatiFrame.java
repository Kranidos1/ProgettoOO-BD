package Frames;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.IscrizioneDaoImpl;

import javax.swing.JScrollPane;
import java.awt.*;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class CorsiArchiviatiFrame extends JFrame {

	private ConnectionDao connectionDao;
	private JPanel contentPane;
	private GeneralPanelGrande panel;
	private Controller controller;
	private JTable table;
	private JFileChooser fileChooser;
	private String nomeCorso;
	private Vector[] vettoreStudenti;
	
	public CorsiArchiviatiFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestisciPresenzeFrame.class.getResource("/imgs/lastin.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 640);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		connectionDao = new ConnectionDao();
		controller = new Controller();

		
		panel = new GeneralPanelGrande();
		panel.menuBar.setBounds(0, 0, 906, 22);
		panel.labelHourDate.setLocation(641, 548);
		panel.setBounds(0, 0, 906, 601);
		getContentPane().add(panel);
		
		DefaultListModel modelList = new DefaultListModel();
		JList listCorsi = new JList(modelList);
		JScrollPane scrollPane = new JScrollPane(listCorsi);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Corsi Archiviati"));
		scrollPane.setBounds(10, 121, 197, 211);
		panel.add(scrollPane);
		
		DefaultTableModel modelTable = new DefaultTableModel(new Object[][] {},new String[] {"Nome","Cognome","Cf","Promosso","%Presenze"}){
			public boolean isCellEditable(int row ,int column) {
				return false;
			}
		};
		table = new JTable(modelTable) {
			
			@Override
			public Component prepareRenderer(TableCellRenderer r,int row , int column) {
				
				Component c = super.prepareRenderer(r, row, column);
				
				if(modelTable.getValueAt(row, 3).toString().equals("Si")) {
					
					c.setBackground(Color.green);
				}else
					c.setBackground(Color.red);
				
				return c;
				
			}
			
		};
		
		JScrollPane scrollPaneStats = new JScrollPane(table);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		scrollPaneStats.setBounds(217, 156, 617, 280);
		panel.add(scrollPaneStats);
		
		JButton ricercaButton = new JButton("Ottieni Statistiche");
		ricercaButton.setForeground(Color.RED);
		ricercaButton.setBorder(new RoundBorderBotton(10));
		ricercaButton.setBackground(Color.WHITE);
		ricercaButton.setBounds(46, 343, 132, 29);
		panel.add(ricercaButton);
		
		JButton btnEsporta = new JButton("Esporta");
		btnEsporta.setForeground(Color.RED);
		btnEsporta.setBorder(new RoundBorderBotton(10));
		btnEsporta.setBackground(Color.WHITE);
		btnEsporta.setBounds(458, 447, 132, 29);
		panel.add(btnEsporta);
		//AGGIUNGO CORSI ARCHIVIATI ALLA LISTA
		modelList.addAll(connectionDao.getCorsoDao().getNomeCorsiArchiviati(connectionDao.getConnection()));
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		ricercaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
				table.revalidate();
				
				if(listCorsi.getSelectedValue() != null) {
					
					nomeCorso = listCorsi.getSelectedValue().toString();
					int corsoId = connectionDao.getCorsoDao().trovaCorsoId(connectionDao.getConnection(), nomeCorso);
					LinkedList[] studentiList = (LinkedList[]) connectionDao.getIscrizioneDao().getStudentiByCorsoId(connectionDao.getConnection(), corsoId);
					
					vettoreStudenti = new Vector[Arrays.asList(studentiList).size()];
					
					int i = 0;
					
					while(i < Arrays.asList(studentiList).size()) {
						
						vettoreStudenti[i] = new Vector();
						vettoreStudenti[i].add(studentiList[i].get(0).toString());
						vettoreStudenti[i].add(studentiList[i].get(1).toString());
						vettoreStudenti[i].add(studentiList[i].get(2).toString());
						
						String percentualePresenza;
						String cfFormatted = studentiList[i].get(2).toString().substring(1, studentiList[i].get(2).toString().length()-1);
						String promozione = connectionDao.getIscrizioneDao().getPromozioneStudente(connectionDao.getConnection(), cfFormatted, corsoId);
						
						if(promozione.equals("Promosso")) {
							vettoreStudenti[i].add("Si");
						}else {
							vettoreStudenti[i].add("No");
						}
						
						int nLezioni = connectionDao.getLezioneDao().countLezioni(connectionDao.getConnection(), corsoId);
						
						String presenza;
						
						List<Integer> lezioniIdList = connectionDao.getLezioneDao().getLezioniByCorsoId(connectionDao.getConnection(),  corsoId);
						
						int p = 0;
						int count = 0;
						
						while(p < lezioniIdList.size()) {
							

							presenza = connectionDao.getPresenzaDao().checkPresenzaStudente(connectionDao.getConnection(), cfFormatted, lezioniIdList.get(p));
							
							if(presenza != null) {
								
								if(presenza.equals("Presente")) {
									
									count++;
									
								}
								
							}
							
							p++;
						}
						
						
						if(nLezioni != 0) {

							if(count != 0) {
								
								
								int percentuale = (count * 100) / nLezioni;
								percentualePresenza = Integer.toString(percentuale);
								vettoreStudenti[i].add(percentualePresenza + "%");
								
							}else
								vettoreStudenti[i].add("X");
						}else
							vettoreStudenti[i].add("X");
							
						
							
						
						model.addRow(vettoreStudenti[i]);
						
					
						
						i++;
					}
					
					
				}
				
			}
		});
		
		btnEsporta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.esporta(fileChooser, nomeCorso, vettoreStudenti, model);
				
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
