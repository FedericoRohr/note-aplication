package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.NoteBasicDTO;
import com.example.demo.dto.NoteDTO;
import com.example.demo.service.NoteService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("notes")
public class NoteController {
	@Autowired
	NoteService noteService;
	@Autowired
	UserService userService;
	
	
    
	@PostMapping("/user")
	public ResponseEntity<NoteDTO> save(@RequestBody NoteDTO dto, 
			@RequestHeader(value="Authorization") String auth) {
		Long userId =userService.getUserId(auth);
		NoteDTO response = noteService.save(dto, userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping("/{noteId}/user")
	public ResponseEntity<Void> delete(@PathVariable Long noteId, @RequestHeader(value="Authorization") String auth) {
		Long userId =userService.getUserId(auth);
		noteService.delete(noteId,userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * update only title or content no categories
	*/
	@PutMapping("/{noteId}/user")
	public ResponseEntity<Void>update(@PathVariable Long noteId, @RequestHeader(value="Authorization") String auth, @RequestBody NoteDTO dto ){
		Long userId =userService.getUserId(auth);
		noteService.update(noteId, userId ,dto);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	@PutMapping("/archive/{noteId}")
	public ResponseEntity<Void>archive(@PathVariable Long noteId,@RequestHeader(value="Authorization") String auth){
		Long userId =userService.getUserId(auth);
		noteService.archive(noteId,userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@PutMapping("/unarchive/{noteId}")
	public ResponseEntity<Void>unArchive(@PathVariable Long noteId,@RequestHeader(value="Authorization") String auth ){
		Long userId =userService.getUserId(auth);
		noteService.unArchive(noteId,userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/**
	 * get all unarchive
	 * shows basic dto because NoteDTO have unnecessary information
	*/
	@GetMapping("/user")
	public ResponseEntity<List<NoteBasicDTO>>getAll(@RequestHeader(value="Authorization") String auth ){
		Long userId =userService.getUserId(auth);
		List<NoteBasicDTO>notes=noteService.getAll(userId);
		return ResponseEntity.ok().body(notes);
	}
	
	/**
	 * get all archive
	 * shows basic dto because NoteDTO have unnecessary information
	*/
	@GetMapping("/user/archive")
	public ResponseEntity<List<NoteBasicDTO>>getAllArchive(@RequestHeader(value="Authorization") String auth){
		Long userId =userService.getUserId(auth);
		List<NoteBasicDTO>notes=noteService.getAllArchive(userId);
		return ResponseEntity.ok().body(notes);
	}
	
	@PostMapping("/{noteId}/category")
	public  ResponseEntity<Void>addCategory(@PathVariable Long noteId,
			@RequestHeader(value="Authorization") String auth , @RequestBody @Valid CategoryDTO category ){
		Long userId =userService.getUserId(auth);
		noteService.addCategory(noteId,userId,category);
	 return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	
	
	/**
	 * delete category in noteEntity but persist in categories table
	 * 
	*/
	@DeleteMapping("{noteId}/category")
	public  ResponseEntity<Void>deleteCategory(@PathVariable Long noteId,
			@RequestHeader(value="Authorization") String auth,@RequestBody @Valid CategoryDTO category ){
		Long userId =userService.getUserId(auth);
		noteService.deleteCategory(noteId,userId,category);
	 return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	
	@GetMapping("category")
	public ResponseEntity<List<NoteBasicDTO>>getAllBycategory(@RequestHeader(value="Authorization") String auth,@RequestBody CategoryDTO category){
		Long userId =userService.getUserId(auth);
		List<NoteBasicDTO>notes=noteService.getAllByCategory(category,userId);
		return ResponseEntity.ok().body(notes);
	}
	
	
	
}
