
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import Frames.FirstFrame;
import Frames.GestisciLezioniFrame;
import Oggetti.DAO.CorsoDaoImpl;

public class Starter {
//TODO CREA GESTIONETEMI FRAME
	//TODO STATISTICHE STUDENTI TRAMITE CORSO
	//TODO GESTIONE LEZIONI 
	
	public static void main(String[] args){

		new FirstFrame();
//		Controller controller = new Controller();
//		CorsoDaoImpl corso = new CorsoDaoImpl();
//		corso.getNomiCorsiByKey(controller.getConnection(),"ALF" );
		
	}
	
//	listCorsi.addMouseListener(new MouseAdapter() {
//		
//	    public void mouseClicked(MouseEvent evt) {
//	        JList list = (JList) evt.getSource();
//	        if (evt.getClickCount() == 2) {
//
//	            // Double-click detected
//	            int index = listCorsi.locationToIndex(evt.getPoint());
//	            //PRENDE OGGETTO CLICCATO DUE VOLTE
//	        }
//	    }
//	    
//	});

}
