package com.example.demo.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//ACA ESTAMOS USANDO JWT APRA LOS ACCESOS
//AGREGAR LAS DEPENDENCIAS @XXXX
@Component
public class TokenUtil implements Serializable{
	
	//PEGAMOS _TODO EL CODIGO DENTRO QUE EL PROFESOR MANDO( ESTA EN EL WSP)
	//PEGAMOS LA DEPENDENCIA EN EL PORM
	//	<dependency>
	//    <groupId>io.jsonwebtoken</groupId>
	//    <artifactId>jjwt</artifactId>
	//    <version>0.9.1</version>
	//</dependency>
	
	//CTRL + SHIF + O APRA AGREGAR LAS DEPENDENCIAS
	//REVISAR BIEN LAS IMPORTACIONE S ES UTIL.SQL
	//EN EL PROPERTIRES AGREGAR LA CLAVE PROPIA
	
	////////////////////////////////
	// DESDE ACA
	private static final long serialVersionUID = -3553931089359371862L;

	public static final long JWT_TOKEN_VALIDITY = 1 * 60 * 60; // Duración - 1 hora  CAMBIAMOS EL "1" para la hora 

	@Value("${jwt.secret}")
	private String secret;

	// Recupera el usuario que se envia en el JWT
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Recupera la fecha de expiración que se envia en el JWT
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// Obtiene la clave secreta del JWT
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Comprueba si el token a caducado
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Genera un token para el usuario
	public String generateToken(String user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	///HASTA ACA
	////////////////////////////////

	

}
