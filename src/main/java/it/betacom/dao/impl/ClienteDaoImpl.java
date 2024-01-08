package it.betacom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.betacom.dao.ClienteDao;
import it.betacom.entity.Cliente;
import it.betacom.service.Connessione;

public class ClienteDaoImpl implements ClienteDao {

	private Connection con;
	private Statement stm;
	
	public ClienteDaoImpl(Connessione connessione) {
		con = connessione.getConnection();
		stm = connessione.getStatement();
	}
	
	@Override
	public ArrayList<Cliente> getAllClienti() {

		ArrayList<Cliente> clienti = new ArrayList<Cliente>();
		ResultSet rs;
		try {
			rs = stm.executeQuery("SELECT * FROM cliente;");
			while (rs.next())
			{
				int idCliente = rs.getInt("idCliente");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String indirizzo = rs.getString("indirizzo");
				String citta = rs.getString("citta");
				String telefono = rs.getString("telefono");
				clienti.add(new Cliente(idCliente, nome, cognome, indirizzo, citta, telefono));
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getAllClienti(): " + e.getMessage());
		}
		
		return clienti;

	}

	@Override
	public Cliente getClienteById(int id) {

		Cliente cliente = null;
		ResultSet rs;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM cliente WHERE idCliente=?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			while (rs.next())
			{
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String indirizzo = rs.getString("indirizzo");
				String citta = rs.getString("citta");
				String telefono = rs.getString("telefono");
				cliente = new Cliente(id, nome, cognome, indirizzo, citta, telefono);
			}
		} catch (SQLException e) {
			System.out.println("Errore nella query di getClienteById(): " + e.getMessage());
		}

		return cliente;
		
	}

	@Override
	public void insertCliente(Cliente cliente) {

		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (nome, cognome, indirizzo, citta, telefono) "
					+ "VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCognome());
			ps.setString(3, cliente.getIndirizzo());
			ps.setString(4, cliente.getCitta());
			ps.setString(5, cliente.getTelefono());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query di insertCliente(): " + e.getMessage());
		}

	}

	@Override
	public void deleteCliente(Cliente cliente) {
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM cliente WHERE idCliente=?");
			ps.setInt(1, cliente.getIdCliente());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query deleteCliente(): " + e.getMessage());
		}

	}

	@Override
	public void updateCliente(Cliente cliente) {
		
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE cliente SET nome=?, cognome=?, indirizzo=?, citta=?, telefono=? WHERE idCliente=?");
			
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCognome());
			ps.setString(3, cliente.getIndirizzo());
			ps.setString(4, cliente.getCitta());
			ps.setString(5, cliente.getTelefono());
			ps.setInt(6, cliente.getIdCliente());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore nella query updateCliente(): " + e.getMessage());
		}
		
	}

}
