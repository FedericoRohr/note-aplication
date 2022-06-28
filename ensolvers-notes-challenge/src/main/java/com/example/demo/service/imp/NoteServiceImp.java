package com.example.demo.service.imp;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.NoteBasicDTO;
import com.example.demo.dto.NoteDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.NoteEntity;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.NoteService;

@Component
public class NoteServiceImp implements NoteService {
	@Autowired
	NoteMapper noteMapper;
	@Autowired
	NoteRepository noteRepository;
	@Autowired
	CategoryService categoryService;
    
	@Transactional
	@Override
	public NoteDTO save(NoteDTO dto, Long id) {
		dto.setUserid(id);
		NoteEntity entity= noteMapper.noteDTO2Entity(dto);
		entity=noteRepository.save(entity);
		NoteDTO response=noteMapper.noteEntity2DTO(entity);
		return response;
	}
	@Transactional
	@Override
	public void delete(Long id) {
			noteRepository.deleteById(id);
		}
	@Transactional
	@Override
	public void update(Long id, NoteDTO dto) throws Error  {
		 Optional<NoteEntity>note= noteRepository.findById(id);
		 if(!note.isPresent()) {
			 throw new RuntimeException("id invalido");
		 }
		 NoteEntity entity= note.get();
		 noteRepository.save(noteMapper.update(entity,dto));
		}
	
	@Transactional
	@Override
	public void archive(Long id) throws Error {
		Optional<NoteEntity>note= noteRepository.findById(id);
		 if(!note.isPresent()) {
			 throw new RuntimeException("id invalido");
		 }
		 NoteEntity entity= note.get();
		 if(entity.isArchived()) {
			 throw new RuntimeException("Alredy archive");
		 }
		 entity.setArchived(true);
	}
	@Transactional
	@Override
	public void unArchive(Long id) throws Error {
		Optional<NoteEntity>note= noteRepository.findById(id);
		 if(!note.isPresent()) {
			 throw new RuntimeException("id invalido");
		 }
		 NoteEntity entity= note.get();
		 if(!entity.isArchived()) {
			 throw new RuntimeException("Alredy unArchive");
		 }
		 entity.setArchived(false);
		
		
	}
	
	
	@Override
	public List<NoteBasicDTO> getAll(Long id) {
		List<NoteEntity>notesEntity= noteRepository.findByUserIdAndArchivedFalse(id);
		return noteMapper.noteEntity2BasicDTOlist(notesEntity);
		 
	}
	@Override
	public List<NoteBasicDTO> getAllArchive(Long id) {
		List<NoteEntity>notesEntity= noteRepository.findByUserIdAndArchivedTrue(id);
		return noteMapper.noteEntity2BasicDTOlist(notesEntity);
	}
	@Override
	public void addCategory(Long noteId, CategoryDTO category) {
		Optional<NoteEntity>note= noteRepository.findById(noteId);
		 if(!note.isPresent()) {
			 throw new RuntimeException("id invalido");
		 }
		 NoteEntity noteEntity= note.get();
		/// checks if exist to avoid duplicates in category table
		CategoryEntity categoryEntity= categoryService.exist(category.getName());
		if(categoryEntity!=null) {
			/// checks if note already have it 
			if(!noteEntity.getCategories().contains(categoryEntity)) {
				this.addSafe(noteEntity, categoryEntity);
			}		
		}else {
			categoryEntity =categoryService.saveAndReturnEntity(category);
			this.addSafe(noteEntity, categoryEntity);
		}	
	}

	
	
	
	
	
	
	
	private void addSafe(NoteEntity note, CategoryEntity category) {
		note.addCategories(category);
		noteRepository.save(note);
	}

}
