package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;

@Component
public class UserMapper {

	public UserEntity UserDTO2Entity(UserDTO user) {
		UserEntity entity = new UserEntity();
		entity.setId(user.getId());
		entity.setName(user.getName());
		return entity;
	}

	public UserDTO UserEntity2DTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
	


}
