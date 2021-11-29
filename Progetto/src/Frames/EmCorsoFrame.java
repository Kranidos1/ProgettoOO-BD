package Frames;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
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

import Oggetti.DAO.AreaTematicaDaoImpl;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.CorsoETemaDaoImpl;

import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class EmCorsoFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	private Controller controller;
	private int checkK = 0;
	private int checkCat = 0;
	
	public EmCorsoFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EmCorsoFrame.class.getResource("/imgs/lastin.png")));
		
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
		listCorsi.setBounds(10, 154, 481, 260);
		
		CorsoDaoImpl corso = new CorsoDaoImpl();
		
		List<String> corsi = corso.getNomiCorsi(controller.getConnection());
		model.addAll(corsi);
		
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
		buttonElimina.setBounds(120, 425, 83, 26);
		panel.add(buttonElimina);
		
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setForeground(Color.RED);
		buttonModifica.setBorder(new RoundBorderBotton(10));
		buttonModifica.setBackground(Color.WHITE);
		buttonModifica.setBounds(276, 425, 83, 26);
		panel.add(buttonModifica);
		
		setVisible(true);
		
		buttonElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tmp = listCorsi.getSelectedValue().toString();
				int answer = JOptionPane.showConfirmDialog((JFrame) SwingUtilities.getRoot(buttonModifica), "are you sure you want to delete " + tmp + "?", "Confirm", JOptionPane.YES_NO_OPTION);
				//0 true 1 false
				if(answer == 0) {
					//TODO QUI OVVIAMENTE PARTE ANCHE ELIMINAZIONE DAL DB
					
					model.removeElement(tmp);
					corso.deleteCorsoByName(controller.getConnection(), tmp);
				}
			}
		});
		
		buttonModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				TODO CREACORSOFRAME con valori preimpostati e messaggio per e conferma effettiva modifica nel caso
				String tmp = listCorsi.getSelectedValue().toString();
				//creo frame
				List<String> listaValori = corso.getCorso(controller.getConnection(), tmp);
				CorsoETemaDaoImpl temi = new CorsoETemaDaoImpl();
				List<String> listaTemiCorso = temi.getAllThemeOfCorso(controller.getConnection(), listaValori.get(4));
				AreaTematicaDaoImpl areaTematica = new AreaTematicaDaoImpl();
				
				LinkedList<String> listaTemi = areaTematica.getThemes(controller.getConnection());
				
				ModificaCorsoFrame frame = new ModificaCorsoFrame((JFrame) SwingUtilities.getRoot(buttonModifica));
				frame.getNomeField().setText(listaValori.get(0));
				frame.getTextAreaDescrizione().setText(listaValori.get(1));
				frame.getMaxField().setText(listaValori.get(2));
				frame.getMinField().setText(listaValori.get(3));
				frame.setId(listaValori.get(4));
				
				DefaultListModel model = (DefaultListModel) frame.getList().getModel();
				model.addAll(listaTemiCorso);
				
				JComboBox<String> box = frame.getChoiceOption();
				Iterator<String> iterator =  listaTemi.iterator();
				
				while(iterator.hasNext()) {
					
					box.addItem(iterator.next());
					
				}

				
			}
		});
		
	}
}
