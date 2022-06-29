package com.example.demo.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiErrorDTO {


private HttpStatus status;
private String message;
private List<String>error;
public ApiErrorDTO(HttpStatus status, String message, List<String> error) {
	this.status = status;
	this.message = message;
	this.error = error;
}


}
