package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String cliente;
	private String celular;
	
	//la relacion que tiene con Producto, 1 a muchos
	//cuando es de muchos a muchos se pone como lista
	//el join table yo decido donde poner en este caso es cliente con producto(se puso)
	//el mappedBy solo lo usamos cuando hay relacion de muchos a muchos o hay llave foranea
	//el ,cascade = {CascadeType.PERSIST,CascadeType.MERGE}) se poner para agilizar
	//las consultas
	@ManyToMany(mappedBy = "clientes", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private List<Producto> productos = new ArrayList<>();
	
	
	//CLIENETE CON ITEMS
	//un cliente tiene muchos items, por eso el array list
	@OneToMany(mappedBy = "cliente")
	private List<Item> items = new ArrayList<>();
	
	
	
	
	
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	
	
}
