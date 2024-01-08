<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Login</title>
</head>
<body>
	<h1>Pet Shop Login</h1>

	<form action = "Login" method = "post">
		Username: <input type = "text" name = "username"/> <br/> <br/>
		Password: <input type = "password" name = "password"/> <br/> <br/>
		<input type = "submit" value = "Accedi"/>
	</form>
	<br/>
	<%
		if(session.getAttribute("risultato") != null) {
			
			if((int)session.getAttribute("risultato") == -1) {
				out.println("<p style='color: red'>Non esiste nessun utente con questo username.</p><br/>");
			}
			else
			{
				if((int)session.getAttribute("risultato") == -2) {
					out.println("<p style='color: red'>Hai sbagliato troppe volte la password. L'utente è stato disabilitato.</p><br/>");
				}
				else
				{
					int tentativi_rimanenti = (int)session.getAttribute("tentativi_rimanenti");
					out.println("<p style='color: red'>Password errata. Tentativi rimanenti: " + tentativi_rimanenti + ".</p><br/>");
				}
			}
			
		}
	%>
	<a href="registrazione.jsp">Non sei ancora registrato? Clicca qui per registrarti.</a>
</body>
</html>