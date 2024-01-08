<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.betacom.entity.Utente,it.betacom.dao.UtenteDao,it.betacom.dao.impl.UtenteDaoImpl,it.betacom.service.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Lista Utenti</title>
</head>
<body>

	<h1>Lista degli utenti</h1>
	<%
	
	if(session.getAttribute("username_loggato") == null) {
		response.sendRedirect("login.jsp");
	}
	else
	{
		
		Operations op = new Operations();
		UtenteDao utenteDao = new UtenteDaoImpl(op.getConnessione());
		List<Utente> lista = utenteDao.getAllUtenti();
		
		String username_loggato = (String)session.getAttribute("username_loggato");
		Utente utente_loggato = utenteDao.getUtenteByUsername(username_loggato);
		session.setAttribute("utente_loggato", utente_loggato);
		
		out.println("<p>Utente Loggato: "+utente_loggato.getNome()+" "+utente_loggato.getCognome()+", Ruolo: "+utente_loggato.getRuolo()+"</p>");
		out.println("<form action=\"ListaUtenti.jsp\" method=\"post\"><button type=\"submit\" name=\"action\" value=\"logout\">Logout</button></form><br/>");
		
		String action = request.getParameter("action");
		int id = -1;
		if(action != null) {
			
			if(action.equals("logout")) {
				op.logout(utente_loggato);
				session.invalidate();
				response.sendRedirect("login.jsp");
			}
			else {
				id = Integer.valueOf(request.getParameter("idCambio"));
				if(action.equals("rendiManager")) {
					op.modificaRuolo(id, "M");
				}
				else if(action.equals("rendiGuest")) {
					op.modificaRuolo(id, "G");
				}
				else if(action.equals("disabilita")) {
					op.modificaStato(id, "D");
				}
				else if(action.equals("abilita")) {
					op.modificaStato(id, "A");
				}
				else if(action.equals("salvaModifiche")) {
					op.modificaDatiUtente(id, request.getParameter("email"), request.getParameter("cellulare"), request.getParameter("password"));
				}
				
				if(!action.equals("modificaDati")) {
					op.closeConnection();
					response.sendRedirect("ListaUtenti.jsp");
				}
			}
			
		}
		
		out.println("<table border=\"1\" width=\"90%\">");
		if(utente_loggato.getRuolo().compareTo("G") == 0) {
			out.println("<tr><th>Id</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Email</th><th>Cellulare</th><th>Ruolo</th></tr>");
			for(Utente u : lista) {
				out.println("<tr><td>"+u.getId()+"</td><td>"+u.getNome()+"</td><td>"+u.getCognome()+"</td><td>"+u.getDataNascita()+"</td><td>"+u.getEmail()+"</td><td>"+u.getCellulare()+"</td><td>"+u.getRuolo()+"</td></tr>");
			}
		}
		else
		{
			out.println("<tr><th>Id</th><th>Nome</th><th>Cognome</th><th>Data di nascita</th><th>Email</th><th>Cellulare</th><th>Password</th><th>Username</th><th>Ruolo</th><th>Stato</th><th>Modifica Ruolo</th><th>Modifica Stato</th><th>Modifica</th></tr>");
			for(Utente u : lista) {
				String modificaRuolo = null;
				String modificaStato = null;
				String modificaDati = null;
				if(u.getRuolo().compareTo("G") == 0) {
					modificaRuolo = ("<td><form action=\"ListaUtenti.jsp\" method=\"post\"><input type=\"hidden\" name=\"idCambio\" value=\""+u.getId()+"\"><button type=\"submit\" name=\"action\" value=\"rendiManager\">Rendi Manager</button></form></td>");
				}
				else
				{
					modificaRuolo = ("<td><form action=\"ListaUtenti.jsp\" method=\"post\"><input type=\"hidden\" name=\"idCambio\" value=\""+u.getId()+"\"><button type=\"submit\" name=\"action\" value=\"rendiGuest\">Rendi Guest</button></form></td>");
				}
				
				if(u.getStato().compareTo("A") == 0) {
					modificaStato = ("<td><form action=\"ListaUtenti.jsp\" method=\"post\"><input type=\"hidden\" name=\"idCambio\" value=\""+u.getId()+"\"><button type=\"submit\" name=\"action\" value=\"disabilita\">Disabilita</button></form></td>");
				}
				else
				{
					modificaStato = ("<td><form action=\"ListaUtenti.jsp\" method=\"post\"><input type=\"hidden\" name=\"idCambio\" value=\""+u.getId()+"\"><button type=\"submit\" name=\"action\" value=\"abilita\">Abilita</button></form></td>");
				}
				modificaDati = ("<td><form action=\"ListaUtenti.jsp\" method=\"post\"><input type=\"hidden\" name=\"idCambio\" value=\""+u.getId()+"\"><button type=\"submit\" name=\"action\" value=\"modificaDati\">Modifica</button></form></td>");
				
				if(action != null && action.equals("modificaDati") && u.getId() == id) {
					out.println("<tr><td>"+u.getId()+"</td><td>"+u.getNome()+"</td><td>"+u.getCognome()+"</td><td>"+u.getDataNascita()+"</td><td>"+u.getEmail()+"</td><td>"+u.getCellulare()+"</td><td>"+u.getPassword()+"</td><td>"+u.getUsername()+"</td><td>"+u.getRuolo()+"</td><td>"+u.getStato()+"</td>"+modificaRuolo+modificaStato+"<td></td></tr>");
					out.println("<tr><td>"+u.getId()+"</td><td>"+u.getNome()+"</td><td>"+u.getCognome()+"</td><td>"+u.getDataNascita()+"</td><form action=\"ListaUtenti.jsp\" method=\"post\"><td><input type=\"hidden\" name=\"idCambio\" value=\""+u.getId()+"\"><input type=\"email\" name=\"email\"></td><td><input type=\"tel\" name=\"cellulare\"></td><td><input type=\"password\" name=\"password\"></td><td>"+u.getUsername()+"</td><td>"+u.getRuolo()+"</td><td>"+u.getStato()+"</td><td></td><td></td><td><button type=\"submit\" name=\"action\" value=\"salvaModifiche\">Salva Modifiche</button></td></form></tr>");
				}
				else
				{
					out.println("<tr><td>"+u.getId()+"</td><td>"+u.getNome()+"</td><td>"+u.getCognome()+"</td><td>"+u.getDataNascita()+"</td><td>"+u.getEmail()+"</td><td>"+u.getCellulare()+"</td><td>"+u.getPassword()+"</td><td>"+u.getUsername()+"</td><td>"+u.getRuolo()+"</td><td>"+u.getStato()+"</td>"+modificaRuolo+modificaStato+modificaDati+"</tr>");
				}
			}
		}
		out.println("</table>");
		
		if(utente_loggato.getRuolo().compareTo("M") == 0) {
			out.println("<br/><a href=\"clienti.jsp\">Visualizza la lista dei clienti.</a>");
			out.println("<br/><a href=\"animali.jsp\">Visualizza la lista di tutti gli animali.</a>");
		}
		
	}
	
	%>

</body>
</html>