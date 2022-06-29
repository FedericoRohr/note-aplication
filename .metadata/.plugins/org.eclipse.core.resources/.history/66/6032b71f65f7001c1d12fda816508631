package com.example.demo.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.auth.service.JwtUtils;
import com.example.demo.auth.service.UserDetailsCustomService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsCustomService userDetailsCostumService;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization"); // header

		String userName = null;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { /// verifico jwt
			jwt = authorizationHeader.substring(7);/// le sacamos el "Beaerer "
			userName = jwtUtils.extractUsername(jwt);
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {// verifico userName y
																									// que no este
																									// autentificado
			UserDetails userDetails = this.userDetailsCostumService.loadUserByUsername(userName);
			if (jwtUtils.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), userDetails.getPassword());// crea el objeto
				Authentication auth = authenticationManager.authenticate(authReq); // lo autoriza
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		filterChain.doFilter(request, response);

	}
}
