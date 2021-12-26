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

import Oggetti.Corso;
import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.CorsoETemaDaoImpl;
import Oggetti.DAO.IscizioneDao;
import Oggetti.DAO.IscrizioneDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;
import Oggetti.DAO.PresenzaDaoImpl;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
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
		String[] columns = {"NomeCorso","N.Lezioni","N.Studenti","MediaPrs","MinimoPrs","MassimoPrs","Finito"
				};
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
		
		
		keyField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getSource() == keyField){
					
					char p = e.getKeyChar();
					
					if(controller.controlloField(p) == 1) {
						
						keyField.setText("");
						
					}	
	
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
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
		
		
		DefaultListModel model = new DefaultListModel();
		JList lista = new JList(model);
		lista.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(lista);
		scrollPane.setBounds(77, 113, 224, 91);
		panel.add(scrollPane);
		
		LinkedList<String> temi =  new LinkedList<String>();
		temi = controller.getConnectionDao().getAreaTematicaDao().getThemes(controller.getConnectionDao().getConnection());
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
				
				if(checkK == 0 && checkCat == 1) {
					
					//dainseriresololaricerca
					String categoria = new String();
					categoria = lista.getSelectedValue().toString();
					//TROVA GLI ID TRAMITE TEMA
					LinkedList<String> listaIdCorsi = controller.getConnectionDao().getCorsoTemaDao().ricercaCorsoByTheme(controller.getConnectionDao().getConnection() , categoria);
					//TROVA CORSI TRAMITE ID
					LinkedList<String> nomiCorsi = controller.getConnectionDao().getCorsoDao().getNomiById(controller.getConnectionDao().getConnection(), listaIdCorsi);
					
					//QUI AVVIENE AGGIUNTA ITERATIVA CON ANNESSI VALORI
					
				Vector[][] vettoreCorsi = new Vector[nomiCorsi.size()][7];
				int i = 0;
				
				while(i < nomiCorsi.size()) {
					
					vettoreCorsi[i][0] = new Vector();
					vettoreCorsi[i][0].add(nomiCorsi.get(i));
					
					Corso corsoCompleto = new Corso();
					corsoCompleto = controller.getConnectionDao().getCorsoDao().getCorso(controller.getConnectionDao().getConnection(), nomiCorsi.get(i));		
					
					vettoreCorsi[i][1] = new Vector();
					vettoreCorsi[i][2] = new Vector();
					vettoreCorsi[i][3] = new Vector();
					vettoreCorsi[i][4] = new Vector();
					vettoreCorsi[i][5] = new Vector();
					vettoreCorsi[i][6] = new Vector();
					
					int nLezioni = controller.getConnectionDao().getLezioneDao().countLezioni(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
					vettoreCorsi[i][1].add(nLezioni);
					int nStudenti = controller.getConnectionDao().getIscrizioneDao().countStudentiIscritti(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
					vettoreCorsi[i][2].add(nStudenti);
					
					List<Integer> listaIdLezioni = controller.getConnectionDao().getLezioneDao().getLezioniByCorsoId(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
					Integer max;
					Integer min;
					List<Integer> numPresenti = new LinkedList();
					
					if(nLezioni != 0) {
						
						if(nStudenti != 0) {
							
							int o = 0;
							
							int presenti = 0;
							while(o < listaIdLezioni.size()) {
								
								
								presenti += controller.getConnectionDao().getPresenzaDao().countPresenti(controller.getConnectionDao().getConnection(), listaIdLezioni.get(o));
								numPresenti.add(controller.getConnectionDao().getLezioneDao().countPresenti(controller.getConnectionDao().getConnection(), listaIdLezioni.get(o)));
								o++;
							}
							
							if(presenti != 0) {
								
								DecimalFormat df = new DecimalFormat();
								df.setMaximumFractionDigits(2);
								
								float presentiFloat = Float.parseFloat(Integer.toString(presenti));
								float nStudentiFloat = Float.parseFloat(Integer.toString(nStudenti));
								float nLezioniFloat = Float.parseFloat(Integer.toString(nLezioni));
								
								float media = (presentiFloat / nStudentiFloat) / nLezioniFloat;
								
								vettoreCorsi[i][3].add(df.format(media));
								System.out.println(vettoreCorsi[i][0]);
								max = Collections.max(numPresenti);
								min = Collections.min(numPresenti);
								vettoreCorsi[i][4].add(min);
								vettoreCorsi[i][5].add(max);
								
							}else {
								vettoreCorsi[i][3].add("X");
								vettoreCorsi[i][4].add("X");
								vettoreCorsi[i][5].add("X");
							}
							
						}else {
							vettoreCorsi[i][3].add("X");
							vettoreCorsi[i][4].add("X");
							vettoreCorsi[i][5].add("X");
						}
					}else {
						vettoreCorsi[i][3].add("X");
						vettoreCorsi[i][4].add("X");
						vettoreCorsi[i][5].add("X");
					}
					

					vettoreCorsi[i][6].add(corsoCompleto.getCheck());
					
					modelTable.addRow(vettoreCorsi[i]);
					
					i++;
					
				}
				
				
				
				
				}
				
				if(checkK == 1 && checkCat == 0) {
					
					//QUI AVVIENE AGGIUNTA ITERATIVA CON ANNESSI VALORI l'aggiunta avviene tramite vector,questo e' solo un esempio
					LinkedList<String> listaCorsi = controller.getConnectionDao().getCorsoDao().getNomiCorsiByKey(controller.getConnectionDao().getConnection(), keyField.getText());
					if(listaCorsi != null) {
						Vector[][] vettoreCorsi = new Vector[listaCorsi.size()][7];
						
						int i = 0;
						
						while(i < listaCorsi.size()) {
							
							vettoreCorsi[i][0] = new Vector();
							vettoreCorsi[i][0].add(listaCorsi.get(i));
							
							Corso corsoCompleto = new Corso();
							corsoCompleto = controller.getConnectionDao().getCorsoDao().getCorso(controller.getConnectionDao().getConnection(), listaCorsi.get(i));
							
							vettoreCorsi[i][1] = new Vector();
							vettoreCorsi[i][2] = new Vector();
							vettoreCorsi[i][3] = new Vector();
							vettoreCorsi[i][4] = new Vector();
							vettoreCorsi[i][5] = new Vector();
							vettoreCorsi[i][6] = new Vector();
							
							int nLezioni = controller.getConnectionDao().getLezioneDao().countLezioni(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
							vettoreCorsi[i][1].add(nLezioni);
							int nStudenti = controller.getConnectionDao().getIscrizioneDao().countStudentiIscritti(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
							vettoreCorsi[i][2].add(nStudenti);
							
							List<Integer> listaIdLezioni = controller.getConnectionDao().getLezioneDao().getLezioniByCorsoId(controller.getConnectionDao().getConnection(),corsoCompleto.getCorsoId());
							Integer max = 0;
							Integer min = 0;
							List<Integer> numPresenti = null;
							
							if(nLezioni != 0) {
								
								if(nStudenti != 0) {
									
									int o = 0;
									
									int presenti = 0;
									while(o < listaIdLezioni.size()) {
										
										
										presenti += controller.getConnectionDao().getPresenzaDao().countPresenti(controller.getConnectionDao().getConnection(), listaIdLezioni.get(o));
										
										if(numPresenti != null) {
											numPresenti.add(controller.getConnectionDao().getLezioneDao().countPresenti(controller.getConnectionDao().getConnection(), listaIdLezioni.get(o)));
										}
										o++;
									}
									
									if(presenti != 0) {
										
										DecimalFormat df = new DecimalFormat();
										df.setMaximumFractionDigits(2);
										
										float presentiFloat = Float.parseFloat(Integer.toString(presenti));
										float nStudentiFloat = Float.parseFloat(Integer.toString(nStudenti));
										float nLezioniFloat = Float.parseFloat(Integer.toString(nLezioni));
										
										float media = (presentiFloat / nStudentiFloat) / nLezioniFloat;
										
											
											vettoreCorsi[i][3].add(df.format(media));
											
										
										if(numPresenti != null) {
											max = Collections.max(numPresenti);
											min = Collections.min(numPresenti);
										}
										
										vettoreCorsi[i][4].add(min);
										vettoreCorsi[i][5].add(max);
										
									}else {
										vettoreCorsi[i][3].add("X");
										vettoreCorsi[i][4].add("X");
										vettoreCorsi[i][5].add("X");
									}
									
								}else {
									vettoreCorsi[i][3].add("X");
									vettoreCorsi[i][4].add("X");
									vettoreCorsi[i][5].add("X");
								}
							}else {
								vettoreCorsi[i][3].add("X");
								vettoreCorsi[i][4].add("X");
								vettoreCorsi[i][5].add("X");
							}
							
							vettoreCorsi[i][6].add(corsoCompleto.getCheck());
							
							modelTable.addRow(vettoreCorsi[i]);
							
							i++;
							
						}
						
					}
					
				}
				
				if(checkK == 1 && checkCat == 1) {
					
					LinkedList<String> listaCorsi = controller.getConnectionDao().getCorsoDao().getCorsiTramiteKeyETema(controller.getConnectionDao().getConnection(), keyField.getText(), lista.getSelectedValue().toString());
					if(listaCorsi != null) {
						Vector[][] vettoreCorsi = new Vector[listaCorsi.size()][7];
						
						int i = 0;
						
						while(i < listaCorsi.size()) {
							
							vettoreCorsi[i][0] = new Vector();
							vettoreCorsi[i][0].add(listaCorsi.get(i));
							
							
							Corso corsoCompleto = new Corso();
							corsoCompleto = controller.getConnectionDao().getCorsoDao().getCorso(controller.getConnectionDao().getConnection(), listaCorsi.get(i));	
							
							vettoreCorsi[i][1] = new Vector();
							vettoreCorsi[i][2] = new Vector();
							vettoreCorsi[i][3] = new Vector();
							vettoreCorsi[i][4] = new Vector();
							vettoreCorsi[i][5] = new Vector();
							vettoreCorsi[i][6] = new Vector();
							
							int nLezioni = controller.getConnectionDao().getLezioneDao().countLezioni(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
							vettoreCorsi[i][1].add(nLezioni);
							int nStudenti = controller.getConnectionDao().getIscrizioneDao().countStudentiIscritti(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
							vettoreCorsi[i][2].add(nStudenti);
							
							List<Integer> listaIdLezioni = controller.getConnectionDao().getLezioneDao().getLezioniByCorsoId(controller.getConnectionDao().getConnection(), corsoCompleto.getCorsoId());
							Integer max;
							Integer min;
							List<Integer> numPresenti = null;
							
							if(nLezioni != 0) {
								
								if(nStudenti != 0) {
									
									int o = 0;
									
									int presenti = 0;
									numPresenti = new LinkedList();
									while(o < listaIdLezioni.size()) {
										
										
										presenti += controller.getConnectionDao().getPresenzaDao().countPresenti(controller.getConnectionDao().getConnection(), listaIdLezioni.get(o));
											
											numPresenti.add(controller.getConnectionDao().getLezioneDao().countPresenti(controller.getConnectionDao().getConnection(), listaIdLezioni.get(o)));
										
										
											o++;
										
									}
									
									if(presenti != 0) {
										
										DecimalFormat df = new DecimalFormat();
										df.setMaximumFractionDigits(2);
										
										float presentiFloat = Float.parseFloat(Integer.toString(presenti));
										float nStudentiFloat = Float.parseFloat(Integer.toString(nStudenti));
										float nLezioniFloat = Float.parseFloat(Integer.toString(nLezioni));
										
										float media = (presentiFloat / nStudentiFloat) / nLezioniFloat;
										
										vettoreCorsi[i][3].add(df.format(media));
										max = Collections.max(numPresenti);
										min = Collections.min(numPresenti);
										vettoreCorsi[i][4].add(min);
										vettoreCorsi[i][5].add(max);
										
									}else {

										vettoreCorsi[i][3].add("X");
										vettoreCorsi[i][4].add("X");
										vettoreCorsi[i][5].add("X");
									}
									
								}else {

									vettoreCorsi[i][3].add("X");
									vettoreCorsi[i][4].add("X");
									vettoreCorsi[i][5].add("X");
								}
							}else {

								vettoreCorsi[i][3].add("X");
								vettoreCorsi[i][4].add("X");
								vettoreCorsi[i][5].add("X");
							}

							vettoreCorsi[i][6].add(corsoCompleto.getCheck());
							
							modelTable.addRow(vettoreCorsi[i]);
							
							i++;
							
						}
						
						
					}
					
				}
			
			}
		});
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {

            	controller.closeConnection();
            	
            }
            
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	controller.closeConnection();
            	
            }
        });
		
}
	
	
}
