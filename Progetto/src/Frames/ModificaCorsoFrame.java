package Frames;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.CorsoETemaDaoImpl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class ModificaCorsoFrame extends JFrame {

	private JTextField nomeField;
	private JTextField maxField;
	private JTextField minField;
	private JTextArea textAreaDescrizione;
	private JList<String> list;
	private JComboBox<String> choiceOption;
	private int j = 0;
	private Controller controller;
	private String id;
	
	public ModificaCorsoFrame(JFrame frame) {
		
		
		super("Project GRU");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 491);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 501, 452);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		controller = new Controller();
		
		nomeField = new JTextField();
		nomeField.setColumns(50);
		nomeField.setBounds(137, 42, 172, 20);
		panel.add(nomeField);
		
		maxField = new JTextField();
		maxField.setColumns(50);
		maxField.setBounds(137, 96, 172, 20);
		panel.add(maxField);
		
		minField = new JTextField();
		minField.setColumns(50);
		minField.setBounds(137, 150, 172, 20);
		panel.add(minField);
		
		choiceOption = new JComboBox<String>();
		choiceOption.setMaximumRowCount(5);
		choiceOption.setBackground(Color.WHITE);
		choiceOption.setBounds(137, 195, 172, 20);
		panel.add(choiceOption);
		
		textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		textAreaDescrizione.setBounds(10, 248, 318, 148);
		panel.add(textAreaDescrizione);
		
		JButton buttonSave = new JButton("Save");
		buttonSave.setForeground(Color.RED);
		buttonSave.setBorder(new RoundBorderBotton(10));
		buttonSave.setBackground(Color.WHITE);
		buttonSave.setBounds(110, 407, 89, 23);
		panel.add(buttonSave);
		
		JButton buttonCleanArea = new JButton("Clean Area");
		buttonCleanArea.setForeground(Color.RED);
		buttonCleanArea.setBorder(new RoundBorderBotton(10));
		buttonCleanArea.setBackground(Color.WHITE);
		buttonCleanArea.setBounds(348, 407, 89, 23);
		panel.add(buttonCleanArea);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setValueIsAdjusting(true);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setBounds(0, 0, 149, 144);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createMatteBorder(2 , 2, 2, 2, Color.black));
		scrollPane.setBounds(338, 248, 153, 148);
		panel.add(scrollPane);
		
		JLabel nomeCorsoLabel = new JLabel("Nome");
		nomeCorsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeCorsoLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		nomeCorsoLabel.setBounds(56, 37, 71, 29);
		panel.add(nomeCorsoLabel);
		
		JLabel maxLabel = new JLabel("Max Partecipants");
		maxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		maxLabel.setBounds(9, 91, 118, 29);
		panel.add(maxLabel);
		
		JLabel minLabel = new JLabel("Min Partecipants");
		minLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		minLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		minLabel.setBounds(20, 145, 108, 29);
		panel.add(minLabel);
		
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		descriptionLabel.setBounds(10, 233, 71, 14);
		panel.add(descriptionLabel);
		
		JLabel lblSelectedTheme = new JLabel("Selected Theme");
		lblSelectedTheme.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectedTheme.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblSelectedTheme.setBounds(338, 234, 135, 14);
		panel.add(lblSelectedTheme);
		
		JLabel themesLabel = new JLabel("Themes");
		themesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		themesLabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		themesLabel.setBounds(19, 194, 108, 21);
		panel.add(themesLabel);
		

		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop (true);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	
            	frame.setEnabled(true);
            	
            	
            }
            @Override
            public void windowOpened(WindowEvent e) {
                frame.setEnabled(false);
            }
        });
		
		
		setVisible(true);
		
		
		List<String> updateThemes = new LinkedList<String>();
		choiceOption.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					if(j == 0) {						
						j++;				
					}else {		
						
						String theme = (String) e.getItem();
						controller.insertInListAndControl(model ,theme ,(JFrame) SwingUtilities.getRoot(lblSelectedTheme));
						updateThemes.add(theme);
						
					}
					
					
				}
			}
			
		});
		
		CorsoETemaDaoImpl corsoTema = new CorsoETemaDaoImpl();
		
		buttonCleanArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.removeAllElements();
				corsoTema.deleteCollegamento(controller.getConnection() ,id);
				
				if(!updateThemes.isEmpty()) {
					
					updateThemes.clear();
					
				}
				
			}
		});
		
		CorsoDaoImpl corsoDao = new CorsoDaoImpl();
		
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Iterator<String> iteratore = updateThemes.iterator();
				
				int idCorso = Integer.parseInt(id);
				
				while(iteratore.hasNext()) {
					
					corsoTema.inserimento(controller.getConnection(), idCorso, iteratore.next());	
					
				}

				controller.insertCorsoDb((JFrame) SwingUtilities.getRoot(lblSelectedTheme), nomeField, maxField, minField, textAreaDescrizione, model, 1, Integer.toString(idCorso));
				//SALVATAGGIO A BUON FINE
				JOptionPane.showMessageDialog((JFrame)SwingUtilities.getRoot(lblSelectedTheme) , "Saved!", "OK!", JOptionPane.INFORMATION_MESSAGE);
				
				//CHIUSURA FINESTRA
				WindowEvent close = new WindowEvent((JFrame)SwingUtilities.getRoot(lblSelectedTheme) ,WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(close);
				
			}
		});
		
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public JComboBox<String> getChoiceOption() {
		return choiceOption;
	}


	public void setChoiceOption(JComboBox<String> choiceOption) {
		this.choiceOption = choiceOption;
	}


	public JTextField getNomeField() {
		return nomeField;
	}

	public void setNomeField(JTextField nomeField) {
		this.nomeField = nomeField;
	}

	public JTextField getMaxField() {
		return maxField;
	}

	public void setMaxField(JTextField maxField) {
		this.maxField = maxField;
	}

	public JTextField getMinField() {
		return minField;
	}

	public void setMinField(JTextField minField) {
		this.minField = minField;
	}

	public JTextArea getTextAreaDescrizione() {
		return textAreaDescrizione;
	}

	public void setTextAreaDescrizione(JTextArea textAreaDescrizione) {
		this.textAreaDescrizione = textAreaDescrizione;
	}

	public JList<String> getList() {
		return list;
	}

	public void setList(JList<String> list) {
		this.list = list;
	}
	

}
