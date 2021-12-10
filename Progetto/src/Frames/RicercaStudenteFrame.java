package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.Box;
import com.toedter.calendar.JDateChooser;

import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.IscrizioneDaoImpl;
import Oggetti.DAO.StudenteDaoImpl;

import java.awt.Font;

public class RicercaStudenteFrame extends JFrame {
	
	private ConnectionDao connectionDao;
	private JPanel contentPane;
	private GeneralPanelGrande panel;
	private JTextField nomeField;
	private JTextField cognomeField;
	private JTextField cfField;
	private JScrollPane scrollPane;
	private JButton buttonRicerca;
	int flagNome = 0;
	int flagCf = 0;
	int flagCognome = 0;
	int flagDate = 0;
	private Controller controller;
	
	public RicercaStudenteFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(RicercaStudenteFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanelGrande();
		panel.setBounds(0, 0, 623, 516);
		getContentPane().add(panel);
		
		connectionDao = new ConnectionDao();
		connectionDao.setConnection(connectionDao.createConnection());
		
		controller = new Controller();
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nomeLabel.setBounds(52, 112, 46, 20);
		panel.add(nomeLabel);
		
		nomeField = new JTextField();
		nomeField.setEnabled(false);
		nomeField.setBounds(108, 112, 137, 20);
		panel.add(nomeField);
		nomeField.setColumns(10);
		
		cognomeField = new JTextField();
		cognomeField.setEnabled(false);
		cognomeField.setColumns(10);
		cognomeField.setBounds(108, 157, 137, 20);
		panel.add(cognomeField);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeLabel.setBounds(38, 157, 60, 20);
		panel.add(cognomeLabel);
		
		cfField = new JTextField();
		cfField.setEnabled(false);
		cfField.setColumns(10);
		cfField.setBounds(108, 198, 137, 20);
		panel.add(cfField);
		
		JLabel cfLabel = new JLabel("CF");
		cfLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cfLabel.setBounds(52, 198, 46, 20);
		panel.add(cfLabel);
		DefaultTableModel model = new DefaultTableModel(new Object[][] {
		},
		new String[] {
			"Nome" ,"Cognome" ,"CF","Corso","N.Lezioni" ,"Presenze" 
		}) {
			public boolean isCellEditable(int row ,int column) {
				return false;
			}
		};
		
		JTable tableStats = new JTable(model) {
			
			@Override
			public Component prepareRenderer(TableCellRenderer r,int row , int column) {
				
				Component c = super.prepareRenderer(r, row, column);
				
				if(model.getValueAt(row, 5).toString().charAt(0) == '[') {
					
					int value = 0;
					
					if(model.getValueAt(row, 5).toString().length() == 4){
						
						value = 1;
						
					}
					
					if(model.getValueAt(row, 5).toString().length() == 5){
						
						value = Integer.parseInt(model.getValueAt(row, 5).toString().substring(1, 3));
						
					}
					
					if(model.getValueAt(row, 5).toString().length() == 6){
						
						value = Integer.parseInt(model.getValueAt(row, 5).toString().substring(1, 4));
						
					}
					
					
					
					
					if( value < 60) {
						
						c.setBackground(Color.RED);
						
					}else
						c.setBackground(Color.green);
					
					return c;
					
					
				}else {
					
					int value = 0;
					
					if(model.getValueAt(row, 5).toString().length() == 2){
						
						value = 1;
						
					}
					
					if(model.getValueAt(row, 5).toString().length() == 3){
						
						value = Integer.parseInt(model.getValueAt(row, 5).toString().substring(0, 2));
						
					}
					
					if(model.getValueAt(row, 5).toString().length() == 4){
						
						value = Integer.parseInt(model.getValueAt(row, 5).toString().substring(0, 3));
						
					}
					
					
					
					
					if( value < 60) {
						
						c.setBackground(Color.RED);
						
					}else
						c.setBackground(Color.green);
					
					return c;
					
				}

			}
		};
		tableStats.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
		tableStats.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableStats.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableStats.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableStats.getColumnModel().getColumn(4).setPreferredWidth(75);
		tableStats.getColumnModel().getColumn(5).setPreferredWidth(75);
		
		scrollPane = new JScrollPane(tableStats);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 269, 603, 183);
		panel.add(scrollPane);
		
		buttonRicerca = new JButton("Ricerca");
		buttonRicerca.setForeground(Color.RED);
		buttonRicerca.setBorder(new RoundBorderBotton(10));
		buttonRicerca.setBackground(Color.WHITE);
		buttonRicerca.setBounds(326, 190, 97, 37);
		panel.add(buttonRicerca);
		
