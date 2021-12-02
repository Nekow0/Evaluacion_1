package com.tiendaVirtual.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaVirtual.models.Role;
import com.tiendaVirtual.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	RoleRepository roleRepository;
	
	public List<Role> encontrarRol(String nombre) {
		return roleRepository.findByNombre(nombre);
	}

}
