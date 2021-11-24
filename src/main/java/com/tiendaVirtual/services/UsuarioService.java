package com.tiendaVirtual.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaVirtual.models.Usuario;
import com.tiendaVirtual.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> obtenerUsuarios() {
		return usuarioRepository.findAll();
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
	
}
