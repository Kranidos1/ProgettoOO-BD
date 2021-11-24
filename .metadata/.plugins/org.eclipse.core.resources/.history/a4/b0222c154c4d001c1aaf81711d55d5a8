package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JYearChooser;

import Oggetti.DAO.CorsoDaoImpl;

import com.toedter.calendar.JDateChooser;
import java.time.*;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JScrollPane;
public class IscriviStudenteFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private JTextField nomeTextField;
	private JTextField cognomeTextField;
	private JTextField cfTextField;
	private Controller controller;
	
	public IscriviStudenteFrame() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/imgs/lastin.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanel();
		panel.setBounds(0, 0, 501, 516);
		getContentPane().add(panel);
		
		controller = new Controller();
		
		nomeTextField = new JTextField();
		nomeTextField.setColumns(50);
		nomeTextField.setBounds(160, 200, 150, 20);
		panel.add(nomeTextField);
		
		cognomeTextField = new JTextField();
		cognomeTextField.setColumns(50);
		cognomeTextField.setBounds(160, 253, 150, 20);
		panel.add(cognomeTextField);
		
		cfTextField = new JTextField();
		cfTextField.setColumns(50);
		cfTextField.setBounds(160, 360, 150, 20);
		panel.add(cfTextField);
		
		JLabel corsoLabel = new JLabel("Corso");
		corsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		corsoLabel.setBounds(64, 111, 86, 20);
		panel.add(corsoLabel);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeLabel.setBounds(64, 200, 86, 20);
		panel.add(nomeLabel);
		
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cognomeLabel.setBounds(64, 253, 86, 20);
		panel.add(cognomeLabel);
		
		JLabel dataLabel = new JLabel("Data di Nascita");
		dataLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dataLabel.setBounds(64, 306, 86, 20);
		panel.add(dataLabel);
		
		JLabel cfLabel = new JLabel("Codice Fiscale");
		cfLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		cfLabel.setBounds(64, 360, 86, 20);
		panel.add(cfLabel);
		
		DefaultListModel model = new DefaultListModel();
		JList listStudente = new JList(model);
		JScrollPane scrollPane = new JScrollPane(listStudente);
		scrollPane.setBounds(160, 111, 150, 78);
		panel.add(scrollPane);
		CorsoDaoImpl corsoDaoImpl = new CorsoDaoImpl();
		List<String> listaCorsi = corsoDaoImpl.getNomiCorsi(controller.getConnection());
		model.addAll(listaCorsi);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(160, 306, 170, 20);
		
		Date dataAttuale = new Date();
		
		int anno = dataAttuale.getYear();
		
		Integer maggiorenne = (anno - 18) + 1900;
		Integer annoMin = (anno - 60) + 1900;
		
		String year = maggiorenne.toString();
		String datMax;
		String datMin;
		
		Date dataMax;
		Date dataMin;
		
		String yearMin = annoMin.toString();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		datMax = year + ".12.31";
		datMin = yearMin + ".01.01";
		
		
		try {
			
			dataMax = format.parse(datMax);
			dateChooser.setMaxSelectableDate(dataMax);
			dataMin = format.parse(datMin);
			dateChooser.setMinSelectableDate(dataMin);
			dateChooser.setDate(dataMin);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dateChooser.setLocale(getLocale());
		dateChooser.setDateFormatString("yyyy.MM.dd");
		
		panel.add(dateChooser);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				controller.controlloInserimentoStudente(nomeTextField, cognomeTextField, listStudente, dateChooser, cfLabel, cfTextField);
				
			}
		});

		saveButton.setForeground(Color.RED);
		saveButton.setBorder(new RoundBorderBotton(10));
		saveButton.setBackground(Color.WHITE);
		saveButton.setBounds(356, 237, 104, 54);
		panel.add(saveButton);
		

		
		
		
		setVisible(true);
		
	}
}
