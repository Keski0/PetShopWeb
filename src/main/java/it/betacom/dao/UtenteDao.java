package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.entity.Utente;

public interface UtenteDao {

	ArrayList<Utente> getAllUtenti();
	Utente getUtenteById(int id);
	Utente getUtenteByUsername(String username);
	boolean insertUtente(Utente utente);
	void deleteUtente(Utente utente);
	void updateUtente(Utente utente);
	void updateRuoloById(int id, String ruolo);
	void updateStatoById(int id, String stato);
	void updateDatiById(int id, String email, String cellulare, String password);
	
}
