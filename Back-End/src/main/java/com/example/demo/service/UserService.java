package com.example.demo.service;

public interface UserService {

	Long getUserId(String auth);

	void chekId(Long id, String auth);

}
