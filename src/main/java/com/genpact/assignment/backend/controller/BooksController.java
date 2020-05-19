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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.service.BookService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class BooksController {

	
	
	@Autowired
	private BookService booksService;
	
	//Fetch the list of all books
	@GetMapping(path = "/books")
	public List<Book> getAllBooks() {
		return booksService.getAllBooks();
	}
	
	//Fetch the list of all books
	@GetMapping(path = "/books/{bookId}")
	public ResponseEntity<Book> getBook(@PathVariable(value = "bookId")Long bookId) throws BookNotFoundException {
		Book book=booksService.getBookById(bookId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(book);
	}
	
	//Fetch the list of all books for specific libraryId
	@GetMapping(path = "/library/{libraryId}/books")
	public List<Book> getBooks(@PathVariable(value = "libraryId") Long libraryId) {
		return booksService.getAllBooksForALibrary(libraryId);
	}
	
	//Fetch the book with specific id for specific libraryId 
	@GetMapping(path = "/library/{libraryId}/book/{id}")
	public ResponseEntity<Book> getBookForId(@PathVariable(value = "libraryId")
	Long libraryId,@PathVariable(value = "id")Long bookId)
	throws BookNotFoundException
			{
		Book book=booksService.getBookForALibraryAndBookId(bookId, libraryId);
		return ResponseEntity.ok().body(book);
	}
	
	//Create new book in specific library
	@PostMapping(path ="/library/{libraryId}/books") 
	public ResponseEntity<Book> addNewBook(@Validated @RequestBody Book book,
			@PathVariable(value = "libraryId") Long libraryId)
	throws LibraryNotFoundException{
		Book savedBook=booksService.saveBookForALibrary(book, libraryId);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(savedBook);
	}

	//Update existing book in specific library
	@PutMapping(path ="/library/{libraryId}/book/{bookId}") 
	public ResponseEntity<String> updateExistingBook(@Validated @RequestBody Book book,
			@PathVariable(value = "libraryId") Long libraryId,@PathVariable(value = "bookId")Long bookId)
	throws LibraryNotFoundException{
		Book savedBook=booksService.updateBookForALibrary(book, libraryId, bookId);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Updated Book Id as : "+savedBook.getId());
	}
	
	//Delete existing book in specific library
	@DeleteMapping(path ="/library/{libraryId}/book/{bookId}") 
		public ResponseEntity<String> deleteExistingBook(@Validated @RequestBody Book book,
				@PathVariable(value = "libraryId") Long libraryId,@PathVariable(value = "bookId")Long bookId)
		throws BookNotFoundException{
			boolean deleteBookFlag=booksService.deleteBookForALibrary(bookId, libraryId);
			if(deleteBookFlag)
				return ResponseEntity.status(HttpStatus.OK)
					.body("Deleted Book Id as : "+bookId);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("There was an error encountered on server");
					
		}
	
	//Delete existing book in specific library
		@DeleteMapping(path ="/books/{bookId}") 
			public ResponseEntity<String> deleteExistingBook(@Validated @RequestBody Book book,
					@PathVariable(value = "bookId")Long bookId)
			throws BookNotFoundException{
				boolean deleteBookFlag=booksService.deleteBook(bookId);
				if(deleteBookFlag)
					return ResponseEntity.status(HttpStatus.OK)
						.body("Deleted Book Id as : "+bookId);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("There was an error encountered on server");
						
			}
	
}
