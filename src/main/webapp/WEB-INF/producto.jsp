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
	
Creacion Producto <br>
	
	<div class="container">
		<form:form action="/producto/create" method="POST" modelAttribute="producto">
			<div><form:label path="nombre">Nombre: </form:label>
			<form:input class="form-control" type="text" path="nombre" /> <br>
			<c:if test="${errorNombre != '' }">
				<c:out value="${errorNombre}"/><br>
			</c:if> 
			</div>
			<div> 
				<form:label path="tipo">Tipo: </form:label>
				<form:input class="form-control" type="text" path="tipo" /> <br>
			</div>
			
			<div>
				<form:label path="precio">Precio Producto: </form:label>
				<form:input class="form-control" type="text" path="precio" /> <br>
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
			<th scope="col">Nombre</th>
			<th scope="col">Tipo</th>
			<th scope="col">Precio</th>
			<th scope="col">Editar</th>
			<th scope="col">Borrar</th>
		  </tr>
		</thead>
		<tbody>
			<c:forEach items="${listaProductos}" var="producto" >
				<tr>
				<th scope="row">${producto.getId()}</th>
				  <td>${producto.getNombre()}</td>
				  <td>${producto.getTipo()}</td>
				  <td>${producto.getPrecio()}</td>
				  <td>
					<form action="/producto/editar">
						<input type="hidden" name="id" value="${producto.getId()}">
						<input type="submit" value="Editar">
					  </form>
				  </td>
				  <td>
					  <form action="/producto/eliminar">
						<input type="hidden" name="id" value="${producto.getId()}">
						<input type="submit" value="Borrar">
					  </form>
				  </td>
				</tr>
			</c:forEach>
		</tbody>
	  </table>

</body>

</html>