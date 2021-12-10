package Oggetti.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
	

	private CorsoDaoImpl corsoDao;
	private IscrizioneDaoImpl iscrizioneDao;
	private LezioneDaoImpl lezioneDao;
	private StudenteDaoImpl studenteDao;
	private PresenzaDaoImpl presenzaDao;
	private AreaTematicaDaoImpl areaTematicaDao;
	private Connection connection;
	private CorsoETemaDaoImpl corsoTemaDao;
	
	public CorsoETemaDaoImpl getCorsoTemaDao() {
		corsoTemaDao = new CorsoETemaDaoImpl();
		return corsoTemaDao;
	}
	public void setCorsoTemaDao(CorsoETemaDaoImpl corsoTemaDao) {
		this.corsoTemaDao = corsoTemaDao;
	}
	
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public CorsoDaoImpl getCorsoDao() {
		corsoDao = new CorsoDaoImpl();
		return corsoDao;
	}
	public void setCorsoDao(CorsoDaoImpl corsoDao) {
		this.corsoDao = corsoDao;
	}
	public IscrizioneDaoImpl getIscrizioneDao() {
		iscrizioneDao = new IscrizioneDaoImpl();
		return iscrizioneDao;
	}
	public void setIscrizioneDao(IscrizioneDaoImpl iscrizioneDao) {
		this.iscrizioneDao = iscrizioneDao;
	}
	public LezioneDaoImpl getLezioneDao() {
		lezioneDao = new LezioneDaoImpl();
		return lezioneDao;
	}
	public void setLezioneDao(LezioneDaoImpl lezioneDao) {
		this.lezioneDao = lezioneDao;
	}
	public StudenteDaoImpl getStudenteDao() {
		studenteDao = new StudenteDaoImpl();
		return studenteDao;
	}
	public void setStudenteDao(StudenteDaoImpl studenteDao) {
		this.studenteDao = studenteDao;
	}
	public PresenzaDaoImpl getPresenzaDao() {
		presenzaDao = new PresenzaDaoImpl();
		return presenzaDao;
	}
	public void setPresenzaDao(PresenzaDaoImpl presenzaDao) {
		this.presenzaDao = presenzaDao;
	}
	public AreaTematicaDaoImpl getAreaTematicaDao() {
		areaTematicaDao = new AreaTematicaDaoImpl();
		return areaTematicaDao;
	}
	public void setAreaTematicaDao(AreaTematicaDaoImpl areaTematicaDao) {
		this.areaTematicaDao = areaTematicaDao;
	}
	
	public ConnectionDao() {
		

		
	}

	public Connection createConnection() {
		
		corsoDao = new CorsoDaoImpl();
		iscrizioneDao = new IscrizioneDaoImpl();
		lezioneDao = new LezioneDaoImpl();
		studenteDao = new StudenteDaoImpl();
		presenzaDao = new PresenzaDaoImpl();
		areaTematicaDao = new AreaTematicaDaoImpl();
		corsoTemaDao = new CorsoETemaDaoImpl();
		
		String jdbcURL = "jdbc:postgresql://localhost:5432/Progetto";
		String username = "postgres";
		String password = "Pippo200-";
				
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection(jdbcURL,username,password);
			return connection;
			//CONNESSIONE RIUSCITA!

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
}
