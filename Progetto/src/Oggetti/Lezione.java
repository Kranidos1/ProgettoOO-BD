package Oggetti;

import java.util.Date;

public class Lezione {
	private String titolo;
	private String descrizione;
	private int durata;
	private String data;
	private String oraInizio;
	private int CorsoId;
	
	
	public int getCorsoId() {
		return CorsoId;
	}
	public void setCorsoId(int corsoId) {
		CorsoId = corsoId;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public String getData() {
		return data;
	}
	public void setData(String string) {
		this.data = string;
	}
	public String getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(String oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	
}
