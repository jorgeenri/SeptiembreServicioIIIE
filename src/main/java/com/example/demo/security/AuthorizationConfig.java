package com.example.demo.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


//AL CREAR ESTA CLASE VAMOS A AGREGAR EN BROWSE "authorizationServerConfigurerAdapter"

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	
	
	//Declaramos donde se guardaran las llaves
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;
	
	

	//implementamos sus metodos en source / Override-implement y seleccionamos los 3 primeros metodos

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		super.configure(security);
	}

	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory()
		.withClient("queridoprofesor")
		.secret(new BCryptPasswordEncoder().encode("queridoprofesor"))
		.authorizedGrantTypes("password", "authorization_code", "refresh_token")
		.scopes("read", "write", "trust")
		.accessTokenValiditySeconds(1*60*60)
		.refreshTokenValiditySeconds(5*60*60);
		
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain chain = new TokenEnhancerChain();
		chain.setTokenEnhancers(Arrays.asList(new TokenEnhancer() {
			
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				
				Map<String, Object> informacionAdicional = new HashMap<>();
				informacionAdicional.put("suscribete", "a mi canas");
				informacionAdicional.put("profesor","wilder");
				
				DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
				token.setAdditionalInformation(informacionAdicional);
				return token;
			}
		}, accessTokenConverter));
		
		endpoints.tokenStore(tokenStore)
		.authenticationManager(authenticationManager)
		.accessTokenConverter(accessTokenConverter)
		.tokenEnhancer(chain);
		
	}

	
	
	
	
}
