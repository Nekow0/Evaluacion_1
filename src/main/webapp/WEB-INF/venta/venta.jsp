

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
	
	<jsp:include page='../template/nav_loggedIn.jsp'/>

	<div class="container">
		<h3>Mi carrito</h3>
		<form action="/tienda">
            <input type="hidden" name="id" value="${usuario.getId()}">

            <button class="btn btn-dark mb-3" type="submit" > Seguir comprando </button>
          </form>


	<table class="table">
		<thead>
		  <tr>

			<th scope="col">Nombre</th>
			<th scope="col">Categoria</th>
			<th scope="col">Precio</th>
			<th scope="col">Agregar</th>
		  </tr>
		</thead>
		<tbody>
			<c:forEach items="${listaVentas}" var="venta" >
				<tr>
				
				  <td>${venta.getProducto().getNombre()}</td>
				  <td>${venta.getProducto().getCategoria()}</td>
				  <td> $ ${venta.getProducto().getPrecio()}</td>
				  <td>
					<form action="/venta/eliminar">
                        <input type="hidden" name="id" value="${venta.getId()}">

						
						<button class="btn btn-dark mb-3" type="submit" > Eliminar del carrito </button>
					  </form>
				  </td>
				 
				</tr>
			</c:forEach>
		</tbody>
	  </table>
	  <h4>Total: <c:out value="${total}"/></h4>
	</div>
</body>

</html>