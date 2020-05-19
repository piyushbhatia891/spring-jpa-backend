package com.genpact.assignment.backend.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Ignore;
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
	@Ignore
	public void testGetLibraries() {
		Library library=new Library("test library");
		Library savedLibrary=libraryRepository.save(library);
		assertNotNull(savedLibrary);
	}
	
	@Test
	@Ignore
	public void testCreatingNewLibrary() {
		Library library=new Library("test library");
		Library savedLibrary=libraryRepository.save(library);
		boolean present=libraryRepository.findById(savedLibrary.getId())
				.isPresent();
			assertTrue(present==true);
	}
	
	@Test
	@Ignore
	public void testDeleteLibrary() {
		Library library=new Library("test");
		Library lib=libraryRepository.save(library);
		libraryRepository.delete(library);
		boolean present=libraryRepository.findById(lib.getId())
			.isPresent();
		assertTrue(present==false);
	}
}
