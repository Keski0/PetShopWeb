package it.betacom.dao;

import java.util.ArrayList;

import it.betacom.entity.Cliente;

public interface ClienteDao {

	ArrayList<Cliente> getAllClienti();
	Cliente getClienteById(int id);
	void insertCliente(Cliente cliente);
	void deleteCliente(Cliente cliente);
	void updateCliente(Cliente cliente);
	
}
