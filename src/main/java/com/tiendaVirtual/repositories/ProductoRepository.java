package com.tiendaVirtual.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tiendaVirtual.models.Producto;



@Repository
public interface ProductoRepository extends CrudRepository<Producto,Long>  {

	//List<Usuario> findAll();
	List<Producto> findAll();
	
	@Query("SELECT p FROM Producto p Where categoria =?1")
	List<Producto> buscarProductoCategoria(String categoria);
	
}
