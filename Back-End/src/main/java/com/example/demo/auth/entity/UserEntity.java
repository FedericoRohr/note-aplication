package com.example.demo.auth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter @Setter
public class UserEntity {
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
private Long id;

@Email
@Column(name="user_name")
private String userName;
@Size(min=8)
private String password;
@Override
public String toString() {
	return "UserEntity [id=" + id + ", userName=" + userName + ", password=" + password + "]";
}



}
