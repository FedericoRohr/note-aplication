package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.NoteBasicDTO;
import com.example.demo.dto.NoteDTO;
import com.example.demo.service.NoteService;

@Controller
@RequestMapping("notes")
public class NoteController {
	@Autowired
	NoteService noteService;
	
    
	@PostMapping("/user/{id}")
	public ResponseEntity<NoteDTO> save(@RequestBody NoteDTO dto, @PathVariable Long id) {
		NoteDTO response = noteService.save(dto, id);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		/// TODO: implementar metodo que cheque que ambos id coincidan y si no tira expecion
		noteService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * update only title or content or categories
	*/
	@PutMapping("/{id}")
	public ResponseEntity<Void>update(@PathVariable Long id, @RequestBody NoteDTO dto ){
		/// TODO: implements UserID
		/// TODO: check user have that note ,throws exception
		noteService.update(id,dto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	@PutMapping("archive/{id}")
	public ResponseEntity<Void>archive(@PathVariable Long id){
		/// TODO: implements UserID
		/// TODO: check user have that note ,throws exception
		noteService.archive(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("unarchive/{id}")
	public ResponseEntity<Void>unArchive(@PathVariable Long id){
		/// TODO: implements UserID
		/// TODO: check user have that note ,throws exception
		noteService.unArchive(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * get all unarchive
	 * shows basic dto because NoteDTO have unnecessary information
	*/
	@GetMapping("/{id}")
	public ResponseEntity<List<NoteBasicDTO>>getAll(@PathVariable Long id){
		List<NoteBasicDTO>notes=noteService.getAll(id);
		return ResponseEntity.ok().body(notes);
	}
	
	/**
	 * get all archive
	 * shows basic dto because NoteDTO have unnecessary information
	*/
	@GetMapping("/archive/{id}")
	public ResponseEntity<List<NoteBasicDTO>>getAllArchive(@PathVariable Long id){
		List<NoteBasicDTO>notes=noteService.getAllArchive(id);
		return ResponseEntity.ok().body(notes);
	}
	
	@PostMapping("/user/{userId}/note/{noteId}")
	public  ResponseEntity<Void>addCategory(@PathVariable Long userId,
			@PathVariable Long noteId, @RequestBody CategoryDTO category ){
		/// TODO: check user have that note ,throws exception
		noteService.addCategory(noteId,category);
	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	
	
	
}
