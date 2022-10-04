package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;

@Service
public class ProductoServiceImp implements ProductoService {
	
	
	//inyectamos la interface
	@Autowired
	private ProductoRepository repositorio;
	
	//ahora vamos a ingrear los metodos de JPA y marcamos el que hicimos temporal
	//RECORDAR QUE LUEGO SE DEBE PONER LA CADENA DE CONEXION EL EL PROPERTIS

	@Override
	public void guardar(Producto producto) {
		// TODO Auto-generated method stub
		//repositorio.guardar(producto);
		repositorio.save(producto);

	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		//repositorio.actualizar(producto);
		repositorio.saveAndFlush(producto);

	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		//repositorio.eliminar(id);
		repositorio.deleteById(id);

	}

	@Override
	public List<Producto> listar() {
		// TODO Auto-generated method stub
		//return repositorio.listar();
		return repositorio.findAll();
	}

	@Override
	public Producto obtener(Integer id) {
		// TODO Auto-generated method stub
		//return repositorio.obtener(id);
		return repositorio.findById(id).orElse(null);
	}

}
