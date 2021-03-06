package Frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;
import Oggetti.DAO.PresenzaDaoImpl;
import Oggetti.DAO.StudenteDaoImpl;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

public class GestisciPresenzeFrame extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private JTable table;
	private GeneralPanelGrande panel;
	private String corsoSelected;
	private List<String>[] studentiCfEPresenza;
	private int contatore = 0;
	private int studenteContatore = 0;
	private int lezioneId;

	private int u = 0;
	private List<String>[] listaDate;
	private String tmpDate = null;
	
	public GestisciPresenzeFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestisciPresenzeFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 640);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanelGrande();
		panel.menuBar.setBounds(0,0,906,22);
		panel.labelHourDate.setLocation(641, 548);
		panel.setBounds(0, 0, 906, 601);
		getContentPane().add(panel);

		controller = new Controller();
		JPanel primoStepPanel = new JPanel();
		primoStepPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Primo Step"));
		primoStepPanel.setBackground(Color.white);
		primoStepPanel.setBounds(10, 110, 200, 427);
		panel.add(primoStepPanel);
		primoStepPanel.setLayout(null);
		
		DefaultListModel model = new DefaultListModel();
		JList listCorsi = new JList(model);
		listCorsi.setBounds(10, 25, 181, 343);
		primoStepPanel.add(listCorsi);
		listCorsi.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Corsi"));
		
		JButton stepButton = new JButton("Step 2");
		stepButton.setBounds(55, 379, 95, 20);
		primoStepPanel.add(stepButton);
		stepButton.setForeground(Color.RED);
		stepButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		stepButton.setBorder(new RoundBorderBotton(10));
		stepButton.setBackground(Color.WHITE);
		
		JPanel secondoStepPanel = new JPanel();
		secondoStepPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Secondo Step"));
		secondoStepPanel.setBackground(Color.white);
		secondoStepPanel.setBounds(220, 123, 442, 414);
		panel.add(secondoStepPanel);
		secondoStepPanel.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		dateChooser.setEnabled(false);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd");
		Date dataAttuale = new Date();
		String data = formatDate.format(dataAttuale);
		Date actualDate;
		
