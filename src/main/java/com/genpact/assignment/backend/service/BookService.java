package com.genpact.assignment.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.DBConnectionNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;

@Service
public interface BookService {
	List<Book> getAllBooks();
	Book getBookById(Long id) throws BookNotFoundException;
	
	List<Book> getAllBooksForALibrary(Long libraryId);
	Book getBookForALibraryAndBookId(Long bookId,Long libraryId) throws BookNotFoundException;
	Book saveBookForALibrary(Book book,Long libraryId) throws LibraryNotFoundException;
	Book updateBookForALibrary(Book book,Long libraryId,Long bookId) throws LibraryNotFoundException;
	boolean deleteBookForALibrary(Long bookId,Long libraryId) throws BookNotFoundException;
	
	boolean deleteBook(Long bookId) throws BookNotFoundException;
	List<Book> getAllBooksFromConnectionPool()throws DBConnectionNotFoundException;
	

}
