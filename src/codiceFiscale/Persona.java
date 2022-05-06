package codiceFiscale;

public class Persona {
	private String nome;
	private String cognome;
	private String data;
	private String sesso;
	private String comune;
	private String CF;
	private Boolean valid;
	
	public Persona(String nome, String cognome, String data, String sesso, String comune) {
		this.nome = nome;
		this.cognome = cognome;
		this.data = data;
		this.sesso = sesso;
		this.comune = comune;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getData() {
		return data;
	}

	public String getSesso() {
		return sesso;
	}

	public String getComune() {
		return comune;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		CF = cF;
	}

	public Boolean isValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
}
