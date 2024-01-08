package it.betacom.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import it.betacom.dao.AnimaleDao;
import it.betacom.dao.UtenteDao;
import it.betacom.dao.impl.AnimaleDaoImpl;
import it.betacom.dao.impl.UtenteDaoImpl;
import it.betacom.entity.Animale;
import it.betacom.entity.Utente;

public class Operations {

	private Connessione connessione;
	public static Logger logger = LogManager.getLogger("Operations");
	
	public Operations() {
		connessione = new Connessione();
	}
	
	public boolean registraUtente(String nome, String cognome, String email, String cellulare, String dataNascita, String password) {
		
		boolean esito = false;
		UtenteDao utenteDao = new UtenteDaoImpl(connessione);
		String usernameBase = (nome.substring(0, 2).toUpperCase() + cognome.substring(0, 2).toUpperCase() + LocalDate.parse(dataNascita).getYear());
		String username = usernameBase;
		int count = 1;
		boolean usernameFlag = false;
		
		try {
			do
			{
				PreparedStatement ps = connessione.getConnection().prepareStatement("SELECT COUNT(id) AS conteggio FROM utente WHERE username=?");
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				rs.next();
				
				if(rs.getInt("conteggio") > 0)
				{
					username = usernameBase.concat("_" + count);
					count++;
				}
				else
				{
					System.out.println("Conteggio: " + rs.getInt("conteggio"));
					usernameFlag = true;
				}
			}while(!usernameFlag);
			
			logger.debug("Creato username " + username + " per l'utente " + nome + " " + cognome);
			Utente utente = new Utente(nome, cognome, email, cellulare, Date.valueOf(dataNascita), password, username);
			esito = utenteDao.insertUtente(utente);			
			
		} catch (SQLException e) {
			System.out.println("Errore nella query di controllo dell'username: " + e.getMessage());
		}
		
		return esito;
		
	}
	
	public int loggaUtente(String username, String password, String usernamePrecedente) {
		
		int esito = 0; //-1 non ci sono utenti con quell'username, 0 fallimento login, 1 successo login
		
		try {
			PreparedStatement ps = connessione.getConnection().prepareStatement("SELECT nome, cognome, password FROM utente WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) { //non ci sono utenti con quell'username
				esito = -1;
				logger.debug("Username inesistente");
			}
			else
			{
				if(rs.getString("password").compareTo(password) != 0) {
					esito = 0;
					logger.debug("Password errata per l'utente " + rs.getString("nome") + " " + rs.getString("cognome"));
				}
				else
				{
					esito = 1;
					logger.debug("Login effettuato per l'utente " + rs.getString("nome") + " " + rs.getString("cognome"));
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Errore nella query di login: " + e.getMessage());
		}
		
		return esito;
		
	}
	
	public void logout(Utente utente) {
		
		logger.debug("Logout effettuato per l'utente " + utente.getNome() + " " + utente.getCognome());
		
	}
	
	public void disabilitaUtente(String username) {
		
		try {
			PreparedStatement ps = connessione.getConnection().prepareStatement("UPDATE utente SET stato='D' WHERE username=?");
			ps.setString(1, username);
			ps.executeUpdate();
			
			checkDisabilitato(username);
		} catch (SQLException e) {
			System.out.println("Errore nella query di disabilitazione dell'utente: " + e.getMessage());
		}
		
	}
	
	public boolean checkDisabilitato(String username) {
		
		boolean esito = false;
		
		try {
			PreparedStatement ps = connessione.getConnection().prepareStatement("SELECT nome, cognome, stato FROM utente WHERE username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()) {
				esito = false;
			}
			else
			{
				if(rs.getString("stato").compareTo("D") == 0) {
					esito = true;
					logger.info("Utente " + rs.getString("nome") + " " + rs.getString("cognome") + " con username " + username + " disabilitato");
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di controllo utente disabilitato: " + e.getMessage());
		}
		
		return esito;
		
	}
	
	public void modificaRuolo(int id, String ruolo) {
		
		UtenteDao utenteDao = new UtenteDaoImpl(connessione);
		utenteDao.updateRuoloById(id, ruolo);
		
	}
	
	public void modificaStato(int id, String stato) {
		
		UtenteDao utenteDao = new UtenteDaoImpl(connessione);
		utenteDao.updateStatoById(id, stato);
		
	}
	
	public void modificaDatiUtente(int id, String email, String cellulare, String password) {
		
		UtenteDao utenteDao = new UtenteDaoImpl(connessione);
		utenteDao.updateDatiById(id, email, cellulare, password);
		
	}
	
	public void modificaDatiAnimale(long matricola, long nuovaMatricola, String nomeAnimale, float prezzo, String tipoAnimale) {
		
		AnimaleDao animaleDao = new AnimaleDaoImpl(connessione);
		animaleDao.updateDatiByMatricola(matricola, nuovaMatricola, nomeAnimale, prezzo, tipoAnimale);
		
	}
	
	public void inserisciAnimale(String matricola, String nomeAnimale, String prezzo, String tipoAnimale) {
		
		AnimaleDao animaleDao = new AnimaleDaoImpl(connessione);
		Animale animale = new Animale(Long.valueOf(matricola), nomeAnimale, Float.valueOf(prezzo), tipoAnimale);
		animaleDao.insertAnimale(animale);
		
	}
	
	public Connessione getConnessione() {
		return connessione;
	}
	
	public void setConnessione(Connessione connessione) {
		this.connessione = connessione;
	}
	
	public void closeConnection() {
		connessione.close();
	}
}
