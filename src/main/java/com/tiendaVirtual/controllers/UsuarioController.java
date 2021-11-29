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
@RequestMapping("")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService;
	
	//Pagina Inicial:
	@RequestMapping("/usuario")
	public String usuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
		model.addAttribute("usuario", new Usuario());	
		List<Usuario> lista;
		lista = usuarioService.obtenerUsuarios();
		System.out.println(lista.size());
		if(lista.size() == 0) {
			crearUsuarios();
		}
		
		model.addAttribute("listaUsuarios", usuarioService.obtenerUsuarios());
		
		return "usuario/usuario.jsp";
	}
	@RequestMapping("/crearUsuario")
	public String crearUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
		model.addAttribute("usuario", new Usuario());	
		List<Usuario> lista;
		lista = usuarioService.obtenerUsuarios();
		System.out.println(lista.size());
		if(lista.size() == 0) {
			crearUsuarios();
		}
		
		model.addAttribute("listaUsuarios", usuarioService.obtenerUsuarios());
		
		return "usuario/crearUsuario.jsp";
	}
	
	//Crear Usuario
	@RequestMapping("/usuario/create")
	public String create(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
			if(validar(usuario)) {
				usuarioService.insertarUsuario(usuario);
				return  "redirect:/usuario";
			}			
			
			
			model.addAttribute("error", "Error al crear Usuario, campos vacios");
			return "usuario/usuario.jsp";
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
	@RequestMapping("/usuario/eliminar")
	public String eliminarUsuario(@RequestParam("id") Long id) {
		if(usuarioService.encontrarUsuario(id) != null) {
			usuarioService.eliminarUsuario(id);
		}
		return "redirect:/usuario";
	}
	@RequestMapping("/login")
	public String logIn(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario/login.jsp";
	}
	
	@RequestMapping("/usuario/login")
	public String verificarCredenciales(@ModelAttribute("usuario") Usuario usuario, Model model) {
			
		System.out.println("Usuario" + usuario.getUsername() + " Contrasena " + usuario.getPassword());
		List<Object[]> credenciales = usuarioService.verificarCredenciales(usuario.getUsername(),  usuario.getPassword());
		for(Object[] fila:credenciales ) {
			if(usuario.getUsername().compareTo(fila[0].toString()) == 0 && usuario.getPassword().compareTo(fila[1].toString()) == 0) {
				
				model.addAttribute("usuario", usuarioService.encontrarUsuario((Long)fila[2]));
				return "redirect:/tienda?id=" + fila[2].toString();
			}
		}
		
		return "redirect:/login";
	}
	
	//Editar Usuario
	@RequestMapping("/usuario/editar")
	public String editar(@RequestParam("id") Long id, Model model) {
		Usuario usuario = usuarioService.encontrarUsuario(id);
		
		
		if(usuario != null) {
			
			model.addAttribute("usuario", usuario);
			return "usuario/editUsuario.jsp";
		}
		else {
			return "redirect:/usuario";
		}
		
		//editarUsuario(usuarioService.encontrarUsuario(id));
	}
	
	@RequestMapping("/usuario/actualizar")
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
		Usuario usuario1 = new Usuario("Maria", "Ternero", "maria.Ternero@correo.cl", "99992222", "maria123", "contraseña123");
		Usuario usuario2 = new Usuario("Sebastian", "Robles", "Seba.robles@mail.com", "22221111", "user", "admin");
		Usuario usuario3 = new Usuario("Luca", "Zapata", "Luca.zapata@correo.es", "55556666", "usuario", "1234");
		
		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);
		listaUsuarios.add(usuario3);
		
		for(int i =0; i < listaUsuarios.size();i++) {
			usuarioService.insertarUsuario(listaUsuarios.get(i));
			
		}
	}
	
	

}
