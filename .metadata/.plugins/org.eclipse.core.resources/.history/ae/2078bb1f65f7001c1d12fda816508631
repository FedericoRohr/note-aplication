package com.example.demo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.auth.filter.JwtRequestFilter;
import com.example.demo.auth.service.UserDetailsCustomService;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

@Autowired
private UserDetailsCustomService userDitailsCustomService;

@Autowired
private JwtRequestFilter jwtRequestFilter;


	
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
	auth.userDetailsService(userDitailsCustomService);
}

@Bean
public AuthenticationManager getAuthenticationManager() throws Exception { /// permite hacer el bean de la instancia
    return super.authenticationManagerBean();
}
	
@Bean
public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance(); // no es una buena practica en general.
	}

@Override
protected void configure(HttpSecurity httpSecurity) throws Exception{
	httpSecurity.csrf().disable()
	.authorizeRequests().antMatchers("/auth/*").permitAll() // permite que se ingrese a determinadas paginas sin autentificacion
	.anyRequest().authenticated()
	.and().exceptionHandling()/// permite manejo de expeciones
	.and().sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);// no guarda el estado de ejecuccion

	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); //usamos nuestro filtro
}


	
	
}
