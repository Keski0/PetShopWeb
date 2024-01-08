<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="it.betacom.entity.Utente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Inserimento Nuovo Animale</title>
</head>
<body>

	<%
	if(session.getAttribute("utente_loggato") == null) {
		response.sendRedirect("login.jsp");
	}
	else if(((Utente)session.getAttribute("utente_loggato")).getRuolo().compareTo("M") != 0) {
		response.sendRedirect("ListaUtenti.jsp");
	}
	%>
	
	<h1>Form di inserimento del nuovo animale</h1>

	<form action = "InserimentoAnimale" method = "post">
		Matricola: <input type = "text" name = "matricola"/> <br/> <br/>
		Nome: <input type = "text" name = "nome_animale"/> <br/> <br/>
		Prezzo: <input type = "number" step = "0.01" name = "prezzo"/> <br/> <br/>
		Tipo Animale: <input type = "text" name = "tipo_animale"/> <br/> <br/>
		<input type = "submit" value = "Inserisci"/>
	</form>
</body>
</html>