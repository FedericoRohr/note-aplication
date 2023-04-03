package com.example.demo.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.auth.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class NoteDTO {
	private Long id;

	private String title;

	private String content;

	private List<CategoryDTO>categories=new ArrayList<>();
	
	private UserEntity user;
	
	private Long userid;
	
	private LocalDate lastedited;

	private boolean archived ;



}
