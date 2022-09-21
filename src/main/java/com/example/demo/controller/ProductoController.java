package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

//semana2_paso01
@RestController 
@RequestMapping("/producto/v1")           //recurso raiz
public class ProductoController {

	//crear metodos paso02
	//metodo listar, no recibe parametro de entrada
	
	//inyectamos la interface
	@Autowired
	private ProductoService service;  ////interface
	
	@RequestMapping(path = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<Producto>>listar(){
		return new ResponseEntity<List<Producto>>(service.listar(), HttpStatus.OK);
		
	}
	
	//metodo guardar  //necesita datos de entrada
	
	@RequestMapping(path = "/guardar", method = RequestMethod.POST)
	public ResponseEntity<Void> guardar(@RequestBody Producto producto){
		service.guardar(producto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	//obtener por id  // el id debe ser igual al id de obtener porid y cambia el tipo de dato
	
	@RequestMapping(path = "/listar/{ïd}",method = RequestMethod.GET)
	public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id){
		
		Producto producto = service.obtener(id);
		
		if( producto != null) {
			return new ResponseEntity<Producto>(producto, HttpStatus.OK);
		}else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
	
		}
		
	}
	
	//Actualizar o editar
	
	@RequestMapping(path = "/editar",method = RequestMethod.PUT)
	public ResponseEntity<Void> editar(@RequestBody Producto producto){
		Producto p = service.obtener(producto.getIdProducto());
		
		if(p != null) {
			service.actualizar(producto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	
	}
	
	//eliminar
	
	@RequestMapping(path = "/eliminar/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		
		Producto producto = service.obtener(id);
		
		if(producto != null) {
			service.eliminar(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
}
