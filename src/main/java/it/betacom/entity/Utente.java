package it.betacom.entity;

import java.sql.Date;

public class Utente {
	
	private int id;
	private String nome;
	private String cognome;
	private String email;
	private String cellulare;
	private Date dataNascita;
	private String password;
	private String username;
	private String ruolo;
	private String stato;
	
	public Utente() {
		super();
	}

	public Utente(int id, String nome, String cognome, String email, String cellulare, Date dataNascita,
			String password, String username, String ruolo, String stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.cellulare = cellulare;
		this.dataNascita = dataNascita;
		this.password = password;
		this.username = username;
		this.ruolo = ruolo;
		this.stato = stato;
	}

	public Utente(String nome, String cognome, String email, String cellulare, Date dataNascita, String password, String username) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.cellulare = cellulare;
		this.dataNascita = dataNascita;
		this.password = password;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", cellulare="
				+ cellulare + ", dataNascita=" + dataNascita + ", password=" + password + ", username=" + username
				+ ", ruolo=" + ruolo + ", stato=" + stato + "]";
	}
	
}
