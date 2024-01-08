package it.betacom.entity;

public class Cliente {
	
	private int idCliente;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String citta;
	private String telefono;
	
	public Cliente() {
		super();
	}

	public Cliente(int idCliente, String nome, String cognome, String indirizzo, String citta, String telefono) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.telefono = telefono;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo="
				+ indirizzo + ", citta=" + citta + ", telefono=" + telefono + "]";
	}

}
