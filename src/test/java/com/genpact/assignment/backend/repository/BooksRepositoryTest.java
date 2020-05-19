package com.genpact.assignment.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.annotation.security.RunAs;

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
	public void testGetBooks() {
		Library library=new Library("test");
		libraryRepository.save(library);
		Book book=new Book("test title","test description",library);
		booksRepository.save(book);
		Book getSavedBook=booksRepository.findByBookName("test title");
		assertNotNull(getSavedBook);
		assertEquals(book.getBookName(), getSavedBook.getBookName());
	}
	
	@Test
	public void testPostBook() {
		Library library=new Library("test");
		libraryRepository.save(library);
		Book book=new Book("test title","test description",library);
		booksRepository.save(book);
		assertEquals("test tile", book.getBookName());
	}
	
	
	
}
