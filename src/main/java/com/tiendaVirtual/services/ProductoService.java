package com.tiendaVirtual.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiendaVirtual.models.Producto;
import com.tiendaVirtual.repositories.ProductoRepository;

@Service
public class ProductoService {
	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> obtenerTodoLista() {
		return productoRepository.findAll();
	}
	
	public List<Producto> obtenerListaCategoria(String categoria){
		return productoRepository.buscarProductoCategoria(categoria);
	}

	public void insertarProducto(@Valid Producto producto) {
		productoRepository.save(producto);
	}
	
	public Producto encontrarProducto(Long id) {
		return productoRepository.findById(id).get();
	}
	public void eliminarProducto(Long id) {
		productoRepository.deleteById(id);
	}

}
