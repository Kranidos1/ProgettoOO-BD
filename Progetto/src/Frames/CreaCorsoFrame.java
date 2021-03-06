package Frames;
import java.awt.*;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.text.AttributeSet.ColorAttribute;

import org.w3c.dom.events.MouseEvent;

import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.ConnectionDao;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class CreaCorsoFrame extends JFrame{

	private JPanel contentPane;
	private GeneralPanel panel;
	private JTextField textFieldName;
	private JTextField textFieldMax;
	private JTextField textFieldMin;
	private JTextField textFieldTheme;
	private JFrame fram;
	private Controller controller;
	public int counter = 0;
	private int j = 0;
	
	public CreaCorsoFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreaCorsoFrame.class.getResource("/imgs/lastin.png")));
		controller = new Controller();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		fram = (JFrame) SwingUtilities.getRoot(panel);
		
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		

		
		JLabel nomeCorsoLabel = new JLabel("Nome");
		nomeCorsoLabel.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		nomeCorsoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		nomeCorsoLabel.setBounds(47, 112, 71, 29);
		panel.add(nomeCorsoLabel);
		
		JLabel maxLabel = new JLabel("Max Partecipants");
		maxLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		maxLabel.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		maxLabel.setBounds(0, 152, 118, 29);
		panel.add(maxLabel);
		
		JLabel minLabel = new JLabel("Min Partecipants");
		minLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		minLabel.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		minLabel.setBounds(10, 192, 108, 29);
		panel.add(minLabel);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(130, 118, 172, 20);
		panel.add(textFieldName);
		textFieldName.setColumns(50);
		
		textFieldMax = new JTextField();
		textFieldMax.setColumns(50);
		textFieldMax.setBounds(130, 158, 172, 20);
		panel.add(textFieldMax);
		
		textFieldMin = new JTextField();
		textFieldMin.setColumns(50);
		textFieldMin.setBounds(130, 201, 172, 20);
		panel.add(textFieldMin);
		
		JTextArea textAreaDescrizione = new JTextArea();
		textAreaDescrizione.setBounds(10, 293, 318, 148);
		textAreaDescrizione.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		panel.add(textAreaDescrizione);
		
		JButton buttonSave = new JButton("Save");
		buttonSave.setBounds(10, 465, 89, 23);
		buttonSave.setBorder(new RoundBorderBotton(10));
		buttonSave.setBackground(Color.white);
		buttonSave.setForeground(Color.red);
		panel.add(buttonSave);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setToolTipText("Use this field only if you forgot to create ONE theme.You can't create here two themes or more.");
		checkBox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBox.setBounds(329, 140, 18, 20);
		checkBox.setBackground(Color.white);
		panel.add(checkBox);
		
		JPanel panelTema = new JPanel();
		panelTema.setToolTipText("Use this field only if you forgot to create ONE theme.You can't create here two themes or more.");
		panelTema.setBounds(353, 152, 121, 82);
		panelTema.setBackground(Color.white);
		panel.add(panelTema);
		panelTema.setLayout(null);
		panelTema.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel labelTemaPanel = new JLabel("New Theme");
		labelTemaPanel.setToolTipText("Use this field only if you forgot to create ONE theme.You can't create here two themes or more.");
		labelTemaPanel.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		labelTemaPanel.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemaPanel.setBounds(10, 0, 101, 32);
		panelTema.add(labelTemaPanel);
		
		textFieldTheme = new JTextField();
		textFieldTheme.setToolTipText("Use this field only if you forgot to create ONE theme.You can't create here two themes or more.");
		textFieldTheme.setEnabled(false);
		textFieldTheme.setBounds(10, 31, 101, 20);
		panelTema.add(textFieldTheme);
		textFieldTheme.setColumns(10);
		
		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		descriptionLabel.setBounds(10, 276, 71, 14);
		panel.add(descriptionLabel);
		
		JComboBox<String> choiceOption = new JComboBox<String>();
		choiceOption.setMaximumRowCount(5);
		choiceOption.setBackground(Color.white);
		choiceOption.setBounds(130, 244, 172, 20);
		panel.add(choiceOption);
		
		
		JLabel themesLabel = new JLabel("Themes");
		themesLabel.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		themesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		themesLabel.setBounds(10, 244, 108, 21);
		panel.add(themesLabel);
		
		JButton buttonCleanAll = new JButton("Clean All");
		buttonCleanAll.setForeground(Color.RED);
		buttonCleanAll.setBorder(new RoundBorderBotton(10));
		buttonCleanAll.setBackground(Color.WHITE);
		buttonCleanAll.setBounds(130, 465, 89, 23);
		panel.add(buttonCleanAll);
		
		JLabel lblSelectedTheme = new JLabel("Selected Theme");
		lblSelectedTheme.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelectedTheme.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		lblSelectedTheme.setBounds(339, 276, 135, 14);
		panel.add(lblSelectedTheme);
		
		JButton buttonSaveTheme = new JButton("Save Theme");
		buttonSaveTheme.setEnabled(false);
		buttonSaveTheme.setForeground(Color.RED);
		buttonSaveTheme.setFont(new Font("Tahoma", Font.PLAIN, 8));
		buttonSaveTheme.setBorder(new RoundBorderBotton(10));
		buttonSaveTheme.setBackground(Color.WHITE);
		buttonSaveTheme.setBounds(396, 245, 78, 14);
		panel.add(buttonSaveTheme);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		JList<String> list = new JList<String>(model);
		list.setValueIsAdjusting(true);
		list.setBounds(158, 331, 1, 1);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(338, 293, 153, 148);
		panel.add(scrollPane);
		scrollPane.setBorder(BorderFactory.createMatteBorder(2 , 2, 2, 2, Color.black));
		
		JButton buttonCleanArea = new JButton("Clean Area");
		buttonCleanArea.setForeground(Color.RED);
		buttonCleanArea.setBorder(new RoundBorderBotton(10));
		buttonCleanArea.setBackground(Color.WHITE);
		buttonCleanArea.setBounds(385, 444, 89, 14);
		panel.add(buttonCleanArea);
		
		checkBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			counter = controller.textEnableDisable(buttonSaveTheme ,fram, counter, textFieldTheme, panelTema);
		
			}
			
		});
		
		buttonCleanAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textAreaDescrizione.setText("");
				textFieldMin.setText("");
				textFieldMax.setText("");
				textFieldName.setText("");
				textFieldTheme.setText("");
				model.removeAllElements();
				
			}
		});
		
		
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//BOOLEAN DI CONTROLLO
				controller.insertCorsoDb(fram, textFieldName, textFieldMax, textFieldMin, textAreaDescrizione, model ,0 ,null);
				
			}
			
		});
		
		buttonSaveTheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				controller.insertNewThemeFromField(fram, textFieldTheme, model);
				counter = 3;
				controller.textEnableDisable(buttonSaveTheme, fram, counter, textFieldTheme, panelTema);
			}			
		});
		
		choiceOption.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					if(j == 0) {						
						j++;						
					}else {		
						String theme = (String) e.getItem();
						controller.insertInListAndControl(model ,theme ,fram);
					}
					
					
				}
			}
			
		});
		
		buttonCleanArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				model.removeAllElements();
				
				
			}
		});
		
		textFieldName.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getSource() == textFieldName) {
					
					char p = e.getKeyChar();
					
					if(controller.controlloField(p) == 1) {
						
						textFieldName.setText("");
						
					}	
					
				}
				
				
			}
			
		

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		textFieldMin.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
				if(e.getSource() == textFieldMin) {
					
					char p = e.getKeyChar();
					
					if(controller.controlloField(p) == 1) {
						
						textFieldMin.setText("");
						
					}	
					
				}
				
				
			}
			
		

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		textFieldMax.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getSource() == textFieldMax) {
					
					char p = e.getKeyChar();
					
					if(controller.controlloField(p ) == 1) {
						
						textFieldMax.setText("");
						
					}	
					
				}

				
			}
			
		

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		textFieldTheme.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
				if(e.getSource() == textFieldTheme) {
					
					char p = e.getKeyChar();
					
					if(controller.controlloField(p ) == 1) {
						
						textFieldTheme.setText("");
						
					}	
					
				}
				

				
			}
			
		

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		textAreaDescrizione.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
				if(e.getSource() == textAreaDescrizione) {
					
					char p = e.getKeyChar();
					
					if(controller.controlloField(p) == 1) {
						
						textAreaDescrizione.setText("");
						
					}	
					
				}
				
			}
			
		

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		List<String> lista = controller.getConnectionDao().getAreaTematicaDao().getThemes(controller.getConnectionDao().getConnection());
		Iterator<String> listIt = lista.listIterator();
		
		while(listIt.hasNext()) {
			choiceOption.addItem(listIt.next());
		}

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
