package Frames;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.IscrizioneDaoImpl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;

public class BocciaPromuoviFrame extends JFrame {
	
	private JPanel contentPane;
	private GeneralPanelGrande panel;
	private JTable table;
	private Controller controller;
	private List[] studentiEPromozione;
	private int numStudenti;
	private int pass;
	private String nomeCorso;

	public BocciaPromuoviFrame() {

		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestisciPresenzeFrame.class.getResource("/imgs/lastin.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 640);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		controller = new Controller();
		panel = new GeneralPanelGrande();
		panel.menuBar.setBounds(0, 0, 906, 22);
		panel.labelHourDate.setLocation(641, 548);
		panel.setBounds(0, 0, 906, 601);
		getContentPane().add(panel);

		pass = 0;
		
		DefaultListModel modelListCorsi = new DefaultListModel();
		JList listCorsi = new JList(modelListCorsi);
		JScrollPane scrollPaneCorsi = new JScrollPane(listCorsi);
		scrollPaneCorsi.setBackground(Color.white);
		scrollPaneCorsi.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Corsi Finiti"));
		scrollPaneCorsi.setBounds(10, 122, 244, 225);
		panel.add(scrollPaneCorsi);

		DefaultTableModel modelTable = new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Cognome", "Cf", "N.Lezioni Corso", "%Presenze" }) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(modelTable) {
			
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

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(264, 148, 632, 264);
		panel.add(scrollPane);

		JButton buttonVisualizzaStudenti = new JButton("Visualizza studenti");
		buttonVisualizzaStudenti.setForeground(Color.RED);
		buttonVisualizzaStudenti.setBorder(new RoundBorderBotton(10));
		buttonVisualizzaStudenti.setBackground(Color.WHITE);
		buttonVisualizzaStudenti.setBounds(64, 358, 123, 37);
		panel.add(buttonVisualizzaStudenti);

		JButton buttonPromuovi = new JButton("Promuovi");
		buttonPromuovi.setForeground(Color.RED);
		buttonPromuovi.setBorder(new RoundBorderBotton(10));
		buttonPromuovi.setBackground(Color.WHITE);
		buttonPromuovi.setBounds(385, 423, 123, 37);
		panel.add(buttonPromuovi);

		JButton buttonBoccia = new JButton("Boccia");
		buttonBoccia.setForeground(Color.RED);
		buttonBoccia.setBorder(new RoundBorderBotton(10));
		buttonBoccia.setBackground(Color.WHITE);
		buttonBoccia.setBounds(683, 423, 123, 37);
		panel.add(buttonBoccia);

		
		LinkedList corsiFiniti = controller.getConnectionDao().getCorsoDao().getNomeCorsiFiniti(controller.getConnectionDao().getConnection());
		int size = Arrays.asList(corsiFiniti).size();

		modelListCorsi.addAll(corsiFiniti);

		buttonVisualizzaStudenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (listCorsi.getSelectedValue() != null) {
					
					modelTable.setRowCount(0);
					table.revalidate();
					
					nomeCorso = listCorsi.getSelectedValue().toString();
					numStudenti = controller.ricercaStudenti(listCorsi, null, modelTable ,table);
					studentiEPromozione = new List[numStudenti];

				}

			}
		});

		

		buttonPromuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row != -1) {

					studentiEPromozione[pass] = new LinkedList();
					// nome
					studentiEPromozione[pass].add(table.getValueAt(row, 0));
					// cognome
					studentiEPromozione[pass].add(table.getValueAt(row, 1));
					// cf
					studentiEPromozione[pass].add(table.getValueAt(row, 2));
					// promozione
					studentiEPromozione[pass].add("Promosso");
					
					int presenzaPercentuale = Integer.parseInt(table.getValueAt(row, 4).toString());

					if(presenzaPercentuale < 60) {
						
						int input = JOptionPane.showConfirmDialog(null, "Sicuro di voler promuovere questo studente?" , "Pensaci bene", JOptionPane.OK_CANCEL_OPTION);
						
						if(true) {
							
							pass++;
							modelTable.removeRow(row);
							table.revalidate();

							if (pass == numStudenti) {

								int i = 0;
								while (i < numStudenti) {

									controller.getConnectionDao().getIscrizioneDao().updateStatoStudente(controller.getConnectionDao().getConnection(),
											studentiEPromozione[i].get(2).toString().substring(1,
													// prendo semplicemente la stringa del cf e levo le quadre.
													studentiEPromozione[i].get(2).toString().length() - 1),
											studentiEPromozione[i].get(3).toString());

									i++;
								}

								int corsoI = controller.getConnectionDao().getCorsoDao().trovaCorsoId(controller.getConnectionDao().getConnection(), nomeCorso);
								controller.getConnectionDao().getCorsoDao().updateCheckCorsoGestito(controller.getConnectionDao().getConnection(), corsoI);

								JOptionPane.showMessageDialog(null,
										"Gestiti tutti gli studenti del corso.Il corso,con un report,potrai vederlo nella sezione \"Corsi Archiviati\".",
										"Ok!", JOptionPane.INFORMATION_MESSAGE);

								setVisible(false);
								new FrameDiScelta();

							}
							
						}
						
					}else {
						
						int input = JOptionPane.showConfirmDialog(null, "Sicuro di voler promuovere questo studente?" , "Pensaci bene", JOptionPane.OK_CANCEL_OPTION);
						
						if(true) {
							
							pass++;
							modelTable.removeRow(row);
							table.revalidate();

							if (pass == numStudenti) {

								int i = 0;
								while (i < numStudenti) {

									controller.getConnectionDao().getIscrizioneDao().updateStatoStudente(controller.getConnectionDao().getConnection(),
											studentiEPromozione[i].get(2).toString().substring(1,
													// prendo semplicemente la stringa del cf e levo le quadre.
													studentiEPromozione[i].get(2).toString().length() - 1),
											studentiEPromozione[i].get(3).toString());

									i++;
								}

								int corsoI = controller.getConnectionDao().getCorsoDao().trovaCorsoId(controller.getConnectionDao().getConnection(), nomeCorso);
								controller.getConnectionDao().getCorsoDao().updateCheckCorsoGestito(controller.getConnectionDao().getConnection(), corsoI);

								JOptionPane.showMessageDialog(null,
										"Gestiti tutti gli studenti del corso.Il corso,con un report,potrai vederlo nella sezione \"Corsi Archiviati\".",
										"Ok!", JOptionPane.INFORMATION_MESSAGE);

								setVisible(false);
								new FrameDiScelta();

							}
							
						}
						
					}
				}

			}
		});

		buttonBoccia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();

				if (row != -1) {

					studentiEPromozione[pass] = new LinkedList();
					// nome
					studentiEPromozione[pass].add(table.getValueAt(row, 0));
					// cognome
					studentiEPromozione[pass].add(table.getValueAt(row, 1));
					// cf
					studentiEPromozione[pass].add(table.getValueAt(row, 2));
					// promozione
					studentiEPromozione[pass].add("Bocciato");

						
						pass++;
						modelTable.removeRow(row);
						table.revalidate();

						if (pass == numStudenti) {

							int i = 0;
							while (i < numStudenti) {

								controller.getConnectionDao().getIscrizioneDao().updateStatoStudente(controller.getConnectionDao().getConnection(),
										studentiEPromozione[i].get(2).toString().substring(1,
												// prendo semplicemente la stringa del cf e levo le quadre.
												studentiEPromozione[i].get(2).toString().length() - 1),
										studentiEPromozione[i].get(3).toString());

								i++;
							}

							int corsoI = controller.getConnectionDao().getCorsoDao().trovaCorsoId(controller.getConnectionDao().getConnection(), nomeCorso);
							controller.getConnectionDao().getCorsoDao().updateCheckCorsoGestito(controller.getConnectionDao().getConnection(), corsoI);

							JOptionPane.showMessageDialog(null,
									"Gestiti tutti gli studenti del corso.Il corso,con un report,potrai vederlo nella sezione \"Corsi Archiviati\".",
									"Ok!", JOptionPane.INFORMATION_MESSAGE);

							setVisible(false);
							new FrameDiScelta();

						
						
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
		
		setVisible(true);
	}
}
