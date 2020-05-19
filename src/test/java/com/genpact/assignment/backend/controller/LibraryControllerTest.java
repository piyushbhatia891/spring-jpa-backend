package com.genpact.assignment.backend.controller;

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
import com.genpact.assignment.backend.BackendApplication;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
classes = BackendApplication.class)
public class LibraryControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@Ignore
	public void addNewLibrary() {
		Library library=new Library("test_lib");
		ResponseEntity<Library> savedLibrary = createNewLibrary(library);
		assertNotNull(savedLibrary);
	}
	
	@Test
	@Ignore
	public void testGetLibrary() throws Exception {
		Library library=new Library("test_lib");
		ResponseEntity<Library> savedLibrary = createNewLibrary(library);
		
	    ResponseEntity<Book> response = getSavedLibrary(savedLibrary);
	    
	    String expected = "{\"id\":1,\"libraryName\":\"test_lib\"}";
	    assertTrue(expected.equals(response), "messages are equal");
	  }

	private ResponseEntity<Book> getSavedLibrary(ResponseEntity<Library> savedLibrary) {
		ResponseEntity<Book> response = restTemplate.exchange(
	          createURLWithPort("/api/v1/library/"+savedLibrary.getBody().getId()), HttpMethod.GET, null, Book.class);
		return response;
	}
	 
	 @Test
	 @Ignore
	 public void deleteLibrary() {
		 Library library=new Library("test_lib");
		 ResponseEntity<Library> savedLibrary = createNewLibrary(library);
		 
	    ResponseEntity<String> response = deleteLibrary(savedLibrary);
	    
	    String expected="Deleted Library Id as : "+savedLibrary.getBody().getId();
	    assertTrue(expected.equals(response), "messages are equal");
	    
	 }

	private ResponseEntity<Library> createNewLibrary(Library library) {
		ResponseEntity<Library> savedLibrary = restTemplate.exchange(
	          createURLWithPort("/api/v1/library"),
	          HttpMethod.POST, new HttpEntity(library, new HttpHeaders()), Library.class);
		return savedLibrary;
	}

	private ResponseEntity<String> deleteLibrary(ResponseEntity<Library> savedLibrary) {
		ResponseEntity<String> response = restTemplate.exchange(
	          createURLWithPort("/api/v1/library/"+savedLibrary.getBody().getId()),
	          HttpMethod.DELETE, null, String.class);
		return response;
	}
	 
	 private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }
	
}
