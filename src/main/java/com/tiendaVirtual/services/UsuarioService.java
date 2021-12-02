package com.tiendaVirtual.services;

import java.util.List;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tiendaVirtual.models.Role;
import com.tiendaVirtual.models.Usuario;
import com.tiendaVirtual.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	RoleService roleService;
	
	@Autowired
	BCryptPasswordEncoder bcpe;
	
	public void persistirUsuarioRol(Usuario usuario) {
		usuario.setPassword(bcpe.encode(usuario.getPassword()));
		List<Role> rol = roleService.encontrarRol("ROLE_USER");
		usuario.setRoles(rol);
		usuarioRepository.save(usuario);
	}
	
	
	public Usuario encontrarPorCorreo(String correo) {
		return usuarioRepository.findByCorreo(correo);
	}

	// insertar usuario
	public void registroUsuario(Usuario usuario) {

		String passwordHashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());// se hashea la password
		usuario.setPassword(passwordHashed); // se guarda la contraseñña a un hash

		usuarioRepository.save(usuario);
	}

	public boolean loginUsuario(String email, String password) {

		Usuario usuario = usuarioRepository.findByCorreo(email);

		if (usuario == null) {
			return false;
		} else {
			// if(password.equals(usuario.getPassword())) {
			if (BCrypt.checkpw(password, usuario.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}

	public List<Usuario> obtenerUsuarios() {
		return usuarioRepository.findAllUsuarios();
	}

	public void insertarUsuario(@Valid Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public Usuario encontrarUsuario(Long id) {
		return usuarioRepository.findById(id).get();
	}

	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public List<Object[]> verificarCredenciales(String username, String password) {
		return usuarioRepository.obtenerCredencialesUsuario();

	}

}