		JCheckBox nomeCheck = new JCheckBox("");
		nomeCheck.setBackground(Color.white);
		nomeCheck.setHorizontalAlignment(SwingConstants.CENTER);
		nomeCheck.setBounds(251, 112, 15, 22);
		panel.add(nomeCheck);
		
		JCheckBox cognomeCheck = new JCheckBox("");
		cognomeCheck.setBackground(Color.white);
		cognomeCheck.setHorizontalAlignment(SwingConstants.CENTER);
		cognomeCheck.setBounds(251, 155, 15, 22);
		panel.add(cognomeCheck);
		
		JCheckBox cfCheck = new JCheckBox("");
		cfCheck.setBackground(Color.white);
		cfCheck.setHorizontalAlignment(SwingConstants.CENTER);
		cfCheck.setBounds(251, 196, 15, 22);
		panel.add(cfCheck);
		
		JButton buttonElimina = new JButton("Elimina Studente");
		buttonElimina.setForeground(Color.RED);
		buttonElimina.setBorder(new RoundBorderBotton(10));
		buttonElimina.setBackground(Color.WHITE);
		buttonElimina.setBounds(263, 463, 126, 26);
		panel.add(buttonElimina);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setBounds(108, 238, 137, 20);
		panel.add(dateChooser);
		
		JLabel lblNewLabel = new JLabel("Data Iscrizione");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 238, 88, 20);
		panel.add(lblNewLabel);
		
		JCheckBox dateCheck = new JCheckBox("");
		dateCheck.setHorizontalAlignment(SwingConstants.CENTER);
		dateCheck.setBackground(Color.WHITE);
		dateCheck.setBounds(251, 238, 15, 22);
		panel.add(dateCheck);
		setVisible(true);
		
		nomeCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				flagNome = controller.isEnbl(nomeField, flagNome ,null ,null);
				
			}
			
		});
		
		cognomeCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				flagCognome = controller.isEnbl(cognomeField, flagCognome ,null ,null);
				
			}
			
		});
		
		cfCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				flagCf = controller.isEnbl(cfField, flagCf ,null ,null);
				
			}
			
		});
		
		dateCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flagDate = controller.isEnbl(null ,flagDate ,dateChooser ,null);
			}
			
		});
		
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.setRowCount(0);
				tableStats.revalidate();
				controller.ricercaStudente(nomeField ,cognomeField ,cfField ,dateChooser, tableStats,flagNome ,flagCognome ,flagCf ,flagDate ,cfLabel ,model);
				
			}
		});
		

		buttonElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = tableStats.getSelectedRow();

				if(row != -1) {
					
					//PRENDO CF E TOLGO LE QUADRE
					String cf = model.getValueAt(row, 2).toString();
					String cfFormatted = cf.substring(1, cf.length()-1);
					
					String corso = model.getValueAt(row, 3).toString();
					int corsoId;
					
					
					corsoId = connectionDao.getCorsoDao().trovaCorsoId(connectionDao.getConnection(), corso.substring(1, corso.length()-1));
					
					String achieved;
					achieved = "true";
					
					if(corsoId != -1) {
						
						achieved = connectionDao.getCorsoDao().controllaStatoCorso(connectionDao.getConnection(), corsoId);
						
						if(achieved.equals("truex") || achieved.equals("true") ) {
							
							JOptionPane.showMessageDialog(null, "Non puo eliminare uno studente da un corso finito o archiviato.", "ERROR", JOptionPane.ERROR_MESSAGE);			
							
						}else {
							
							if(!corso.substring(1, corso.length()-1).equals("Empty")) {
								

								int answer = JOptionPane.showConfirmDialog(null, "Sicuro di voler eliminare questo studente da questo corso?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
								
								//E' SI QUINDI CANCELLA
								if(answer == 0) {
									
									
									connectionDao.getIscrizioneDao().deleteStudente(connectionDao.getConnection(), cfFormatted ,corsoId);
									JOptionPane.showMessageDialog(null, "Studente Cancellato", "Deleted", JOptionPane.INFORMATION_MESSAGE);
									model.removeRow(row);
									tableStats.revalidate();
									
								}
								
							}
							
						}
						
					}else {
						
						int risposta = JOptionPane.showConfirmDialog(null, "Studente non iscritto a corsi.Sicuro di voler eliminare questo studente dal database?", "Confirm", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						if(risposta == 0) {
							
						
							connectionDao.getStudenteDao().deleteStudente(connectionDao.getConnection(), cfFormatted);
							JOptionPane.showMessageDialog(null, "Studente eliminato dal db", "Ok", JOptionPane.INFORMATION_MESSAGE);
							model.removeRow(row);
							tableStats.revalidate();
							
						}
					}
							
				}else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);
	
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
