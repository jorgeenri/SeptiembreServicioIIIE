package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;

//CONFIGURACION PARA SEGURIDAD
//ctril + shift + c para comentar
//ctrl + shit + o si hay erro en eol user al importar, o borramos el import y luego el comando
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter{

////VERSIONES PARA ABAJO DE 2.7.3
//EN EL POM VAMOS A CONFIGURAR LA VERSION, MARCANDO UNA Y PONIENDO LA 2.6.7 IMPORTANTE PARA EXTENDER EL WebSecurityConfigurerAdapter	
// PARA AGREGAR LOS 2 OVERRIDE VAMOS A SOURCE / OVERRIDE/IMPLEMETEM METODOS Y LUEGO 
//SELECCIONAMOS "HttpSecurity http" , "AuthenticationManagerBuilder" , "authenticationManager" y "authenticationManagerBean"
	
	//INDICAR LAS CLASES QUE HEMOS SOBREESCRITO
	@Autowired
	private UserDetailService service;
	
	@Autowired
	private TokenFilter filter;
	
	@Autowired
	private EntryPoint entrypoint;
	
	
	///01
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}



	
	
	//PARA ENCRIPTAR LA CONTRASEÑA
	@Bean
	public PasswordEncoder encriptado() {
		return new BCryptPasswordEncoder();
	}
	
	//02
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("profesor").password(encriptado().encode("123")).roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("estudiante").password(encriptado().encode("123")).roles("USER");
		//USARAREMOPS LOS AUTH DE JWT
		auth.userDetailsService(service).passwordEncoder(encriptado());
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers(HttpMethod.GET).access("hasRole('USER')")
//		.antMatchers("/producto/v1/*").access("hasRole('ADMIN')")
//		.and()
//		.httpBasic()
//		.and()
//		.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/crearToken").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(entrypoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
		.csrf().disable();
		
	}
	
	
	

	
	
	
	
	
/// FORMA PARA TRABAJAR CON VERSION 2.7.3	
//	
//	@Bean
//	public UserDetailsService userDetail() {
//		
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(
//				User.withUsername("profesor")
//						.password(encriptado().encode("123"))
//						.roles ("ADMIN")
//						.build());
//		return manager;
//	}
//	
//	
//	//se puede usar en cualquier lado
//	@Bean
//	public PasswordEncoder encriptado() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		
//		http.authorizeRequests()
//			.antMatchers("/producto/v1/*").access("hasRole('ADMIN')")//estamos aacediendo a todos los enpoint o metoodos
//			.and()
//			.httpBasic()
//			.and()
//			.csrf().disable();
//			
//			
//		return http.build();
//}
	
	

	
	
	
	
	

}
