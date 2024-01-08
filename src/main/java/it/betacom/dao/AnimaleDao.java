package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.entity.Animale;

public interface AnimaleDao {

	ArrayList<Animale> getAllAnimali();
	Animale getAnimaleByMatricola(long matricola);
	void insertAnimale(Animale animale);
	void deleteAnimale(Animale animale);
	void updateAnimale(Animale animale);
	void updateDatiByMatricola(long matricola, long nuovaMatricola, String nomeAnimale, float prezzo, String tipoAnimale);
	
}
