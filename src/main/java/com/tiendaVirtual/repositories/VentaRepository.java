package com.tiendaVirtual.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tiendaVirtual.models.Producto;
import com.tiendaVirtual.models.Venta;

@Repository
public interface VentaRepository extends CrudRepository<Venta,Long>{
	List<Venta> findAll();
	
	@Query("Select v From Venta v Where usuario_id = ?1")
	List<Venta> obtenerVentaProductos(Long usuario_id);
	
	@Query(value="SELECT productos FROM ventas Where usuario_id =?1", nativeQuery = true)
	List<Producto> obtenerProductosUsuario(Long usuario_id);
}
