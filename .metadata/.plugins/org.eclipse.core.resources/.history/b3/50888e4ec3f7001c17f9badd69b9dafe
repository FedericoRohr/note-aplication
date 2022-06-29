package com.example.demo.auth.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.auth.dto.UserDTO;
import com.example.demo.auth.entity.UserEntity;
import com.example.demo.auth.repository.UserRepository;

@Service
public class UserDetailsCustomService implements UserDetailsService {

@Autowired
private UserRepository userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUserName(username);
		if(userEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		 return new User(userEntity.getUserName(), userEntity.getPassword(), Collections.emptyList());
	
	}
	
	
	
	 public boolean save(UserDTO dto){
		 System.out.println(dto.getUsername());
		 UserEntity userEntity = new UserEntity(); ///
		 userEntity.setPassword(dto.getPassword());
		 userEntity.setUserName(dto.getUsername());
		 userEntity = userRepository.save(userEntity);
		 System.out.println(userEntity);
	        return userEntity != null;
	 }
	 
	
	

}
