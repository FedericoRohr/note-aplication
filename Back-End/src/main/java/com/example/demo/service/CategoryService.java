package com.example.demo.service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;

public interface CategoryService {
	/**
	 * @return categoryEntity or null
	*/
	public CategoryEntity exist(String name) throws RuntimeException;

	public CategoryEntity saveAndReturnEntity(CategoryDTO category);

}
