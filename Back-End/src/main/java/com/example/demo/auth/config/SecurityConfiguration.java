package com.example.demo.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Arrays;


@EnableWebSecurity
@Configuration
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
public AuthenticationManager getAuthenticationManager() throws Exception { /// to bean a instance
    return super.authenticationManagerBean();
}
	
@Bean
public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance(); // TODO: encode
	}
@Override
protected void configure(HttpSecurity httpSecurity) throws Exception{
	httpSecurity.cors().and().csrf().disable()
	.authorizeRequests().antMatchers("/auth/*").permitAll() //
	.anyRequest().authenticated()
	.and().exceptionHandling()
	.and().sessionManagement()
	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);// 

	httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class); 
}


	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8081"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		configuration.addAllowedOrigin("*");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	
	
}
