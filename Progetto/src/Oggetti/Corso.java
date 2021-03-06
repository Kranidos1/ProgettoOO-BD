package Oggetti;
	
public class Corso {
	
	private String nome;
	private String descrizione;
	private Integer MaxPartecipanti;
	private Integer MinPartecipazione;
	private Integer CorsoId;
	private String check;
	
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public void setCorsoId(Integer corsoId) {
		CorsoId = corsoId;
	}

	public Corso() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getMaxPartecipanti() {
		return MaxPartecipanti;
	}

	public void setMaxPartecipanti(Integer maxPartecipanti) {
		MaxPartecipanti = maxPartecipanti;
	}

	public Integer getMinPartecipazione() {
		return MinPartecipazione;
	}

	public void setMinPartecipazione(Integer minPartecipazione) {
		MinPartecipazione = minPartecipazione;
	}
	
	public Integer getCorsoId() {
		return CorsoId;
	}
	
}
