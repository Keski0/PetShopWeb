package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.AnimaleDao;
import it.betacom.entity.Animale;
import it.betacom.service.Connessione;

public class AnimaleDaoImpl implements AnimaleDao {

	private Connection con;
	private Statement stm;
	
	public AnimaleDaoImpl(Connessione connessione) {
		con = connessione.getConnection();
		stm = connessione.getStatement();
	}
	
	@Override
	public ArrayList<Animale> getAllAnimali() {

		ArrayList<Animale> animali = new ArrayList<Animale>();
		ResultSet rs;
		try {
			rs = stm.executeQuery("SELECT * FROM animale;");
			while (rs.next())
			{
				long matricola = rs.getLong("matricola");
				String nomeAnimale = rs.getString("nome_animale");
				Date dataAcquisto = rs.getDate("data_acquisto");
				float prezzo = rs.getFloat("prezzo");
				String tipoAnimale = rs.getString("tipo_animale");
				int idCliente = rs.getInt("idCliente");
				animali.add(new Animale(matricola, nomeAnimale, dataAcquisto, prezzo, tipoAnimale, idCliente));
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getAllAnimali(): " + e.getMessage());
		}
		
		return animali;

	}

	@Override
	public Animale getAnimaleByMatricola(long matricola) {

		Animale animale = null;
		ResultSet rs;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM animale WHERE matricola=?");
			ps.setLong(1, matricola);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				String nomeAnimale = rs.getString("nome_animale");
				Date dataAcquisto = rs.getDate("data_acquisto");
				float prezzo = rs.getFloat("prezzo");
				String tipoAnimale = rs.getString("tipo_animale");
				int idCliente = rs.getInt("idCliente");
				animale = new Animale(matricola, nomeAnimale, dataAcquisto, prezzo, tipoAnimale, idCliente);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getAnimaleByMatricola(): " + e.getMessage());
		}

		return animale;
		
	}

	@Override
	public void insertAnimale(Animale animale) {

		try {
			PreparedStatement ps = null;
			
			if(animale.getIdCliente() == 0) {
				ps = con.prepareStatement("INSERT INTO animale (matricola, nome_animale, data_acquisto, prezzo, tipo_animale) "
						+ "VALUES (?, ?, ?, ?, ?)");
				ps.setLong(1, animale.getMatricola());
				ps.setString(2, animale.getNomeAnimale());
				ps.setDate(3, animale.getDataAcquisto());
				ps.setFloat(4, animale.getPrezzo());
				ps.setString(5, animale.getTipoAnimale());
			}
			else
			{
				ps = con.prepareStatement("INSERT INTO animale "
						+ "VALUES (?, ?, ?, ?, ?, ?)");
				ps.setLong(1, animale.getMatricola());
				ps.setString(2, animale.getNomeAnimale());
				ps.setDate(3, animale.getDataAcquisto());
				ps.setFloat(4, animale.getPrezzo());
				ps.setString(5, animale.getTipoAnimale());
				ps.setInt(6, animale.getIdCliente());
			}
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query di insertAnimale(): " + e.getMessage());
		}

	}

	@Override
	public void deleteAnimale(Animale animale) {
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM animale WHERE matricola=?");
			ps.setLong(1, animale.getMatricola());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query deleteAnimale(): " + e.getMessage());
		}

	}

	@Override
	public void updateAnimale(Animale animale) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE animale SET nome=?, data_acquisto=?, prezzo=?, tipo_animale=?, idCliente=? WHERE matricola=?");
			
			ps.setString(1, animale.getNomeAnimale());
			ps.setDate(2, animale.getDataAcquisto());
			ps.setFloat(3, animale.getPrezzo());
			ps.setString(4, animale.getTipoAnimale());
			ps.setInt(5, animale.getIdCliente());
			ps.setLong(6, animale.getMatricola());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateAnimale(): " + e.getMessage());
		}
		
	}
	
	@Override
	public void updateDatiByMatricola(long matricola, long nuovaMatricola, String nomeAnimale, float prezzo, String tipoAnimale) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE animale SET matricola=?, nome_animale=?, prezzo=?, tipo_animale=? WHERE matricola=?");
			
			ps.setLong(1, nuovaMatricola);
			ps.setString(2, nomeAnimale);
			ps.setFloat(3, prezzo);
			ps.setString(4, tipoAnimale);
			ps.setLong(5, matricola);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateDatiByMatricola(): " + e.getMessage());
		}
		
	}

}
