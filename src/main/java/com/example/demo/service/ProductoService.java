package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Producto;

public interface ProductoService {
	
	//primero creamos la interface e implementacio esto sale de la otra productorepository

	void guardar(Producto producto);
	void actualizar(Producto producto);
	void eliminar(Integer id);
	List<Producto> listar();
	Producto obtener(Integer id);
	
	
}
