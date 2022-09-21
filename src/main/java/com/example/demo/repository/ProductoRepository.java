package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Producto;

public interface ProductoRepository {
	// 2do
	//simulacion de la base de datos -- interfaces
	
	void guardar(Producto producto);
	void actualizar(Producto producto);
	void eliminar(Integer id);
	List<Producto> listar();

	Producto obtener(Integer id);
	
	
	

}
