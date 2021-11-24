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

import com.tiendaVirtual.models.Venta;
import com.tiendaVirtual.services.VentaService;

@Controller
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	VentaService ventaService;
	
	//Pagina Inicial:
	@RequestMapping("")
	public String venta(@ModelAttribute("venta") Venta venta, Model model) {
		model.addAttribute("venta", new Venta());	
		List<Venta> lista;
		lista = ventaService.obtenerVentas();
		if(lista.size() == 0) {
			crearVentas();
		}
		model.addAttribute("listaVentas", ventaService.obtenerVentas());
		return "venta.jsp";
	}
	
	//Crear Venta
	@RequestMapping("/create")
	public String login(@Valid @ModelAttribute("venta") Venta venta, Model model) {
			
			if (validar(venta)) {
				ventaService.insertarVenta(venta);
				return  "redirect:/venta";
			}
			
			model.addAttribute("error", "Error al registrar venta, campos vacios");
			return "venta.jsp";
	}
	
	public Boolean validar(Venta venta) {
		if(venta.getNombreUsuario().isEmpty()|| venta.getProductoComprado().isEmpty() || venta.getTotalCompra().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	//Eliminar Venta
	@RequestMapping("/eliminar")
	public String eliminarVenta(@RequestParam("id") Long id) {
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
			return "editVenta.jsp";
		}
		else {
			return "redirect:/venta";
		}
		
		//editarVenta(ventaService.encontrarVenta(id));
	}
	
	@RequestMapping("/actualizar")
	public String actualizar(@RequestParam(value="id") Long id,@Valid @ModelAttribute("venta") Venta venta ){
		Venta existente = ventaService.encontrarVenta(id);
		
		
		if(existente != null && validar(venta)) {
			ventaService.insertarVenta(venta);
			return "redirect:/venta";
		}
		return "redirect:/venta";
	}
	
	public void crearVentas() {
		ArrayList<Venta> listaVentas = new ArrayList<Venta>();
		Venta venta1 = new Venta("User.Maria", "Alicate", "4000");
		Venta venta2 = new Venta("User.Seb", "Alcohol Gel", "2100");
		Venta venta3 = new Venta("User.LF", "Esmalte", "400");
		
		listaVentas.add(venta1);
		listaVentas.add(venta2);
		listaVentas.add(venta3);
		
		for(int i = 0; i< listaVentas.size(); i++) {
			ventaService.insertarVenta(listaVentas.get(i));
		}
		
	}
}
