package com.example.demo.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


///AL CREAR ESTA CLASE AGREGAMOS LA INTERFAZ DE SPRING QUE SE LLAMAR USERDETAILSERVICE  OJOO   IMPORTANTE

@Service
public class UserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//
		
		//EL USER ES DE SPRING EL QUE SE IMPORTA user.security.core
		if("profesor".equals(username)) {
			//este User VVV
			return new User("profesor","123", new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("Usuario no existe" + username);
		}
		
	}

}
