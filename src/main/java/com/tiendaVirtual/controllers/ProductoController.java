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
import com.tiendaVirtual.models.Venta;
import com.tiendaVirtual.services.ProductoService;
import com.tiendaVirtual.services.UsuarioService;
import com.tiendaVirtual.services.VentaService;

@Controller
@RequestMapping("")
public class ProductoController {
	@Autowired
	ProductoService productoService;

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	VentaService ventaService;
	
	@RequestMapping("/tienda")
	public String tienda(@RequestParam("id") Long id, Model model) {
		List<Producto> lista;
		lista = productoService.obtenerTodoLista();
		if (lista.size() == 0) {
			crearProductos();
		}
		model.addAttribute("usuario", usuarioService.encontrarUsuario(id));
		model.addAttribute("listaProductos", productoService.obtenerTodoLista());
		return "producto/verTienda.jsp";
	}
	
	@RequestMapping("/buscarCategoria")
	public String buscarCategoria(@RequestParam("id") Long id,@RequestParam("categoria") String categoria, Model model) {
		System.out.println(categoria + "es lo que se va a buscar");
		model.addAttribute("listaProductos", productoService.obtenerListaCategoria(categoria));
		model.addAttribute("usuario", usuarioService.encontrarUsuario(id));
		return "producto/verTienda.jsp";
		
	}
	
	@RequestMapping("/producto/agregar")
	public String agregarCarrito(@RequestParam("id_producto") Long id_producto,@RequestParam("id_usuario") Long id_usuario, Model model){
		System.out.println( id_producto + " es el id producto");
		System.out.println( id_usuario + " es el id usuario");
		ventaService.insertarVenta(new Venta(usuarioService.encontrarUsuario(id_usuario), productoService.encontrarProducto(id_producto) ));
		model.addAttribute("listaProductos", productoService.obtenerTodoLista());
		
		return "redirect:/tienda?id="+id_usuario;
	}
	// Formulario Producto
	@RequestMapping("/producto")
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
	@RequestMapping("/producto/create")
	public String login(@Valid @ModelAttribute("producto") Producto producto, Model model) {
		if(validar(producto)) {
			productoService.insertarProducto(producto);
			return "redirect:/producto";
		}
		model.addAttribute("error", "Error al crear Producto, Campos vacios");
		return "producto.jsp";
	}
	
	public Boolean validar(Producto producto) {
		if(producto.getNombre().isEmpty() || producto.getPrecio().isEmpty() || producto.getCategoria().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
		
	}

	// eliminar Producto
	@RequestMapping("/producto/eliminar")
	public String eliminarProducto(@RequestParam("id") Long id) {
		if (productoService.encontrarProducto(id) != null) {
			productoService.eliminarProducto(id);
		}
		return "redirect:/producto";
	}

	// Editar Producto
	@RequestMapping("/producto/editar")
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
	
	@RequestMapping("/producto/actualizar")
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
		Producto producto2 = new Producto("Manzanas", "Comida", "1100");
		Producto producto3 = new Producto("Computador", "Electronica", "899900");
		Producto producto4 = new Producto("Shampoo", "Belleza", "4300");
		Producto producto5 = new Producto("Avena", "Comida", "3000");
		Producto producto6 = new Producto("Celular #21", "Electronica", "300000");
		Producto producto7 = new Producto("Jabon", "Belleza", "690");
		Producto producto8 = new Producto("Jugo de naranja", "Alimento", "1000");
		Producto producto9 = new Producto("Harina", "Comida", "2000");
		listaProductos.add(producto1);
		listaProductos.add(producto2);
		listaProductos.add(producto3);
		listaProductos.add(producto4);
		listaProductos.add(producto5);
		listaProductos.add(producto6);
		listaProductos.add(producto7);
		listaProductos.add(producto8);
		listaProductos.add(producto9);
		for (int i = 0; i < listaProductos.size(); i++) {
			productoService.insertarProducto(listaProductos.get(i));
		}

	}
}
