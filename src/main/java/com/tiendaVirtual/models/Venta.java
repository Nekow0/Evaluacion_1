package com.tiendaVirtual.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {
	
	@Id //clave primaria o PK
	@GeneratedValue(strategy= GenerationType.IDENTITY)//auto incrementable
	private Long id;
	private String nombreUsuario;
	private String productoComprado;
	private String totalCompra;
	
	//Constructores
	
	public Venta() {
		super();
	}
	
	
	public Venta(String nombreUsuario, String productoComprado, String totalCompra) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.productoComprado = productoComprado;
		this.totalCompra = totalCompra;
	}


	public Venta(Long id, String nombreUsuario, String productoComprado, String totalCompra) {
		super();
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.productoComprado = productoComprado;
		this.totalCompra = totalCompra;
	}

	//Getter and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getProductoComprado() {
		return productoComprado;
	}
	public void setProductoComprado(String productoComprado) {
		this.productoComprado = productoComprado;
	}
	public String getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(String totalCompra) {
		this.totalCompra = totalCompra;
	}

}
