package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;


//agregar para conectar a la BD, indica a spring
@Entity
@Table(name = "productos")
public class Producto {
	//1 er
	//la base datos las tabllas  - clase
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	private String nombreProducto;
	private String descripcion;
	private Double precio;
	private Integer stock;
	
	//la relacion que tiene con la tabla proveedor, 1 a 1
	//cuando es de 1 a 1 se pone como objeto
		@OneToOne
		private Proveedor proveedor;
		
		
	//la relacion que tiene con Cliente, 1 a muchos
		//cuando es de muchos a muchos se pone como lista
		//el join table yo decido donde poner en este caso es cliente con producto
		//el "(cascade = {CascadeType.PERSIST,CascadeType.MERGE})" se poner para agilizar
		//las consultas
		@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
		@JoinTable(
					name="producto_cliente",
					joinColumns = @JoinColumn(
							name="id_producto",
							nullable = false,
							unique = true,
							foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto)references productos(id_producto)")
							),
					inverseJoinColumns = @JoinColumn(
							name="id_cliente",
							nullable = false,
							unique = true,
							foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(id_producto)references clientes(id_cliente)")
							)		
		)
		private List<Cliente> clientes = new ArrayList<>();
	
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	

}
