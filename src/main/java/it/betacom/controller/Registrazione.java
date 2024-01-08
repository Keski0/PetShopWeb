package it.betacom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.betacom.service.Operations;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet(description = "Servlet di registrazione", urlPatterns = { "/Registrazione" })
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean inserted = registra(request);
		
		if(inserted) {
			Operations.logger.info("Registrazione effettuata per l'utente " + request.getParameter("nome") + " " + request.getParameter("cognome"));
			response.sendRedirect("login.jsp");
		}
		else
		{
			System.out.println("Errore nella registrazione dell'utente.");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean registra(HttpServletRequest request) {
		
		boolean esito = false;
		
		Operations op = new Operations();
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String cellulare = request.getParameter("cellulare");
		String dataNascita = request.getParameter("dataNascita");
		String password = request.getParameter("password");
		
		esito = op.registraUtente(nome, cognome, email, cellulare, dataNascita, password);
		
		op.closeConnection();
		
		return esito;
		
	}

}
