<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Shop Registrazione</title>
</head>
<body>
	<h1>Form di registrazione</h1>

	<form action = "Registrazione" method = "post">
		Nome: <input type = "text" name = "nome"/> <br/> <br/>
		Cognome: <input type = "text" name = "cognome"/> <br/> <br/>
		Email: <input type = "email" name = "email"/> <br/> <br/>
		Cellulare: <input type = "tel" name = "cellulare"/> <br/> <br/>
		Data di nascita: <input type = "date" name = "dataNascita"/> <br/> <br/>
		Password: <input type = "password" name = "password"/> <br/> <br/>
		<input type = "submit" value = "Registrati"/>
	</form>
</body>
</html>