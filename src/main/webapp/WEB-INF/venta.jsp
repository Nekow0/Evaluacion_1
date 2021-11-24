

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
	
Creacion Venta <br>

	<div class="container">
		<form:form action="/venta/create" method="POST" modelAttribute="venta">
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
				<c:if test="${invalid != '' }">
					<c:out value="${invalid}"/>
				</c:if>
			</div>
			
			<button  class="btn btn-warning mb-3" value="limpiar"> Limpiar </button>
			<button  class="btn btn-primary mb-3" type="submit" value="enviar"> Enviar </button>
		</form:form>
	</div>


	<table class="table">
		<thead>
		  <tr>
			<th scope="col">#</th>
			<th scope="col">Nombre Usuario</th>
			<th scope="col">Producto comprado</th>
			<th scope="col">Total de la compra</th>
			<th scope="col">Editar</th>
			<th scope="col">Borrar</th>
		  </tr>
		</thead>
       
		<tbody>
			<c:forEach items="${listaVentas}" var="venta" >
				<tr>
				<th scope="row">${venta.getId()}</th>
				  <td>${venta.getNombreUsuario()}</td>
				  <td>${venta.getProductoComprado()}</td>
				  <td>${venta.getTotalCompra()}</td>
				  <td>
					<form action="/venta/editar">
						<input type="hidden" name="id" value="${venta.getId()}">
						<input type="submit" value="Editar">
					  </form>
				  </td>
				  <td>
					  <form action="/venta/eliminar">
						<input type="hidden" name="id" value="${venta.getId()}">
						<input type="submit" value="Borrar">
					  </form>
				  </td>
				</tr>
			</c:forEach>
		</tbody>
	  </table>

</body>

</html>