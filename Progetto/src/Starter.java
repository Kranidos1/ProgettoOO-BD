
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Starter {
//
	public static void main(String[] args){
//
//Connection connection = null;
//try {
//	Class.forName("org.postgresql.Driver");
//	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Pippo200-");
//	if(connection != null) {
//		System.out.println("WELL");
//}catch(Exception e) {
//	System.out.println("ciao");
//}
		new FirstFrame();
	}

}