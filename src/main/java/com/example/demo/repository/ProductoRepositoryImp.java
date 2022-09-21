package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.model.Producto;



//4to
//@Service
@Repository
public class ProductoRepositoryImp implements ProductoRepository {
	
	//3er
	//Es una clase y le damos en add agregar la interface, es una extension de la interace
	
	
	//5to
	//simulacion a una bd de datos
	
	List<Producto> almacenamiento = new ArrayList<>();
	
	
	
	
	///
	
	

	@Override
	public void guardar(Producto producto) {
		// TODO Auto-generated method stub
		almacenamiento.add(producto);              //7to pasamos la variable y add

	}

	@Override
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		Producto existeActualizar = obtener(producto.getIdProducto());
		eliminar(existeActualizar.getIdProducto());
		almacenamiento.add(producto);
	}

	@Override
	public void eliminar(Integer id) {     //9no agre
		// TODO Auto-generated method stub
		Producto existeEliminar = obtener(id);
		almacenamiento.remove(existeEliminar);

	}

	@Override
	public List<Producto> listar() {
		// TODO Auto-generated method stub
		return almacenamiento;                //6to pasamos la variable 
	}

	@Override
	public Producto obtener(Integer id) {
		// TODO Auto-generated method stub
		
		return almacenamiento.stream().filter(p->p.getIdProducto()==id).findFirst().orElse(null);   //8to  progrmacion en flujo(solo en listas) para ver si el id es igual al id de la lista
	}

}
