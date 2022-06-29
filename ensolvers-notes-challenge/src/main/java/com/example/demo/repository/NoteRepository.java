package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.NoteEntity;
@Repository
public interface NoteRepository extends JpaRepository<NoteEntity,Long> {

	
	List<NoteEntity> findByUserIdAndArchivedTrue(Long id);
	List<NoteEntity> findByUserIdAndArchivedFalse(Long id);
	List<NoteEntity>findByUserId(Long id);
	
}
