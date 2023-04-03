package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CategoryEntity;

public interface CategoryRepository  extends JpaRepository<CategoryEntity, Long> {

	Optional<CategoryEntity> findByName(String name);

}
