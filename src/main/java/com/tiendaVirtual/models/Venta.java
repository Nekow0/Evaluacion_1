package com.tiendaVirtual.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {
	
	@Id //clave primaria o PK
	@GeneratedValue(strategy= GenerationType.IDENTITY)//auto incrementable
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;

	//Constructores
	
	public Venta() {
		super();
	}
	
	public Venta(Usuario usuario, Producto producto) {
		super();
		this.usuario = usuario;
		this.producto = producto;
	}

	//Getter and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}


}
