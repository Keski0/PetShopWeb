<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.betacom.entity.*,it.betacom.dao.*,it.betacom.dao.impl.*,it.betacom.service.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Animali Cliente</title>
</head>
<body>

	<%
	
	if(session.getAttribute("utente_loggato") == null) {
		response.sendRedirect("login.jsp");
	}
	else if(((Utente)session.getAttribute("utente_loggato")).getRuolo().compareTo("M") != 0) {
		response.sendRedirect("ListaUtenti.jsp");
	}
	else
	{
		
		Operations op = new Operations();
		AnimaleDao animaleDao = new AnimaleDaoImpl(op.getConnessione());
		List<Animale> lista = animaleDao.getAllAnimali();
		
		Utente utente_loggato = (Utente)session.getAttribute("utente_loggato");
		
		String action = request.getParameter("action");
		int id = -1;
		if(action != null) {
			
			if(action.equals("visualizzaAcquisti")) {
				id = Integer.valueOf(request.getParameter("idCliente"));
			}
			
		}
		
		ClienteDao clienteDao = new ClienteDaoImpl(op.getConnessione());
		Cliente cliente = clienteDao.getClienteById(id);
		
		out.println("<h1>Lista degli animali di " + cliente.getNome() + " " + cliente.getCognome() + ", idCliente: " + cliente.getIdCliente() + "</h1>");
		
		out.println("<p>Utente Loggato: "+utente_loggato.getNome()+" "+utente_loggato.getCognome()+", Ruolo: "+utente_loggato.getRuolo()+"</p>");
		out.println("<form action=\"ListaUtenti.jsp\" method=\"post\"><button type=\"submit\" name=\"action\" value=\"logout\">Logout</button></form><br/>");
		
		out.println("<table border=\"1\" width=\"90%\">");
		out.println("<tr><th>Matricola</th><th>Nome Animale</th><th>Data Acquisto</th><th>Prezzo</th><th>Tipo Animale</th><th>IdCliente</th></tr>");
		for(Animale a : lista) {
			if(a.getIdCliente() == id) {
				out.println("<tr><td>"+a.getMatricola()+"</td><td>"+a.getNomeAnimale()+"</td><td>"+a.getDataAcquisto()+"</td><td>"+a.getPrezzo()+"</td><td>"+a.getTipoAnimale()+"</td><td>"+a.getIdCliente()+"</td></tr>");
			}
		}
		out.println("</table>");
		out.println("<br/><a href=\"clienti.jsp\">Visualizza la lista dei clienti.</a>");
		out.println("<br/><a href=\"ListaUtenti.jsp\">Visualizza la lista degli utenti.</a>");
		out.println("<br/><a href=\"animali.jsp\">Visualizza la lista di tutti gli animali.</a>");
		
	}
	
	%>

</body>
</html>