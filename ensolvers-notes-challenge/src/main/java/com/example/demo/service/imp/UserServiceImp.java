package com.example.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.auth.entity.UserEntity;
import com.example.demo.auth.repository.UserRepository;
import com.example.demo.auth.service.JwtUtils;
import com.example.demo.service.UserService;

@Service
public class UserServiceImp implements UserService{
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Long getUserId(String auth) {
     return getUserByAuth(auth).getId();
	}
	
	public UserEntity getUserByAuth(String auth) {
		String jwt = auth.substring(7);
		String userName = jwtUtils.extractUsername(jwt);
		return userRepository.findByUserName(userName);
		
	}

}
