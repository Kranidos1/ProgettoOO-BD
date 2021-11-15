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
public class EmCorsoFrame extends JFrame {

	private JPanel contentPane;
	private GeneralPanel panel;
	
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
		
		DefaultListModel model = new DefaultListModel();
		JList listCorsi = new JList(model);
		listCorsi.setVisibleRowCount(4);
		listCorsi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCorsi.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.BLACK));
		listCorsi.setBounds(10, 131, 481, 260);
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
		buttonElimina.setBounds(65, 402, 147, 37);
		panel.add(buttonElimina);
		
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setForeground(Color.RED);
		buttonModifica.setBorder(new RoundBorderBotton(10));
		buttonModifica.setBackground(Color.WHITE);
		buttonModifica.setBounds(288, 402, 147, 37);
		panel.add(buttonModifica);
		
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
				
//				TODO CREACORSOFRAME con valori preimpostati e messaggio per conferma

			}
		});
	}
}
