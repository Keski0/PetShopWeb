<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.betacom.entity.*,it.betacom.dao.*,it.betacom.dao.impl.*,it.betacom.service.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Lista Animali</title>
</head>
<body>

	<h1>Lista di tutti gli animali</h1>
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
		
		out.println("<p>Utente Loggato: "+utente_loggato.getNome()+" "+utente_loggato.getCognome()+", Ruolo: "+utente_loggato.getRuolo()+"</p>");
		out.println("<form action=\"ListaUtenti.jsp\" method=\"post\"><button type=\"submit\" name=\"action\" value=\"logout\">Logout</button></form><br/>");
		
		String action = request.getParameter("action");
		long matricola = -1;
		if(action != null) {
			matricola = Long.valueOf(request.getParameter("matricolaCambio"));
			
			if(action.equals("salvaModifiche")) {
				op.modificaDatiAnimale(matricola, Long.valueOf(request.getParameter("nuova_matricola")), request.getParameter("nome_animale"), Float.valueOf(request.getParameter("prezzo")), request.getParameter("tipo_animale"));
			}
			else if(action.equals("eliminaAnimale")) {
				animaleDao.deleteAnimale(animaleDao.getAnimaleByMatricola(matricola));
			}
			
			op.closeConnection();
			response.sendRedirect("animali.jsp");
		}
		
		out.println("<table border=\"1\" width=\"90%\">");
		out.println("<tr><th>Matricola</th><th>Nome Animale</th><th>Data Acquisto</th><th>Prezzo</th><th>Tipo Animale</th><th>IdCliente</th><th>Modifica dati animale</th></tr>");
		for(Animale a : lista) {
			if(a.getIdCliente() == 0) {
				if(action != null && action.equals("modificaAnimale") && a.getMatricola() == matricola) {
					out.println("<tr><td>"+a.getMatricola()+"</td><td>"+a.getNomeAnimale()+"</td><td></td><td>"+a.getPrezzo()+"</td><td>"+a.getTipoAnimale()+"</td><td></td></tr>");
					out.println("<tr><form action=\"animali.jsp\" method=\"post\"><input type=\"hidden\" name=\"matricolaCambio\" value=\""+a.getMatricola()+"\"><td><input type=\"text\" name=\"nuova_matricola\"></td><td><input type=\"text\" name=\"nome_animale\"></td><td></td><td><input type=\"number\" step=\"0.01\" name=\"prezzo\"></td><td><input type=\"text\" name=\"tipo_animale\"></td><td><button type=\"submit\" name=\"action\" value=\"salvaModifiche\">Salva Modifiche</button></td></form></tr>");
				}
				else
				{
					out.println("<tr><td>"+a.getMatricola()+"</td><td>"+a.getNomeAnimale()+"</td><td></td><td>"+a.getPrezzo()+"</td><td>"+a.getTipoAnimale()+"</td><td></td><td><form action=\"animali.jsp\" method=\"post\"><input type=\"hidden\" name=\"matricolaCambio\" value=\""+a.getMatricola()+"\"><button type=\"submit\" name=\"action\" value=\"modificaAnimale\">Modifica</button><button type=\"submit\" name=\"action\" value=\"eliminaAnimale\">Elimina</button></form></td></tr>");
				}
			}
			else
			{
				out.println("<tr><td>"+a.getMatricola()+"</td><td>"+a.getNomeAnimale()+"</td><td>"+a.getDataAcquisto()+"</td><td>"+a.getPrezzo()+"</td><td>"+a.getTipoAnimale()+"</td><td>"+a.getIdCliente()+"</td></tr>");
			}
		}
		out.println("</table>");
		out.println("<br/><a href=\"inserisciAnimale.jsp\">Inserisci un nuovo animale.</a>");
		out.println("<br/><a href=\"clienti.jsp\">Visualizza la lista dei clienti.</a>");
		out.println("<br/><a href=\"ListaUtenti.jsp\">Visualizza la lista degli utenti.</a>");
		
	}
	
	%>

</body>
</html>