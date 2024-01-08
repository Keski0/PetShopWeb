<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.betacom.entity.*,it.betacom.dao.ClienteDao,it.betacom.dao.impl.ClienteDaoImpl,it.betacom.service.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Clienti</title>
</head>
<body>

	<h1>Lista dei clienti</h1>
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
		ClienteDao clienteDao = new ClienteDaoImpl(op.getConnessione());
		List<Cliente> lista = clienteDao.getAllClienti();
		
		Utente utente_loggato = (Utente)session.getAttribute("utente_loggato");
		
		out.println("<p>Utente Loggato: "+utente_loggato.getNome()+" "+utente_loggato.getCognome()+", Ruolo: "+utente_loggato.getRuolo()+"</p>");
		out.println("<form action=\"ListaUtenti.jsp\" method=\"post\"><button type=\"submit\" name=\"action\" value=\"logout\">Logout</button></form><br/>");
		
		out.println("<table border=\"1\" width=\"90%\">");
		out.println("<tr><th>IdCliente</th><th>Nome</th><th>Cognome</th><th>Indirizzo</th><th>Città</th><th>Telefono</th><th>Acquisti</th></tr>");
		for(Cliente c : lista) {
			out.println("<tr><td>"+c.getIdCliente()+"</td><td>"+c.getNome()+"</td><td>"+c.getCognome()+"</td><td>"+c.getIndirizzo()+"</td><td>"+c.getCitta()+"</td><td>"+c.getTelefono()+"</td><td><form action=\"animaliCliente.jsp\" method=\"post\"><input type=\"hidden\" name=\"idCliente\" value=\""+c.getIdCliente()+"\"><button type=\"submit\" name=\"action\" value=\"visualizzaAcquisti\">Visualizza Acquisti</button></form></td></tr>");
		}
		out.println("</table>");
		out.println("<br/><a href=\"ListaUtenti.jsp\">Visualizza la lista degli utenti.</a>");
		out.println("<br/><a href=\"animali.jsp\">Visualizza la lista di tutti gli animali.</a>");
		
	}
	
	%>

</body>
</html>