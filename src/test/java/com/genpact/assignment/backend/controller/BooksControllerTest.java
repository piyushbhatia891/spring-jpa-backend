package com.genpact.assignment.backend.controller;

import java.util.ArrayList;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.genpact.assignment.backend.BackendApplication;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
classes = BackendApplication.class)
public class BooksControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Sql({ "schema.sql", "data.sql" })
    @Test
    @Ignore
    public void testAllBooks() 
    {
        assertTrue(
                this.restTemplate
                    .getForObject("http://localhost:" + port + "api/v1/books", ArrayList.class)
                    .size() == 1);
    }
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

	
	@SuppressWarnings("unchecked")
	@Test
	public void addNewBook() {
		Book book=new Book("test_book","test_description",new Library("test_lib"));
		ResponseEntity<String> response = createNewBook(book);
		assertNotNull(response);
	}
	private ResponseEntity<String> createNewBook(Book book) {
		ResponseEntity<String> response =restTemplate.exchange(
		          createURLWithPort("/api/v1/books"),
		          HttpMethod.POST, new HttpEntity(book, new HttpHeaders()), String.class);
		return response;
	}
	
	@Test
	public void testGetBook() throws Exception {
		Book book=new Book("test_book","test_description",new Library("test_lib"));
		ResponseEntity<String> bookId = createNewBook(book);
	    ResponseEntity<Book> response = getBook(bookId);
	    String expected = "{\"id\":1,\"bookName\":\"test_book\",\"bookDescription\":\"test description\"}";
	    assertTrue(expected.equals(response), "messages are equal");
	  }
	private ResponseEntity<Book> getBook(ResponseEntity<String> bookId) {
		ResponseEntity<Book> response = restTemplate.exchange(
	          createURLWithPort("/api/v1/books/"+bookId), HttpMethod.GET, null, Book.class);
		return response;
	}
	 
	 @Test
	 @Ignore
	 public void deleteBook() {
		 Book book=new Book("test_book","test_description",new Library("test_lib"));
		 ResponseEntity<String> bookId = createNewBook(book);
	    ResponseEntity<String> response = deleteBook(bookId);
	    String expected="Deleted Book Id as : "+bookId;
	    assertTrue(expected.equals(response), "messages are equal");
	    
	 }
	private ResponseEntity<String> deleteBook(ResponseEntity<String> bookId) {
		ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/api/v1/books/"+bookId), HttpMethod.DELETE, null, String.class);
		return response;
	}
	 
	
}
