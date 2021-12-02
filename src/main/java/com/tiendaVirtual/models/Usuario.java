package com.tiendaVirtual.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)//auto incrementable
	private Long id;
	private String nombre;
	private String apellido;
	private String correo;
	private String codigoPostal;
	
	private String password;
	@Transient
	private String passwordConfirmation;
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private List<Venta> ventas;
	
	@ManyToMany(fetch = FetchType.LAZY)
	/*@JoinTable{
		name="usuarios_roles",
		joinColumns = @JoinColumn(name="usuario_id"),
		inverseJoinColumns = @JoinColumn(name="role_id")
	}*/
	@JoinTable(
		name="usuarios_roles",
		joinColumns = @JoinColumn(name="usuario_id"),
		inverseJoinColumns = @JoinColumn(name="role_id")
	)
	private List<Role> roles;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;
	//Constructores
	
	public Usuario() {
		super();
	}

	public Usuario(String nombre, String apellido, String correo, String codigoPostal, String password,
			String passwordConfirmation) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.codigoPostal = codigoPostal;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

	public Usuario(String nombre, String apellido, String correo, String codigoPostal) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.codigoPostal = codigoPostal;
	}

	
	public Usuario(String nombre, String apellido, String correo, String codigoPostal, 
			String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.codigoPostal = codigoPostal;
		this.password = password;
	}

	//Getters and Setters
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
}
