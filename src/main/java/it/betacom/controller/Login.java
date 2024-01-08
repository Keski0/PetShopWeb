package it.betacom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.betacom.service.Operations;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Servlet di login", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int ritorno = logga(request.getParameter("username"), request.getParameter("password"), session);
		if(ritorno == 1)
		{
			//successo
			session.setAttribute("username_loggato", request.getParameter("username"));
			response.sendRedirect("ListaUtenti.jsp");
		}
		else
		{
			//fallimento
			session.setAttribute("risultato", ritorno);
			response.sendRedirect("login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private int logga(String username, String password, HttpSession session) {
		
		int ritorno = 0; //-2 disabilitato, -1 utente non esistente, 0 sbagliato, 1 corretto
		
		Operations op = new Operations();
		
		String usernamePrecedente = (String)session.getAttribute("usernamePrecedente"); //se non c'è, sarà null
		session.setAttribute("stato", "A"); //di base, lo stato è A, poi dopo verrà modificato

		if(!op.checkDisabilitato(username)) { //se l'utente non è già disabilitato
			
			//se è la prima volta				o se sono già stati fatti tentativi, ma non con questo username
			if(usernamePrecedente == null || (usernamePrecedente != null && username.compareTo(usernamePrecedente) != 0)) {
				usernamePrecedente = username;
				session.setAttribute("usernamePrecedente", usernamePrecedente);
				session.setAttribute("tentativi_totali", 3);
				session.setAttribute("tentativi_rimanenti", (int)session.getAttribute("tentativi_totali"));
			}
			
			//questo lo fa in ogni caso, anche se sono già stati fatti tentativi con questo username
			ritorno = op.loggaUtente(username, password, usernamePrecedente);
			
			if(ritorno == 0) {
				session.setAttribute("tentativi_rimanenti", ((int)session.getAttribute("tentativi_rimanenti") - 1));
				
				if((int)session.getAttribute("tentativi_rimanenti") == 0) { //3 tentativi effettuati
					
					op.disabilitaUtente(username);
					session.setAttribute("stato", "D");
					ritorno = -2;
				}
			}
			
		}
		else
		{
			session.setAttribute("stato", "D");
			session.setAttribute("usernamePrecedente", username);
			ritorno = -2;
		}
		
		op.closeConnection();
		
		return ritorno;
	}

}
