package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioDTORequest;
import com.example.demo.dto.UsuarioDTOResponse;
import com.example.demo.security.TokenUtil;
import com.example.demo.security.UserDetailService;

//ESTE USUARIO CONTROLLER LO CREO PARA PODER GESTIONARDE MANERA SEGURA LA CAPA SECURITY
@RestController
public class UsuarioController {
	
	@Autowired
	private TokenUtil util;
	
	@Autowired
	private UserDetailService service;
	
	@RequestMapping(path = "/crearToken",method = RequestMethod.POST)
	public ResponseEntity<?> crearToken(@RequestBody UsuarioDTORequest request){
		
		UserDetails user = service.loadUserByUsername(request.getUsuario());
		if(user.getPassword().equals(request.getContrasenia())) 
		return ResponseEntity.ok(new UsuarioDTOResponse(util.generateToken(user.getUsername())));
		else
			throw new UsernameNotFoundException("Contraseña Invalida");
	}
	

}
