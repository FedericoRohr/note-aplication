package com.example.demo.controllers;



import java.util.Arrays;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ApiErrorDTO;
import com.example.demo.exception.ArchiveException;
import com.example.demo.exception.ParamNotFound;
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

@ExceptionHandler (value= {ParamNotFound.class})
protected ResponseEntity<Object> handleParamNotFound(RuntimeException ex, WebRequest request){
	ApiErrorDTO errorDTO= new ApiErrorDTO(HttpStatus.BAD_REQUEST,ex.getMessage(),Arrays.asList("Param not found"));
	return handleExceptionInternal(ex,errorDTO,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
}

@ExceptionHandler (value= {ArchiveException.class})
protected ResponseEntity<Object> handleArchiveException(RuntimeException ex, WebRequest request){
	ApiErrorDTO errorDTO= new ApiErrorDTO(HttpStatus.BAD_REQUEST,ex.getMessage(),Arrays.asList("Archive exception"));
	return handleExceptionInternal(ex,errorDTO,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);
}




}
	

