package com.tiendaVirtual.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tiendaVirtual.models.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
	
	@Query("SELECT u FROM Usuario u ")
	List<Usuario> findAllUsuarios();
	
	@Query("SELECT u.username, u.password, u.id From Usuario u")
	List<Object[]> obtenerCredencialesUsuario();
	
}
