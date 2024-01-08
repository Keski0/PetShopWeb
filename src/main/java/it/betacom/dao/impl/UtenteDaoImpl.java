package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.UtenteDao;
import it.betacom.entity.Utente;
import it.betacom.service.Connessione;

public class UtenteDaoImpl implements UtenteDao {

	private Connection con;
	private Statement stm;
	
	public UtenteDaoImpl(Connessione connessione) {
		con = connessione.getConnection();
		stm = connessione.getStatement();
	}
	
	@Override
	public ArrayList<Utente> getAllUtenti() {

		ArrayList<Utente> utenti = new ArrayList<Utente>();
		ResultSet rs;
		try {
			rs = stm.executeQuery("SELECT * FROM utente;");
			while (rs.next())
			{
				int id_utente = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String cellulare = rs.getString("cellulare");
				Date data_nascita = rs.getDate("data_nascita");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String ruolo = rs.getString("ruolo");
				String stato = rs.getString("stato");
				utenti.add(new Utente(id_utente, nome, cognome, email, cellulare, data_nascita, password, username, ruolo, stato));
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getAllUtenti(): " + e.getMessage());
		}
		
		return utenti;
		
	}

	@Override
	public Utente getUtenteById(int id) {
		
		Utente utente = null;
		ResultSet rs;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE id=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String cellulare = rs.getString("cellulare");
				Date data_nascita = rs.getDate("data_nascita");
				String password = rs.getString("password");
				String username = rs.getString("username");
				String ruolo = rs.getString("ruolo");
				String stato = rs.getString("stato");
				utente = new Utente(id, nome, cognome, email, cellulare, data_nascita, password, username, ruolo, stato);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getUtenteById(): " + e.getMessage());
		}

		return utente;
		
	}
	
	@Override
	public Utente getUtenteByUsername(String username) {
		Utente utente = null;
		
		ResultSet rs;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM utente WHERE username=?");
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				String cellulare = rs.getString("cellulare");
				Date data_nascita = rs.getDate("data_nascita");
				String password = rs.getString("password");
				String ruolo = rs.getString("ruolo");
				String stato = rs.getString("stato");
				utente = new Utente(id, nome, cognome, email, cellulare, data_nascita, password, username, ruolo, stato);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getUtenteByUsername(): " + e.getMessage());
		}
		
		return utente;
	}

	@Override
	public boolean insertUtente(Utente utente) {
		
		boolean inserted = false;
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO utente (nome, cognome, email, cellulare, data_nascita, password, username) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getCellulare());
			ps.setDate(5, utente.getDataNascita());
			ps.setString(6, utente.getPassword());
			ps.setString(7, utente.getUsername());
			
			ps.executeUpdate();
			
			inserted = true;
		} catch (SQLException e) {
			System.out.println("Errore nella query di insertUtente(): " + e.getMessage());
			inserted = false;
		}
		
		return inserted;
		
	}

	@Override
	public void deleteUtente(Utente utente) {

		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM utente WHERE id=?");
			ps.setInt(1, utente.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query deleteUtente(): " + e.getMessage());
		}
		
	}

	@Override
	public void updateUtente(Utente utente) {

		try {
			PreparedStatement ps = con.prepareStatement("UPDATE utente SET nome='?', cognome='?', email='?', cellulare='?', data_nascita=?, password='?', username='?' WHERE id=?");
			
			ps.setString(1, utente.getNome());
			ps.setString(2, utente.getCognome());
			ps.setString(3, utente.getEmail());
			ps.setString(4, utente.getCellulare());
			ps.setDate(5, utente.getDataNascita());
			ps.setString(6, utente.getPassword());
			ps.setString(7, utente.getUsername());
			ps.setInt(8, utente.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateUtente(): " + e.getMessage());
		}
		
	}
	
	@Override
	public void updateRuoloById(int id, String ruolo) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE utente SET ruolo=? WHERE id=?");
			
			ps.setString(1, ruolo);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateRuoloById(): " + e.getMessage());
		}
		
	}
	
	@Override
	public void updateStatoById(int id, String stato) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE utente SET stato=? WHERE id=?");
			
			ps.setString(1, stato);
			ps.setInt(2, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateStatoById(): " + e.getMessage());
		}
		
	}
	
	@Override
	public void updateDatiById(int id, String email, String cellulare, String password) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE utente SET email=?, cellulare=?, password=? WHERE id=?");
			
			ps.setString(1, email);
			ps.setString(2, cellulare);
			ps.setString(3, password);
			ps.setInt(4, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateDatiById(): " + e.getMessage());
		}
		
	}

}
