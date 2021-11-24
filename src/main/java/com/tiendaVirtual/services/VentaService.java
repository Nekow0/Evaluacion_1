package com.tiendaVirtual.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiendaVirtual.models.Venta;
import com.tiendaVirtual.repositories.VentaRepository;

@Service
public class VentaService {

	@Autowired
	VentaRepository ventaRepository;
	public List<Venta> obtenerVentas() {
		
		return ventaRepository.findAll();
	}

	public void insertarVenta(@Valid Venta venta) {
		ventaRepository.save(venta);
		
	}

	public Venta encontrarVenta(Long id) {
		return ventaRepository.findById(id).get();
	}

	public void eliminarVenta(Long id) {
		ventaRepository.deleteById(id);
		
	}

}
