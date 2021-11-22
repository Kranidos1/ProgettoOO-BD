package Frames;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class EmCorsoFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private JTextField categoryField;
	private JTextField keyField;
	private Controller controller;
	private JCheckBox checkKey;
	private JCheckBox checkCategory;
	private JLabel categoryLabel;
	private JLabel keyLabel;
	private int checkK = 0;
	private int checkCat = 0;
	
	public EmCorsoFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\matti\\Desktop\\lastin.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new GeneralPanel();
		getContentPane().add(panel);
		
		controller = new Controller();
		DefaultListModel model = new DefaultListModel();
		JList listCorsi = new JList(model);
		listCorsi.setVisibleRowCount(4);
		listCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCorsi.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
		listCorsi.setBounds(10, 196, 481, 260);
		model.addElement("ciao");
		model.addElement("cia1o");
		model.addElement("cia12o");
		model.addElement("ci3ao");
		model.addElement("cia34o");
		model.addElement("cia454o");
		
		//TODO
		//aggiungere cose
		
		//FUNZIONA SU CLICK E DECIDE COSA MODIFICARE O ELIMINARE
//		listCorsi.addMouseListener(new MouseAdapter() {
//			
		
//		       public void mouseClicked(MouseEvent e) {
//		    	   
//		            if ( e.getClickCount() == 2) {
//		               listCorsi.getsel
//		            }
//		        }
//		       
//		});
		panel.add(listCorsi);
		
		JButton buttonElimina = new JButton("Elimina");
		buttonElimina.setForeground(Color.RED);
		buttonElimina.setBorder(new RoundBorderBotton(10));
		buttonElimina.setBackground(Color.WHITE);
		buttonElimina.setBounds(10, 467, 83, 26);
		panel.add(buttonElimina);
		
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setForeground(Color.RED);
		buttonModifica.setBorder(new RoundBorderBotton(10));
		buttonModifica.setBackground(Color.WHITE);
		buttonModifica.setBounds(132, 467, 83, 26);
		panel.add(buttonModifica);
		
		categoryField = new JTextField();
		categoryField.setEnabled(false);
		categoryField.setColumns(10);
		categoryField.setBounds(69, 127, 119, 20);
		panel.add(categoryField);
		
		keyField = new JTextField();
		keyField.setEnabled(false);
		keyField.setColumns(10);
		keyField.setBounds(69, 165, 119, 20);
		panel.add(keyField);
		
		checkCategory = new JCheckBox("");
		checkCategory.setBackground(Color.WHITE);
		checkCategory.setBounds(194, 127, 21, 17);
		panel.add(checkCategory);
		
		checkKey = new JCheckBox("");
		checkKey.setBackground(Color.WHITE);
		checkKey.setBounds(194, 165, 21, 17);
		panel.add(checkKey);
		
		JButton buttonRicerca = new JButton("Ricerca");
		buttonRicerca.setForeground(Color.RED);
		buttonRicerca.setBorder(new RoundBorderBotton(10));
		buttonRicerca.setBackground(Color.WHITE);
		buttonRicerca.setBounds(241, 138, 75, 26);
		panel.add(buttonRicerca);
		
		categoryLabel = new JLabel("Category");
		categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLabel.setBounds(0, 127, 57, 20);
		panel.add(categoryLabel);
		
		keyLabel = new JLabel("Key");
		keyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keyLabel.setBounds(12, 165, 47, 17);
		panel.add(keyLabel);
		
		setVisible(true);
		
		buttonElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tmp = listCorsi.getSelectedValue().toString();
				int answer = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getRoot(buttonModifica), "are you sure you want to delete " + tmp + "?", "Confirm", JOptionPane.YES_NO_OPTION);
				//0 true 1 false
				if(answer == 0) {
					model.removeElement(tmp);
				}
				//TODO QUI OVVIAMENTE PARTE ANCHE ELIMINAZIONE DAL DB
			}
		});
		
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				TODO CREACORSOFRAME con valori preimpostati e messaggio per e conferma effettiva modifica nel caso

			}
		});
		
		checkCategory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkCat = controller.isEnbl(categoryField, checkCat ,null);
				
			}
			
		});
		
		checkKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkK = controller.isEnbl(keyField, checkK ,null);
				
			}
			
		});
		
		buttonRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
}
