package Oggetti;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Studente {
	private String nome;
	private String cognome;
	private String data;
	private String CF;
	private String dataIscrizione;
	
	
	public String getDataIscrizione() {
		return dataIscrizione;
	}
	public void setDataIscrizione(String dataIscrizione) {
		System.out.println(dataIscrizione);
		this.dataIscrizione = dataIscrizione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		System.out.println(data);
		this.data = data;
	}
	public String getCF() {
		return CF;
	}
	public void setCF(String cF) {
		CF = cF;
	}

}
