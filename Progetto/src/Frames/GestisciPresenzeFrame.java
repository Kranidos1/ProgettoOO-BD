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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;

import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;

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
		secondoStepPanel.setBounds(220, 123, 393, 414);
		panel.add(secondoStepPanel);
		secondoStepPanel.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy.MM.dd");
		Date dataAttuale = new Date();
		String data = formatDate.format(dataAttuale);
		Date actualDate;
		
		try {
			
			actualDate = formatDate.parse(data);
			dateChooser.setMaxSelectableDate(actualDate);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		dateChooser.setBounds(31, 40, 178, 20);
		secondoStepPanel.add(dateChooser);
		
		JButton btnCercaLezione = new JButton("Cerca Lezione");
		btnCercaLezione.setForeground(Color.RED);
		btnCercaLezione.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCercaLezione.setBorder(new RoundBorderBotton(10));
		btnCercaLezione.setBackground(Color.WHITE);
		btnCercaLezione.setBounds(233, 40, 95, 20);
		secondoStepPanel.add(btnCercaLezione);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "CF", "%Presenze","N.Lezioni"
			}
		));
		table.setBounds(108, 474, 1, 1);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 71, 373, 272);
		secondoStepPanel.add(scrollPane);
		
		JButton buttonPresente = new JButton("Presente");
		buttonPresente.setForeground(Color.RED);
		buttonPresente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonPresente.setBorder(new RoundBorderBotton(10));
		buttonPresente.setBackground(Color.WHITE);
		buttonPresente.setBounds(71, 354, 114, 32);
		secondoStepPanel.add(buttonPresente);
		
		JButton buttonAssente = new JButton("Assente");
		buttonAssente.setForeground(Color.RED);
		buttonAssente.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonAssente.setBorder(new RoundBorderBotton(10));
		buttonAssente.setBackground(Color.WHITE);
		buttonAssente.setBounds(223, 354, 114, 32);
		secondoStepPanel.add(buttonAssente);
		
		JLabel dataLabel = new JLabel("Data");
		dataLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dataLabel.setBounds(33, 25, 47, 14);
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
		scrollPaneLezioni.setBounds(620, 33, 276, 504);
		panel.add(scrollPaneLezioni);
		
		CorsoDaoImpl corso = new CorsoDaoImpl();
		model.addAll(corso.getNomiCorsi(controller.getConnection()));
		
		stepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				corsoSelected = listCorsi.getSelectedValue().toString();
				primoStepPanel.setEnabled(false);
				listCorsi.setEnabled(false);
				stepButton.setEnabled(false);
				
				CorsoDaoImpl corsoDao = new CorsoDaoImpl();
				int corsoId = corsoDao.trovaCorsoId(controller.getConnection(), corsoSelected);
				LezioneDaoImpl lezioneDao = new LezioneDaoImpl();
				
				modelListLezioni.addAll(lezioneDao.getDateLezioniDaGestire(controller.getConnection(), corsoId));
				corsoTrovatoLabel.setText(listCorsi.getSelectedValue().toString());
				
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
					
					CorsoDaoImpl corsoDao = new CorsoDaoImpl();
					int corsoId = corsoDao.trovaCorsoId(controller.getConnection(), corsoSelected);
					LezioneDaoImpl lezioneDao = new LezioneDaoImpl();
					
					modelListLezioni.addAll(lezioneDao.getDateLezioniDaGestire(controller.getConnection(), corsoId));
					
		        }
		    }
		    
		});
		
		btnCercaLezione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String tmpDate = null;
				
				if(dateChooser.getDate() != null) {
					
					tmpDate = sdf.format(dateChooser.getDate());
					//QUERY ATTRAVERSO DATA OVVERO CERCO L'ID DELLA LEZIONE TRAMITE DATA E PRENDO TUTTI GLI STUDENTI FACENDONE ANCHE UNA COUNT INOLTRE VEDI VALORE X E LA LEZIONE DIVENTA TRUE NON FALSE
					
				}else
					//gestisci errore
					System.out.println("ciao");
			}
		});
		
		setVisible(true);
	}
}
