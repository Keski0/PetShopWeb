package it.betacom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.betacom.service.Operations;

/**
 * Servlet implementation class InserimentoAnimale
 */
@WebServlet(description = "Servlet di inserimento nuovo animale", urlPatterns = { "/InserimentoAnimale" })
public class InserimentoAnimale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserimentoAnimale() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		inserisci(request);
		
		response.sendRedirect("animali.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void inserisci(HttpServletRequest request) {
		
		Operations op = new Operations();
		
		String matricola = request.getParameter("matricola");
		String nomeAnimale = request.getParameter("nome_animale");
		String prezzo = request.getParameter("prezzo");
		String tipoAnimale = request.getParameter("tipo_animale");
		
		op.inserisciAnimale(matricola, nomeAnimale, prezzo, tipoAnimale);
		
		op.closeConnection();
		
	}

}