//		try {
//			
//			actualDate = formatDate.parse(data);
//			dateChooser.setMaxSelectableDate(actualDate);
//			
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		dateChooser.setBounds(31, 40, 178, 20);
		secondoStepPanel.add(dateChooser);
		
		JButton btnCercaLezione = new JButton("Cerca Lezione");
		btnCercaLezione.setEnabled(false);
		btnCercaLezione.setForeground(Color.RED);
		btnCercaLezione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCercaLezione.setBorder(new RoundBorderBotton(10));
		btnCercaLezione.setBackground(Color.WHITE);
		btnCercaLezione.setBounds(233, 40, 95, 20);
		secondoStepPanel.add(btnCercaLezione);
		
		DefaultTableModel modelStudenti = new DefaultTableModel(new Object[][] {},new String[] {"Nome", "Cognome", "Cf"});
		table = new JTable();
		table.setModel(modelStudenti);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.setBounds(108, 474, 1, 1);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 71, 422, 272);
		secondoStepPanel.add(scrollPane);
		
		JButton buttonPresente = new JButton("Presente");
		buttonPresente.setEnabled(false);
		buttonPresente.setForeground(Color.RED);
		buttonPresente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonPresente.setBorder(new RoundBorderBotton(10));
		buttonPresente.setBackground(Color.WHITE);
		buttonPresente.setBounds(71, 354, 114, 32);
		secondoStepPanel.add(buttonPresente);
		
		JButton buttonAssente = new JButton("Assente");
		buttonAssente.setEnabled(false);
		buttonAssente.setForeground(Color.RED);
		buttonAssente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonAssente.setBorder(new RoundBorderBotton(10));
		buttonAssente.setBackground(Color.WHITE);
		buttonAssente.setBounds(223, 354, 114, 32);
		secondoStepPanel.add(buttonAssente);
		
		JLabel dataLabel = new JLabel("Data");
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setBounds(31, 25, 47, 14);
		secondoStepPanel.add(dataLabel);
		
		JLabel corsoTrovatoLabel = new JLabel("Corso");
		corsoTrovatoLabel.setFont(new Font("Impact", Font.PLAIN, 13));
		corsoTrovatoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		corsoTrovatoLabel.setBounds(223, 11, 142, 18);
		secondoStepPanel.add(corsoTrovatoLabel);
		
		DefaultListModel modelListLezioni = new DefaultListModel();
		JList listLezioni = new JList(modelListLezioni);
		listLezioni.setEnabled(false);
		JScrollPane scrollPaneLezioni = new JScrollPane(listLezioni);
		scrollPaneLezioni.setBackground(Color.white);
		scrollPaneLezioni.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Lezioni da gestire"));
		scrollPaneLezioni.setBounds(672, 33, 224, 504);
		panel.add(scrollPaneLezioni);
		
		JButton buttonReset = new JButton("CambiaCorso");
		buttonReset.setForeground(Color.RED);
		buttonReset.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonReset.setBorder(new RoundBorderBotton(10));
		buttonReset.setBackground(Color.WHITE);
		buttonReset.setBounds(160, 548, 170, 20);
		panel.add(buttonReset);
		

		model.addAll(controller.getConnectionDao().getCorsoDao().getNomiCorsi(controller.getConnectionDao().getConnection()));
		
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(listCorsi.getSelectedValue() != null) {
					
					corsoSelected = listCorsi.getSelectedValue().toString();
					
					primoStepPanel.setEnabled(false);
					listCorsi.setEnabled(false);
					stepButton.setEnabled(false);
					
					
					int corsoId = controller.getConnectionDao().getCorsoDao().trovaCorsoId(controller.getConnectionDao().getConnection(), corsoSelected);
					
					
//					modelListLezioni.addAll(lezioneDao.getDateLezioniDaGestire(connectionDao.getConnection(), corsoId));
					
					listaDate = controller.getConnectionDao().getLezioneDao().getDateLezioniDaGestireELezione(controller.getConnectionDao().getConnection(), corsoId);
					int i = 0;
					
					while(i < Arrays.asList(listaDate).size()) {
						
						modelListLezioni.addElement(listaDate[i].get(0).toString());
						
						i++;
					}
					corsoTrovatoLabel.setText(listCorsi.getSelectedValue().toString());
					
					dateChooser.setEnabled(true);
					btnCercaLezione.setEnabled(true);
					buttonPresente.setEnabled(true);
					buttonAssente.setEnabled(true);
				
				}else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
				
			}
		});
		
		//USALO DOPO RIEMPIMENTO LISTA
		listCorsi.addMouseListener(new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList) evt.getSource();
		        if (evt.getClickCount() == 2) {

		            // Double-click detected
		            int index = listCorsi.locationToIndex(evt.getPoint());
		            //PRENDE OGGETTO CLICCATO DUE VOLTE
		            corsoTrovatoLabel.setText(listCorsi.getSelectedValue().toString());
					corsoSelected = listCorsi.getSelectedValue().toString();
					primoStepPanel.setEnabled(false);
					listCorsi.setEnabled(false);
					stepButton.setEnabled(false);
					
					
					int corsoId = controller.getConnectionDao().getCorsoDao().trovaCorsoId(controller.getConnectionDao().getConnection(), corsoSelected);

					
					listaDate = controller.getConnectionDao().getLezioneDao().getDateLezioniDaGestireELezione(controller.getConnectionDao().getConnection(), corsoId);
					int i = 0;
					
					while(i < Arrays.asList(listaDate).size()) {
						
						modelListLezioni.addElement(listaDate[i].get(0).toString());
						
						i++;
					}
					
					corsoTrovatoLabel.setText(listCorsi.getSelectedValue().toString());
					
					dateChooser.setEnabled(true);
					btnCercaLezione.setEnabled(true);
					buttonPresente.setEnabled(true);
					buttonAssente.setEnabled(true);
					
		        }
		    }
		    
		});

		
		btnCercaLezione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				
				if(dateChooser.getDate() != null) {
					
					tmpDate = sdf.format(dateChooser.getDate());
					//QUERY ATTRAVERSO DATA OVVERO CERCO L'ID DELLA LEZIONE TRAMITE DATA E PRENDO TUTTI GLI STUDENTI FACENDONE ANCHE UNA COUNT INOLTRE VEDI VALORE X E LA LEZIONE DIVENTA TRUE NON FALSE
					
					//DEPRECATA
//					lezioneId = lezioneDao.getLezioneIdByData(connectionDao.getConnection(), tmpDate);
					int i = 0;

					while(i < Arrays.asList(listaDate).size()) {
						
						
						if(listaDate[i].get(0).equals(tmpDate) && listaDate[i].get(7).equals("f")) {
							
							lezioneId = Integer.parseInt(listaDate[i].get(1));
							break;
							
						}
						
						i++;
					}
					
					if(lezioneId != 0) {
						
						
						List<String> studentiList = controller.getConnectionDao().getPresenzaDao().getStudentiCfByLezioneId(controller.getConnectionDao().getConnection(), lezioneId);
						
						Iterator iteratore = studentiList.listIterator();
						
						Vector<String>[][] studenti = new Vector[1][4];
						
						while(iteratore.hasNext()) {
							
							studenti = controller.getConnectionDao().getStudenteDao().ricercaStudenteByCf(controller.getConnectionDao().getConnection(), iteratore.next().toString());
							studenti[0][3].clear();
							
							modelStudenti.addRow(studenti[0]);
							contatore++;
							
						}
						
						dateChooser.setEnabled(false);
						btnCercaLezione.setEnabled(false);
						
						studentiCfEPresenza = new List[contatore];
						
					}else
						JOptionPane.showMessageDialog(null, "Non ci sono lezioni da gestire per questa data!", "Lezioni_ERROR", JOptionPane.ERROR_MESSAGE);

				}else
					//gestisci errore
					JOptionPane.showMessageDialog(null, "Non hai inserito date!", "Lezioni_ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		buttonPresente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				if(row != -1) {
					
					String cfStudente = table.getValueAt(row, 2).toString();
					
					studentiCfEPresenza[studenteContatore] = new LinkedList();
					studentiCfEPresenza[studenteContatore].add(cfStudente.substring(1, cfStudente.length()-1));
					studentiCfEPresenza[studenteContatore].add("Presente");
					
					studenteContatore++;
					
					
					if(studenteContatore == contatore) {
						
						while(u < contatore) {
							
							controller.getConnectionDao().getPresenzaDao().updatePresenzaStudente(controller.getConnectionDao().getConnection(), lezioneId, studentiCfEPresenza[u].get(0).toString(), studentiCfEPresenza[u].get(1).toString());
							
							u++;
						}
						controller.getConnectionDao().getLezioneDao().updateCheck(controller.getConnectionDao().getConnection(), lezioneId);
						
						int i = 0;
						
						while(i < Arrays.asList(listaDate).size()) {
							
							if(listaDate[i].get(0).equals(tmpDate) && listaDate[i].get(7).equals("f")) {
								
								
								listaDate[i].set(7, "t");
								break;
								
							}
							
							i++;
						}
						
						buttonPresente.setEnabled(false);
						buttonAssente.setEnabled(false);
						modelListLezioni.remove(row);
						listLezioni.revalidate();
						
						
						lezioneId = 0;
					}
					
					modelStudenti.removeRow(row);
					
				}else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
			}
		});
		
		buttonAssente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				int row = table.getSelectedRow();
				if(row != -1) {
					String cfStudente = table.getValueAt(row, 2).toString();
					
					studentiCfEPresenza[studenteContatore] = new LinkedList();
					studentiCfEPresenza[studenteContatore].add(cfStudente.substring(1, cfStudente.length()-1));
					studentiCfEPresenza[studenteContatore].add("Assente");
					
					studenteContatore++;
					
					if(studenteContatore == contatore) {

						while(u < contatore) {
							
							controller.getConnectionDao().getPresenzaDao().updatePresenzaStudente(controller.getConnectionDao().getConnection(), lezioneId, studentiCfEPresenza[u].get(0).toString(), studentiCfEPresenza[u].get(1).toString());
							
							u++;
						}
						
						controller.getConnectionDao().getLezioneDao().updateCheck(controller.getConnectionDao().getConnection(), lezioneId);
						
						int i = 0;
						
						while(i < Arrays.asList(listaDate).size()) {
							
							if(listaDate[i].get(0).equals(tmpDate) && listaDate[i].get(7).equals("f")) {
								
								listaDate[i].set(7, "t");
								break;
								
							}
							
							i++;
						}
						
						buttonPresente.setEnabled(false);
						buttonAssente.setEnabled(false);
						modelListLezioni.remove(row);
						listLezioni.revalidate();
						
						
						lezioneId = 0;
						
					}
					
					modelStudenti.removeRow(row);
					
				}else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
				
			}
		});
		
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				buttonPresente.setEnabled(false);
				buttonAssente.setEnabled(false);
				btnCercaLezione.setEnabled(false);
				dateChooser.setEnabled(false);
				
				primoStepPanel.setEnabled(true);
				listCorsi.setEnabled(true);
				stepButton.setEnabled(true);
				modelListLezioni.clear();
				listLezioni.revalidate();
				
				modelStudenti.setRowCount(0);
				table.revalidate();
				
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
		
		setVisible(true);
	}
}
