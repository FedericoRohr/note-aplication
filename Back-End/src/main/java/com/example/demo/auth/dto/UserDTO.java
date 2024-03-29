package com.example.demo.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {
@Email(message="user name must be Email")
private String username;
@Size(min=8, message="must have min than 8 characters")
private String password;
	
	

}
