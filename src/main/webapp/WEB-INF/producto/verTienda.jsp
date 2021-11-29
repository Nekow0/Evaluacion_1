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
		
        <h2>Bienvenid@ a la tienda</h2>
	<br>

    Buscar por categoria~
    
    <form action="/buscarCategoria">
        <select name="categoria" class="form-select" >
            <option value="Belleza">Belleza</option>
            <option value="Electronica">Electronica</option>
            <option value="Comida">Comida</option>
        </select>
        <input type="hidden" name="id" value="${usuario.getId()}">
        <button class="btn btn-dark mb-3" type="submit" >Buscar por categoria</button>
    </form>

    <br>
        <form action="/venta">
            <input type="hidden" name="id" value="${usuario.getId()}">
            <button class="btn btn-dark mb-3" type="submit">Ver mi carrito!</button>
          </form>
          <br>
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
			<c:forEach items="${listaProductos}" var="producto" >
				<tr>
				
				  <td>${producto.getNombre()}</td>
				  <td>${producto.getCategoria()}</td>
				  <td> $ ${producto.getPrecio()}</td>
				  <td>
					<form action="/producto/agregar">
                        <input type="hidden" name="id_usuario" value="${usuario.getId()}">
						<input type="hidden" name="id_producto" value="${producto.getId()}">
						
                        <button class="btn btn-dark mb-3"  type="submit">Agregar Producto</button>
					  </form>
				  </td>
				 
				</tr>
			</c:forEach>
		</tbody>
	  </table>
    </div>
</body>

</html>