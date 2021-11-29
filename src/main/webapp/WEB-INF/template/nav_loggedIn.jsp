<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
                    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
                    crossorigin="anonymous">
                <title>Document</title>
            </head>
            <style type="text/css">
                .leftNav { text-align: end; }
              </style>
            <body>
                <nav class="navbar navbar-dark bg-dark">
                    <div class="container-fluid">
                        <a class="navbar-brand">Tiendita</a>
                        
                        <div class="btn-group" role="group" aria-label="Basic example">
                        
                             <form class="d-flex" action="/tienda">
                                    <input type="hidden" name="id" value="${usuario.getId()}">

                                    <button class="btn btn-dark mb-3" type="submit" value="Seguir comprando">Seguir
                                        comprando</button>
                                </form>
                            
                                <form class="d-flex"  action="/venta">
                                    <input type="hidden" name="id" value="${usuario.getId()}">

                                    <button class="btn btn-dark mb-3" type="submit" value="Seguir comprando">Ver carrito</button>
                                </form>
                            
                            <button class="btn btn-dark mb-3" href="/">Salir</button>
                        
                        </div>
                    </div>
                </nav>

            </body>

            </html>