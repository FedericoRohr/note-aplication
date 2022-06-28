package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.NoteBasicDTO;
import com.example.demo.dto.NoteDTO;

public interface NoteService {

	NoteDTO save(NoteDTO dto, Long id);

	void delete(Long id);

	void update(Long id, NoteDTO dto);

	void archive(Long id);

	void unArchive(Long id);

	List<NoteBasicDTO> getAll(Long id);

	List<NoteBasicDTO> getAllArchive(Long id);

	void addCategory(Long userId, CategoryDTO category);

	

}
