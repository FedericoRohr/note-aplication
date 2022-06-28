package com.example.demo.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
@Autowired
CategoryRepository categoryRepository;
@Autowired
CategoryMapper categoryMapper;

	@Override
	public CategoryEntity exist(String name) {
		CategoryEntity entity = null;
		Optional<CategoryEntity>category= categoryRepository.findByName(name);
		 if(category.isPresent()) {
			 entity=category.get();
		 }
		return entity;
	}

	@Override
	public CategoryEntity saveAndReturnEntity(CategoryDTO category) {
		CategoryEntity entity = categoryMapper.categoryDTO2Entity(category);
		return entity = categoryRepository.save(entity);
		}
	
	
	

}
