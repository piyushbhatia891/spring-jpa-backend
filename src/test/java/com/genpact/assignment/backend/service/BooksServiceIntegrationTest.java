package com.genpact.assignment.backend.service;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.repository.BooksRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceIntegrationTest {
	@Autowired 
	BookService booksService;

	 @MockBean
	    private BooksRepository booksRepository;
	    @Test
	    public void testGetBooks() throws Exception {
	    	List<Book> returnedBooks=booksService.getAllBooksFromConnectionPool();
			assertThat(returnedBooks.size(),is(1));
	    }

}
