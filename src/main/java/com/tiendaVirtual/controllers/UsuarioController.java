package com.tiendaVirtual.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiendaVirtual.models.Usuario;
import com.tiendaVirtual.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	//Pagina Inicial:
	@RequestMapping("")
	public String usuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
		model.addAttribute("usuario", new Usuario());	
		model.addAttribute("listaUsuarios", usuarioService.obtenerUsuarios());
		return "usuario.jsp";
	}
	
	//Crear Usuario
	@RequestMapping("/create")
	public String login(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
			
			
			usuarioService.insertarUsuario(usuario);
			
			return  "redirect:/usuario";
	}
	
	//Eliminar Usuario
	@RequestMapping("/eliminar")
	public String eliminarUsuario(@RequestParam("id") Long id) {
		if(usuarioService.encontrarUsuario(id) != null) {
			usuarioService.eliminarUsuario(id);
		}
		return "redirect:/usuario";
	}
	
	//Editar Usuario
	@RequestMapping("/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		Usuario usuario = usuarioService.encontrarUsuario(id);
		
		
		if(usuario != null) {
			
			model.addAttribute("usuario", usuario);
			return "editUsuario.jsp";
		}
		else {
			return "redirect:/usuario";
		}
		
		//editarUsuario(usuarioService.encontrarUsuario(id));
	}
	
	

}
