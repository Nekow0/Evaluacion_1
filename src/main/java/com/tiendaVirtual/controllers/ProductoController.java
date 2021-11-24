package com.tiendaVirtual.controllers;

import java.util.ArrayList;
import java.util.List;

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

	// Formulario Producto
	@RequestMapping("")
	public String producto(@ModelAttribute("producto") Producto producto, Model model) {
		model.addAttribute("producto", new Producto());
		List<Producto> lista;
		lista = productoService.obtenerTodoLista();
		if (lista.size() == 0) {
			crearProductos();
		}
		model.addAttribute("listaProductos", productoService.obtenerTodoLista());
		return "producto.jsp";
	}

	// Crear Producto
	@RequestMapping("/create")
	public String login(@Valid @ModelAttribute("producto") Producto producto, Model model) {
		if(validar(producto)) {
			productoService.insertarProducto(producto);
			return "redirect:/producto";
		}
		model.addAttribute("error", "Error al crear Producto, Campos vacios");
		return "producto.jsp";
	}
	
	public Boolean validar(Producto producto) {
		if(producto.getNombre().isEmpty() || producto.getPrecio().isEmpty() || producto.getTipo().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
		
	}

	// eliminar Producto
	@RequestMapping("/eliminar")
	public String eliminarProducto(@RequestParam("id") Long id) {
		if (productoService.encontrarProducto(id) != null) {
			productoService.eliminarProducto(id);
		}
		return "redirect:/producto";
	}

	// Editar Producto
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		Producto producto = productoService.encontrarProducto(id);

		if (producto != null) {

			model.addAttribute("producto", producto);
			return "editProducto.jsp";
		} else {
			return "redirect:/producto";
		}

		// editarUsuario(usuarioService.encontrarUsuario(id));
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@RequestParam(value="id") Long id,@Valid @ModelAttribute("producto") Producto producto) {
		Producto existente = productoService.encontrarProducto(id);
		if(existente != null && validar(producto)) {
			productoService.insertarProducto(producto);
			return "redirect:/producto";
		}
		return "redirect:/producto";
	}

	//Crea 3 objetos producto y los almacena en la db estando en un arraylist
	public void crearProductos() {
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		Producto producto1 = new Producto("Peineta", "Belleza", "2000");
		Producto producto2 = new Producto("Manzanas", "Alimento", "1100");
		Producto producto3 = new Producto("Computador", "Electronica", "899900");

		listaProductos.add(producto1);
		listaProductos.add(producto2);
		listaProductos.add(producto3);

		for (int i = 0; i < listaProductos.size(); i++) {
			productoService.insertarProducto(listaProductos.get(i));
		}

	}
}
