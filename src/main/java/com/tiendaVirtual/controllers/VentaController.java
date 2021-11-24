package com.tiendaVirtual.controllers;

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
		model.addAttribute("listaVentas", ventaService.obtenerVentas());
		return "venta.jsp";
	}
	
	//Crear Venta
	@RequestMapping("/create")
	public String login(@Valid @ModelAttribute("venta") Venta venta, Model model) {
			
			
			ventaService.insertarVenta(venta);
			
			return  "redirect:/venta";
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
}
