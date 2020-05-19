package com.genpact.assignment.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Library;
import com.genpact.assignment.backend.service.LibraryService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	//Fetch the list of all libraries
	@GetMapping(path = "/libraries")
	public List<Library> getLibraries() {
		return libraryService.getAllLibraries();
	}

	//Fetch the specific library for specific library id
	@GetMapping(path = "/library/{id}")
	public ResponseEntity<Library> getLibraryForId(@PathVariable(value = "id")Long libraryId)
	throws LibraryNotFoundException
			{
		Library library=libraryService.getLibraryById(libraryId);
		return ResponseEntity.ok().body(library);
	}
	

	//create new library entity object
	@PostMapping(path ="/library") 
	public ResponseEntity<Library> addNewLibrary(@Validated @RequestBody Library library) {
		Library savedLibrary=libraryService.saveLibrary(library);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedLibrary);
	}
	
	//create new library entity object
		@DeleteMapping(path ="/library/{libraryId}") 
		public ResponseEntity<String> addNewLibrary(@PathVariable(value = "libraryId")Long libraryId) throws LibraryNotFoundException {
			boolean deleteLibraryFlag=libraryService.deleteLibrary(libraryId);
			if(deleteLibraryFlag)
				return ResponseEntity.status(HttpStatus.OK)
					.body("Deleted Library Id as : "+libraryId);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("There was an error encountered on server");
		}
	
}
