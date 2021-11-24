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
		List<Usuario> lista;
		lista = usuarioService.obtenerUsuarios();
		System.out.println(lista.size());
		if(lista.size() == 0) {
			crearUsuarios();
		}
		
		model.addAttribute("listaUsuarios", usuarioService.obtenerUsuarios());
		
		return "usuario.jsp";
	}
	
	//Crear Usuario
	@RequestMapping("/create")
	public String login(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
			if(validar(usuario)) {
				usuarioService.insertarUsuario(usuario);
				return  "redirect:/usuario";
			}			
			
			
			model.addAttribute("error", "Error al crear Usuario, campos vacios");
			return "usuario.jsp";
	}
	
	public Boolean validar(Usuario usuario) {
		if(usuario.getNombre().isEmpty() || usuario.getApellido().isEmpty() || usuario.getCorreo().isEmpty() || usuario.getCodigoPostal().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
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
	
	@RequestMapping("/actualizar")
	public String actualizar(@RequestParam(value="id") Long id,@Valid @ModelAttribute("usuario") Usuario usuario){
		Usuario existente = usuarioService.encontrarUsuario(id);
		if(existente != null && validar(usuario)) {
			usuarioService.insertarUsuario(usuario);
			return "redirect:/usuario";
		}
		return "redirect:/usuario";
	}
	
	//Crear Usuarios utilizando Arraylist para almacenar los objetos
	public void crearUsuarios() {
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario usuario1 = new Usuario("Maria", "Ternero", "maria.Ternero@correo.cl", "99992222");
		Usuario usuario2 = new Usuario("Sebastian", "Robles", "Seba.robles@mail.com", "22221111");
		Usuario usuario3 = new Usuario("Luca", "Zapata", "Luca.zapata@correo.es", "55556666");
		
		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);
		listaUsuarios.add(usuario3);
		
		for(int i =0; i < listaUsuarios.size();i++) {
			usuarioService.insertarUsuario(listaUsuarios.get(i));
			
		}
	}
	
	

}
