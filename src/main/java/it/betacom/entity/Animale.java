package it.betacom.entity;

import java.sql.Date;

public class Animale {
	
	private long matricola;
	private String nomeAnimale;
	private Date dataAcquisto;
	private float prezzo;
	private String tipoAnimale;
	private int idCliente;
	
	public Animale() {
		super();
	}

	public Animale(long matricola, String nomeAnimale, Date dataAcquisto, float prezzo, String tipoAnimale,
			int idCliente) {
		super();
		this.matricola = matricola;
		this.nomeAnimale = nomeAnimale;
		this.dataAcquisto = dataAcquisto;
		this.prezzo = prezzo;
		this.tipoAnimale = tipoAnimale;
		this.idCliente = idCliente;
	}

	public Animale(long matricola, String nomeAnimale, float prezzo, String tipoAnimale) {
		super();
		this.matricola = matricola;
		this.nomeAnimale = nomeAnimale;
		this.prezzo = prezzo;
		this.tipoAnimale = tipoAnimale;
	}

	public long getMatricola() {
		return matricola;
	}

	public void setMatricola(long matricola) {
		this.matricola = matricola;
	}

	public String getNomeAnimale() {
		return nomeAnimale;
	}

	public void setNomeAnimale(String nomeAnimale) {
		this.nomeAnimale = nomeAnimale;
	}

	public Date getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public String getTipoAnimale() {
		return tipoAnimale;
	}

	public void setTipoAnimale(String tipoAnimale) {
		this.tipoAnimale = tipoAnimale;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Animale [matricola=" + matricola + ", nomeAnimale=" + nomeAnimale + ", dataAcquisto=" + dataAcquisto
				+ ", prezzo=" + prezzo + ", tipoAnimale=" + tipoAnimale + ", idCliente=" + idCliente + "]";
	}

}
