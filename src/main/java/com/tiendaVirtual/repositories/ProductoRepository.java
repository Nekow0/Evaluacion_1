package com.tiendaVirtual.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.tiendaVirtual.models.Producto;



@Repository
public interface ProductoRepository extends CrudRepository<Producto,Long>  {

	//List<Usuario> findAll();
	List<Producto> findAll();
}
