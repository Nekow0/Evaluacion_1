package com.tiendaVirtual.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	@Id // clave primaria o PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto incrementable
	private Long id;
	private String nombre;
	private String categoria;
	private String precio;

	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
	private List<Venta> ventas;
	
	
	// Constructores
	public Producto() {
		super();
	}

	public Producto(String nombre, String categoria, String precio) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
	}

	// Getter and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

}
