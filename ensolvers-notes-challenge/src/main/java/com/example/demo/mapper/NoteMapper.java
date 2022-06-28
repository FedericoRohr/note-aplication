package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.NoteBasicDTO;
import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.NoteEntity;
import com.example.demo.repository.UserRepository;

@Component
public class NoteMapper {
	@Autowired
	UserRepository userRepository;

	public NoteEntity noteDTO2Entity(NoteDTO dto) {
		NoteEntity entity = new NoteEntity();
		/// entity.setCategories(dto.getCategories());
		entity.setContent(dto.getContent());
		entity.setId(dto.getId());
		entity.setLastEdited(dto.getLastedited());
		entity.setTitle(dto.getTitle());
		entity.setUser(userRepository.getReferenceById(dto.getUserid()));
		entity.setUserId(dto.getUserid());
		return entity;
	}

	public NoteDTO noteEntity2DTO(NoteEntity entity) {
		NoteDTO dto = new NoteDTO();
		dto.setArchived(entity.isArchived());
		dto.setContent(entity.getContent());
		dto.setId(entity.getId());
		dto.setLastedited(entity.getLastEdited());
		dto.setTitle(entity.getTitle());
		dto.setUser(entity.getUser());
		dto.setUserid(entity.getUserId());
		/// dto.setCategories(entity.getCategories());
		return dto;
	}

	public NoteEntity update(NoteEntity entity, NoteDTO dto) {
		/// entity.setCategories(null);
		if (dto.getTitle() != null) {
			entity.setTitle(dto.getTitle());
		}
		if (dto.getContent() != null) {
			entity.setContent(dto.getContent());
		}
		return entity;
	}

	public List<NoteBasicDTO> noteEntity2BasicDTOlist(List<NoteEntity> notesEntity) {
		List<NoteBasicDTO> dtoList = new ArrayList<>();
		for (NoteEntity e : notesEntity) {
			dtoList.add(this.noteEntity2BasicDTO(e));
		}
		return dtoList;
	}
	
     public NoteBasicDTO noteEntity2BasicDTO(NoteEntity entity) {
    	 NoteBasicDTO dto = new NoteBasicDTO();
    	 dto.setId(entity.getId());
    	 dto.setTitle(entity.getTitle());
    	 dto.setContent(entity.getContent());
    	 dto.setLastedited(entity.getLastEdited());
    	 return dto; 	 
     }

}
