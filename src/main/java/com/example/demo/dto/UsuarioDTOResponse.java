package com.example.demo.dto;

//ESTA ES PARA LA CAPA SEGURIDAD DEL LOGIN
public class UsuarioDTOResponse {
	
	
	private String token;

	public String getToken() {
		return token;
	}
	
	//GETTER Y SETTER
	public void setToken(String token) {
		this.token = token;
	}

	//CONSTRUCTOR VACIO
	public UsuarioDTOResponse(String token) {
		super();
		this.token = token;
	}
	
	//CONSTRUCTOR CON PARAMETRO ES EL SUPERC CLASS
	public UsuarioDTOResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	

}
