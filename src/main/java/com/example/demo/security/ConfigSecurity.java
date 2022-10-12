package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//CONFIGURACION PARA SEGURIDAD
//ctril + shift + c para comentar
//ctrl + shit + o si hay erro en eol user al importar, o borramos el import y luego el comando
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity{
	
	@Bean
	public UserDetailsService userDetail() {
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("profesor")
						.password(encriptado().encode("123"))
						.roles ("ADMIN")
						.build());
		return manager;
	}
	
	
	//se puede usar en cualquier lado
	@Bean
	public PasswordEncoder encriptado() {
		return new BCryptPasswordEncoder();
	}
	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/producto/v1/*").access("hasRole('ADMIN')")//estamos aacediendo a todos los enpoint o metoodos
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
			
			
		return http.build();
}
	
	
	
	
	

}
