package com.genpact.assignment.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;
import com.genpact.assignment.backend.service.LibraryService;

public class LibraryRepositoryTest {

	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@AfterEach
	public void setUp() {
		libraryRepository.deleteAll();
	}
	
	@Test
	public void testGetBooks() {
		Library library=new Library("test library");
		libraryRepository.save(library);
		Library getSavedLibrary=libraryRepository.findByLibraryName("test library");
		assertNotNull(getSavedLibrary);
		assertEquals(library.getLibraryName(), getSavedLibrary.getLibraryName());
	}
	
	@Test
	public void testPostBook() {
		Library library=new Library("test library");
		libraryRepository.save(library);
		assertEquals("test library", library.getLibraryName());
	}
	
}
