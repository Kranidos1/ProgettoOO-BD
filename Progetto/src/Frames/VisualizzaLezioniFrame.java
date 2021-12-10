package Frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Oggetti.DAO.ConnectionDao;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.LezioneDaoImpl;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

public class VisualizzaLezioniFrame extends JFrame {

	private ConnectionDao connectionDao;
	private JPanel contentPane;
	private Controller controller;
	private String tmpCorso;
	private int corsoId;
	private List<String>[] listaDateLezioni;
	private int lezioneId;
	private JTextField titoloField;

	public VisualizzaLezioniFrame() {

		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestisciPresenzeFrame.class.getResource("/imgs/lastin.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 639);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		connectionDao = new ConnectionDao();
		connectionDao.setConnection(connectionDao.createConnection());
		
		controller = new Controller();

		GeneralPanelGrande generalPanelGrande = new GeneralPanelGrande();
		generalPanelGrande.setBounds(0, 0, 624, 601);
		contentPane.add(generalPanelGrande);

		DefaultListModel modelList = new DefaultListModel();
		JList listCorsi = new JList(modelList);
		JScrollPane scrollPaneCorsi = new JScrollPane(listCorsi);
		scrollPaneCorsi.setBackground(Color.white);
		scrollPaneCorsi.setBounds(10, 118, 181, 97);
		scrollPaneCorsi.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK), "Corsi"));
		generalPanelGrande.add(scrollPaneCorsi);

		JButton visualizzaLezioniButton = new JButton("Visualizza Lezioni");
		visualizzaLezioniButton.setForeground(Color.RED);
		visualizzaLezioniButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		visualizzaLezioniButton.setBorder(new RoundBorderBotton(10));
		visualizzaLezioniButton.setBackground(Color.WHITE);
		visualizzaLezioniButton.setBounds(215, 153, 109, 32);
		generalPanelGrande.add(visualizzaLezioniButton);

		DefaultListModel modelLezioni = new DefaultListModel();
		JList listLezioni = new JList(modelLezioni);
		JScrollPane scrollPaneLezioni = new JScrollPane(listLezioni);
		scrollPaneLezioni.setBackground(Color.white);
		scrollPaneLezioni.setBounds(414, 153, 200, 351);
		scrollPaneLezioni.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Lista Lezioni"));
		generalPanelGrande.add(scrollPaneLezioni);

		JButton cercaLezioneButton = new JButton("Cerca Lezione");
		cercaLezioneButton.setEnabled(false);
		cercaLezioneButton.setForeground(Color.RED);
		cercaLezioneButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		cercaLezioneButton.setBorder(new RoundBorderBotton(10));
		cercaLezioneButton.setBackground(Color.WHITE);
		cercaLezioneButton.setBounds(466, 515, 109, 22);
		generalPanelGrande.add(cercaLezioneButton);

		JPanel panelModificaCorso = new JPanel();
		panelModificaCorso.setBackground(Color.WHITE);
		panelModificaCorso.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Modifica Lezione"));
		panelModificaCorso.setBounds(10, 237, 394, 301);
		generalPanelGrande.add(panelModificaCorso);
		panelModificaCorso.setLayout(null);

		titoloField = new JTextField();
		titoloField.setEnabled(false);
		titoloField.setBounds(112, 29, 191, 20);
		panelModificaCorso.add(titoloField);
		titoloField.setColumns(10);

		JLabel titoloLabel = new JLabel("Titolo");
		titoloLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		titoloLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titoloLabel.setBounds(48, 29, 59, 17);
		panelModificaCorso.add(titoloLabel);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setEnabled(false);
		dateChooser.setBounds(112, 71, 191, 20);
		panelModificaCorso.add(dateChooser);

		JLabel dataLabel = new JLabel("Data");
		dataLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		dataLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dataLabel.setBounds(48, 71, 59, 20);
		panelModificaCorso.add(dataLabel);

		JPanel panelInizio = new JPanel();
		panelInizio.setLayout(null);
		panelInizio.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black),
				"Ora Inizio"));
		panelInizio.setBackground(Color.WHITE);
		panelInizio.setBounds(58, 102, 104, 63);
		panelModificaCorso.add(panelInizio);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.HOUR_OF_DAY);
		JSpinner spinnerInizio = new JSpinner(
				new SpinnerDateModel(new Date(1637337589030L), null, null, Calendar.HOUR_OF_DAY));
		spinnerInizio.setEnabled(false);
		spinnerInizio.setEditor(new JSpinner.DateEditor(spinnerInizio, "kk:mm"));
		spinnerInizio.setBounds(10, 21, 65, 31);
		panelInizio.add(spinnerInizio);

		JPanel panelDurata = new JPanel();
		panelDurata.setLayout(null);
		panelDurata.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black),
				"Durata Ore/Minuti"));
		panelDurata.setBackground(Color.WHITE);
		panelDurata.setBounds(216, 102, 105, 63);
		panelModificaCorso.add(panelDurata);

		JSpinner spinnerDurata = new JSpinner();
		spinnerDurata.setEnabled(false);
		spinnerDurata.setModel(new SpinnerNumberModel(new Integer(5), null, null, new Integer(1)));
		spinnerDurata.setBounds(10, 21, 74, 31);
		panelDurata.add(spinnerDurata);

		JTextPane textAreaDescrizione = new JTextPane();
		textAreaDescrizione.setEnabled(false);
		textAreaDescrizione.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black), "Descrizione"));
		textAreaDescrizione.setBounds(10, 164, 374, 126);
		panelModificaCorso.add(textAreaDescrizione);

		JButton updateButton = new JButton("Save");
		updateButton.setEnabled(false);
		updateButton.setForeground(Color.RED);
		updateButton.setFont(new Font("Dialog", Font.PLAIN, 11));
		updateButton.setBorder(new RoundBorderBotton(10));
		updateButton.setBackground(Color.WHITE);
		updateButton.setBounds(138, 548, 109, 22);
		generalPanelGrande.add(updateButton);

		List<String> listaCorsi = connectionDao.getCorsoDao().getNomiCorsi(connectionDao.getConnection());
		modelList.addAll(listaCorsi);

		visualizzaLezioniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (listCorsi.getSelectedValue() != null) {

					tmpCorso = listCorsi.getSelectedValue().toString();
					visualizzaLezioniButton.setEnabled(false);
					corsoId = connectionDao.getCorsoDao().trovaCorsoId(connectionDao.getConnection(), tmpCorso);

					listaDateLezioni = connectionDao.getLezioneDao()
							.getDateLezioniDaGestireELezione(connectionDao.getConnection(), corsoId);
//					modelLezioni.addAll(listaDateLezioni);
					int i = 0;

					while (i < Arrays.asList(listaDateLezioni).size()) {

						modelLezioni.addElement(listaDateLezioni[i].get(0));

						i++;
					}

					cercaLezioneButton.setEnabled(true);
					listCorsi.setEnabled(false);

				} else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);

			}
		});

		SimpleDateFormat format = new SimpleDateFormat("kk:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cercaLezioneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (listLezioni.getSelectedValue() != null) {

					String data = listLezioni.getSelectedValue().toString();

					int i = 0;
					while (i < Arrays.asList(listaDateLezioni).size()) {

						if (listaDateLezioni[i].get(0) == data) {

							lezioneId = Integer.parseInt(listaDateLezioni[i].get(1));
							titoloField.setText(listaDateLezioni[i].get(6));
							try {
								dateChooser.setDate(dateFormat.parse(listaDateLezioni[i].get(0).toString()));
							} catch (ParseException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
							textAreaDescrizione.setText(listaDateLezioni[i].get(5));
							try {
								spinnerInizio.setValue(format.parseObject(listaDateLezioni[i].get(3).toString()));
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							spinnerDurata.setValue(Integer.parseInt(listaDateLezioni[i].get(4)));

							break;

						}

						i++;
					}

					cercaLezioneButton.setEnabled(false);
					titoloField.setEnabled(true);
					dateChooser.setEnabled(true);
					spinnerDurata.setEnabled(true);
					spinnerInizio.setEnabled(true);
					textAreaDescrizione.setEnabled(true);
					updateButton.setEnabled(true);

				} else
					controller.jpanelManagementCreaCorsoFrame(null, null, null, 10);

			}
		});

		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				controller.inserisciLezione(tmpCorso, titoloField, dateChooser, spinnerInizio, spinnerDurata,
						textAreaDescrizione, format, lezioneId, 1);

				cercaLezioneButton.setEnabled(true);
				titoloField.setEnabled(false);
				dateChooser.setEnabled(false);
				spinnerDurata.setEnabled(false);
				spinnerInizio.setEnabled(false);
				textAreaDescrizione.setEnabled(false);
				updateButton.setEnabled(false);

				titoloField.setText("");
				dateChooser.cleanup();
				try {
					spinnerInizio.setValue(format.parseObject("01:01"));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				spinnerDurata.setValue(1);
				textAreaDescrizione.setText("");

				listaDateLezioni = connectionDao.getLezioneDao()
						.getDateLezioniDaGestireELezione(connectionDao.getConnection(), corsoId);
//				modelLezioni.addAll(listaDateLezioni);
				int i = 0;

				modelLezioni.clear();
				while (i < Arrays.asList(listaDateLezioni).size()) {

					modelLezioni.addElement(listaDateLezioni[i].get(0));

					i++;
				}

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
