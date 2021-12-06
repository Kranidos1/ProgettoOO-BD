
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import java.io.File;
import Frames.Controller;
import Frames.FirstFrame;
import Frames.GestisciLezioniFrame;
import Oggetti.DAO.CorsoDaoImpl;
import Oggetti.DAO.PresenzaDaoImpl;

public class Starter {
//TODO CREA GESTIONETEMI FRAME
	//TODO STATISTICHE STUDENTI TRAMITE CORSO
	//TODO GESTIONE LEZIONI 
	//TODO SPOSTARE CONNECTION DA CONTROLLER A UN ALTRO OGGETTO COLLEGATO ALLE DAO
	//TODO CREALEZIONE E' OVVIAMENTE COLLEGATA A UN CORSO
	
	//GESTITI DUPLICAI STUDENTE,CORSO
	public static void main(String[] args){
//
		new FirstFrame();

	


	}

}
