package com.genpact.assignment.backend.service;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;
import com.genpact.assignment.backend.repository.BooksRepository;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class BooksServiceTest {
	
	@InjectMocks
	private BookService booksService;
	
	@Mock
	private BooksRepository booksRepository;
	
	
	@Test
	@Ignore
	public void getBooks() {
		Mockito.when(booksRepository.findAll()).thenReturn((Iterable<Book>) any(Iterable.class));
		List<Book> returnedBooks=booksService.getAllBooks();
		assertThat(returnedBooks.size(),is(1));
		
	}
	
	@Test
	@Ignore
	public void getBooksFromConnectionPool() {
		Mockito.when(booksRepository.findAll()).thenReturn((Iterable<Book>) any(Iterable.class));
		List<Book> returnedBooks=booksService.getAllBooksFromConnectionPool();
		assertThat(returnedBooks.size(),is(1));
		
	}
}
