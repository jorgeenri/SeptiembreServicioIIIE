package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Producto;

//indicar que esta intefaz es un repositorio (T=modelo, ID=)
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	//esto era con la simulacion
	// 2do
	//simulacion de la base de datos -- interfaces
	
	//void guardar(Producto producto);
	//void actualizar(Producto producto);
	//void eliminar(Integer id);
	//List<Producto> listar();

	//Producto obtener(Integer id);
	
	//esto es coon BD de Mysql el oficial(se le agrega el extends )
	
	
	

}
