package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;


///AL CREAR ESTA CLASE AGREGAMOS LA INTERFAZ DE SPRING QUE SE LLAMAR USERDETAILSERVICE  OJOO   IMPORTANTE

@Service
public class UserDetailService implements UserDetailsService {
	
	//inyectamos la capa repositorio
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usu = repository.findByUsuario(username);
		
		if(usu != null) {
			List<GrantedAuthority> listGranted = new ArrayList<>();
			GrantedAuthority granted = new SimpleGrantedAuthority(usu.getRol());
			listGranted.add(granted);
			return new User(
					usu.getUsuario(),
					usu.getContrasenia(),
					listGranted);			
		}else {
			throw new UsernameNotFoundException("Usuario no existe" + username);
		}
		
		
		
		
		
		
		
//		//EL USER ES DE SPRING EL QUE SE IMPORTA user.security.core
//		if("profesor".equals(username)) {
//			//este User VVV                // la contraseña debe de encripat, asi lo pide aoutho
//			return new User("profesor", new BCryptPasswordEncoder().encode("123"), new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("Usuario no existe" + username);
//		}
		
	}

}
