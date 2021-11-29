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
<style>
    .center{
        text-align: center;
    }
</style>
<body>
	
        <jsp:include page='template/nav_outside.jsp'/>
        <div class="container">
            <br>
        <H2>Pagina Principal Ventas</H2>
        <br>
        <div class="center">
        Usted no ha iniciado sesion. <br>
        <a class="btn btn-primary" href="/login" role="button">Iniciar sesion</a>
        <a class="btn btn-primary" href="/crearUsuario" role="button">Registrarse</a>
    </div>
    </div>
    

	

</body>

</html>