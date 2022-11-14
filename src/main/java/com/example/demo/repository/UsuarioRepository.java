package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;

//EXTENDEMOS A JPA (ESTE REPOSITORIO ES PAR EL MODELO DE USUARIO QUE TENDRA CONEXION A BD)

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Usuario findByUsuario(String usuario);

}
