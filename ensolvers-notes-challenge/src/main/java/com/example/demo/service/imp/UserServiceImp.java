package com.example.demo.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserExpampleEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
@Service
public class UserServiceImp implements UserService {

@Autowired
UserMapper userMapper;
@Autowired
UserRepository userRepository;

	  
	@Override
	public UserDTO save(UserDTO user) {
		UserExpampleEntity entity =  userMapper.UserDTO2Entity(user);
		entity=userRepository.save(entity);
		UserDTO response = userMapper.UserEntity2DTO(entity);
		return response;
	}
	


	
	
}
