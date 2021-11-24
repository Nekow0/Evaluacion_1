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
	
Creacion Usuario <br>
	
	<div class="container">
		<form:form action="/usuario/create" method="POST" modelAttribute="usuario">
			<div><form:label path="nombre">Nombre: </form:label>
			<form:input class="form-control" type="text" path="nombre" /> <br>
			<c:if test="${errorNombre != '' }">
				<c:out value="${errorNombre}"/><br>
			</c:if> 
			</div>
			<div> 
				<form:label path="apellido">Apellido: </form:label>
				<form:input class="form-control" type="text" path="apellido" /> <br>
				<c:if test="${errorApellido != '' }">
					<c:out value="${errorApellido}"/><br>
				</c:if> 
			</div>
			
			<div>
				<form:label path="correo">Correo: </form:label>
				<form:input class="form-control" type="email" path="correo" /> <br>
				<c:if test="${errorLimite != '' }">
					<c:out value="${errorLimite}"/><br>
				</c:if> 
			</div>
			
			<div>
				<form:label path="codigoPostal">Codigo Postal: </form:label>
				<form:input class="form-control" type="text" path="codigoPostal" /> <br>
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
			<th scope="col">Apellido</th>
			<th scope="col">Correo</th>
			<th scope="col">Codigo postal</th>
			<th scope="col">Editar</th>
			<th scope="col">Borrar</th>
		  </tr>
		</thead>
		<tbody>
			<c:forEach items="${listaUsuarios}" var="usuario" >
				<tr>
				<th scope="row">${usuario.getId()}</th>
				  <td>${usuario.getNombre()}</td>
				  <td>${usuario.getApellido()}</td>
				  <td>${usuario.getCorreo()}</td>
				  <td>${usuario.getCodigoPostal()}</td>
				  <td>
					<form action="/usuario/editar">
						<input type="hidden" name="id" value="${usuario.getId()}">
						<input type="submit" value="Editar">
					  </form>
				  </td>
				  <td>
					  <form action="/usuario/eliminar">
						<input type="hidden" name="id" value="${usuario.getId()}">
						<input type="submit" value="Borrar">
					  </form>
				  </td>
				</tr>
			</c:forEach>
		</tbody>
	  </table>

</body>

</html>