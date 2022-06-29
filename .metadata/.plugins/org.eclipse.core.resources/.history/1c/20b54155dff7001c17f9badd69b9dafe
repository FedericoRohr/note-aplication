package com.example.demo.auth.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.auth.dto.AuthenticationRequest;
import com.example.demo.auth.dto.AuthenticationResponse;
import com.example.demo.auth.dto.UserDTO;
import com.example.demo.auth.service.JwtUtils;
import com.example.demo.auth.service.UserDetailsCustomService;

@Controller
@RequestMapping("/auth")
public class UserAuthController {
	
	private AuthenticationManager authenticationManager;
    private UserDetailsCustomService userDetailsCustomService;
    private JwtUtils jwtTokenUtil;

    @Autowired
    public UserAuthController(
        AuthenticationManager authenticationManager,
        UserDetailsCustomService userDetailsCustomService, 
        JwtUtils jwtTokenUtil) {
            this.authenticationManager = authenticationManager;
            this.userDetailsCustomService = userDetailsCustomService;
            this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signUp (@Valid @RequestBody UserDTO dto) throws Exception {
     userDetailsCustomService.save(dto);
     return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    
    @PostMapping ("/login")
    public ResponseEntity<AuthenticationResponse> signIn (@Valid @RequestBody AuthenticationRequest authRequest) throws Exception {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            userDetails = (UserDetails) auth.getPrincipal();

        } catch (BadCredentialsException e) {
            throw new Exception("user or password wrong");
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
}
