package com.genpact.assignment.backend.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.annotation.security.RunAs;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BooksRepositoryTest {

	
	@Autowired
	private BooksRepository booksRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@AfterEach
	public void setUp() {
		booksRepository.deleteAll();
	}
	
	@Test
	@Ignore
	public void testGetBooks() {
		Library library=new Library("test");
		Book book=new Book("test title","test description",library);
		Book savedBook=booksRepository.save(book);
		assertNotNull(savedBook);
	}
	
	@Test
	@Ignore
	public void testDeleteBook() {
		Library library=new Library("test");
		Book book=new Book("test title","test description",library);
		Book savedBook=booksRepository.save(book);
		booksRepository.delete(book);
		boolean present=booksRepository.findById(savedBook.getId())
			.isPresent();
		assertTrue(present==false);
	}
	
	@Test
	@Ignore
	public void testCreateNewBook() {
		Library library=new Library("test");
		Book book=new Book("test title","test description",library);
		Book savedBook=booksRepository.save(book);
		boolean present=booksRepository.findById(savedBook.getId())
				.isPresent();
			assertTrue(present==true);
	}
	
}
