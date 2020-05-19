package com.genpact.assignment.backend.service;

import java.util.List;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;


public interface BookService {
	List<Book> getAllBooks();
	Book getBookById(Long id) throws BookNotFoundException;
	
	List<Book> getAllBooksForALibrary(Long libraryId);
	Book getBookForALibraryAndBookId(Long bookId,Long libraryId) throws BookNotFoundException;
	Book saveBookForALibrary(Book book,Long libraryId) throws LibraryNotFoundException;
	Book updateBookForALibrary(Book book,Long libraryId,Long bookId) throws LibraryNotFoundException;
	

}