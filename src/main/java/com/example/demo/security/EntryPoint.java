package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


///AL CREAR ESTA CLASE AGREGAMOS LA INTERFAZ DE SPRING QUE SE LLAMAR AuthenticationEntryPoint  OJOO 

@Component
public class EntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado");
	}

}
