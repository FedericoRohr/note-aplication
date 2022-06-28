package com.example.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="userExample")
@Getter @Setter
public class UserExpampleEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	
	
	
	
}
