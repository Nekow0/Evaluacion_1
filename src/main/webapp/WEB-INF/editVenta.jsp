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

    <div class="container">
		<form:form action="/venta/actualizar" method="POST" modelAttribute="venta">
			<div><form:label path="nombreUsuario">Nombre del Usuario: </form:label>
                <form:input class="form-control" type="text" path="nombreUsuario" /> <br>
        
                </div>
                <div> 
                    <form:label path="productoComprado">Producto Comprado: </form:label>
                    <form:input class="form-control" type="text" path="productoComprado" /> <br>
                </div>
                
                <div>
                    <form:label path="totalCompra">Total Compra: </form:label>
                    <form:input class="form-control" type="text" path="totalCompra" /> <br>
                </div>
			
			<button  class="btn btn-warning mb-3" value="limpiar"> Limpiar </button>
			<button  class="btn btn-primary mb-3" type="submit" value="enviar"> Enviar </button>
		</form:form>
	</div>

</body>

</html>