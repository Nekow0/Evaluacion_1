<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Document</title>
</head>

<body>
	<jsp:include page='../template/nav_outside.jsp'/>
	<div class="container">
		<h2>Iniciar sesion</h2>
		<form:form action="/usuario/login" method="post" modelAttribute="usuario">
			<div>
				<form:label path="correo">Correo: </form:label>
				<form:input class="form-control" type="email" path="correo" /> <br>
			</div>
			
			<div>
				<form:label path="password">Contrasena: </form:label>
				<form:input class="form-control" type="password" path="password" /> <br>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<button  class="btn btn-primary mb-3" type="submit" value="enviar"> Iniciar Sesion </button>
		</form:form>
		¿ No tienes una cuenta? <a href="/crearUsuario">Registrate</a>
	</div>
	
	
	

</body>

</html>