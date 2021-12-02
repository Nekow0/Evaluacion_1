package com.tiendaVirtual.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaVirtual.models.Usuario;
import com.tiendaVirtual.models.Venta;
import com.tiendaVirtual.services.ProductoService;
import com.tiendaVirtual.services.UsuarioService;
import com.tiendaVirtual.services.VentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	VentaService ventaService;
	
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	
	
	//Pagina Inicial:
	@RequestMapping("")
	public String venta(HttpSession session, @ModelAttribute("venta") Venta venta, Model model) {
		Long id = (Long) session.getAttribute("usuario_id");
		Usuario usuarioActivo = usuarioService.encontrarUsuario(id);
		model.addAttribute("usuario", usuarioActivo);	
		List<Venta> productosUsuario= ventaService.listaProductos(id);
		model.addAttribute("total", calcularTotal(productosUsuario));
		model.addAttribute("listaVentas", productosUsuario);
		
		return "venta/venta.jsp";
	}
	
	public Integer calcularTotal(List<Venta> ventas) {
		Integer total = 0;
		for(Venta venta: ventas) {
			total = total +Integer.parseInt(venta.getProducto().getPrecio());
			
		}
		return total;
		
	}
	/*
	 * //Crear Venta
	 * 
	 * @RequestMapping("/create") public String
	 * login(@Valid @ModelAttribute("venta") Venta venta, Model model) {
	 * 
	 * 
	 * model.addAttribute("error", "Error al registrar venta, campos vacios");
	 * return "venta/venta.jsp"; }
	 */
	/*
	 * public void validar(Venta venta) { if(venta.getNombreUsuario().isEmpty()||
	 * venta.getProductoComprado().isEmpty() || venta.getTotalCompra().isEmpty()) {
	 * return false; } else { return true; } }
	 */
	//Eliminar Venta
	
	@RequestMapping("/eliminar")
	public String eliminarVenta(@RequestParam("id") Long id) {
		String id_usuario = ventaService.encontrarVenta(id).getUsuario().getId().toString();
		if(ventaService.encontrarVenta(id) != null) {
			ventaService.eliminarVenta(id);
		}
		return "redirect:/venta?id=" + id_usuario;
	}

	
	@RequestMapping("/eliminarProducto")
	public String eliminarProducto(@RequestParam("id") Long id) {
		if(ventaService.encontrarVenta(id) != null) {
			ventaService.eliminarVenta(id);
		}
		return "redirect:/venta";
	}
	
	//Editar Venta
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		Venta venta = ventaService.encontrarVenta(id);
		
		
		if(venta != null) {
			
			model.addAttribute("venta", venta);
			return "venta/editVenta.jsp";
		}
		else {
			return "redirect:/venta";
		}
		
		//editarVenta(ventaService.encontrarVenta(id));
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@RequestParam(value="id") Long id,@Valid @ModelAttribute("venta") Venta venta ){
		Venta existente = ventaService.encontrarVenta(id);
		
		
		if(existente != null ) {
			ventaService.insertarVenta(venta);
			return "redirect:/venta";
		}
		return "redirect:/venta";
	}
	
	
}
