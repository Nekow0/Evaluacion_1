package com.tiendaVirtual.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaVirtual.models.Producto;
import com.tiendaVirtual.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	ProductoService productoService;
	
	//Formulario Producto
	@RequestMapping("")
	public String producto(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", new Producto());	
		model.addAttribute("listaProductos", productoService.obtenerTodoLista());
		return "producto.jsp";
	}
	
	
	//Crear Producto
	@RequestMapping("/create")
	public String login(@Valid @ModelAttribute("producto") Producto producto, Model model) {
			
			
		productoService.insertarProducto(producto);
			
		return  "redirect:/producto";
	}
	//eliminar Producto
	@RequestMapping("/eliminar")
	public String eliminarProducto(@RequestParam("id") Long id) {
		if(productoService.encontrarProducto(id) != null) {
			productoService.eliminarProducto(id);
		}
		return "redirect:/usuario";
	}
	
	//Editar Producto
		@RequestMapping("/editar")
		public String editar(@RequestParam("id") Long id, Model model) {
			Producto producto = productoService.encontrarProducto(id);
			
			
			if(producto != null) {
				
				model.addAttribute("producto", producto);
				return "editProducto.jsp";
			}
			else {
				return "redirect:/producto";
			}
			
			//editarUsuario(usuarioService.encontrarUsuario(id));
		}
}
