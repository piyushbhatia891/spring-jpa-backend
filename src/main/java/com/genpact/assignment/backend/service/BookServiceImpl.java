package com.genpact.assignment.backend.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.pool.DBConnectionPool;
import com.genpact.assignment.backend.repository.BooksRepository;
import com.genpact.assignment.backend.repository.LibraryRepository;


public class BookServiceImpl implements BookService{
	
	@Autowired
	private BooksRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;
	
	@Autowired
	private DBConnectionPool dbConnectionPool;

	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) throws BookNotFoundException {
		Optional<Book> book=bookRepository.findById(id);
		if(!book.isPresent())
			throw new BookNotFoundException("Book Not Found for Id:"+id);
		return book.get();
		
	}


	@Override
	public List<Book> getAllBooksForALibrary(Long libraryId) {
		return bookRepository.findByLibraryId(libraryId);
	}

	@Override
	public Book getBookForALibraryAndBookId( Long bookId,Long libraryId) throws BookNotFoundException{
		Optional<Book> book=bookRepository.findByIdAndLibraryId(bookId, libraryId);
		if(!book.isPresent())
			throw new BookNotFoundException("Book Not Found for Id:"+bookId);
		return book.get();

	}

	@Override
	public Book saveBookForALibrary(Book book, Long libraryId) throws LibraryNotFoundException {
		return libraryRepository.findById(libraryId).map(library->{
			book.setLibrary(library);
			return bookRepository.save(book);
		}).orElseThrow(()-> new LibraryNotFoundException("Library id not found : "+libraryId));
	}

	@Override
	public Book updateBookForALibrary(Book book, Long libraryId, Long bookId) throws LibraryNotFoundException {
		if(!libraryRepository.existsById(libraryId))
			throw new LibraryNotFoundException("library not found for id : "+libraryId);
		return 
			bookRepository.findById(bookId).map(bookFromDb->{
				bookFromDb.setBookName(book.getBookName());
				bookFromDb.setBookDescription(book.getBookDescription());
				return bookRepository.save(bookFromDb);
		}).orElseThrow(()-> new LibraryNotFoundException("Library id not found : "+libraryId));

	}

	@Override
	public boolean deleteBookForALibrary(Long bookId, Long libraryId) throws BookNotFoundException {
		 return bookRepository.findByIdAndLibraryId(bookId, libraryId).map(book -> {
	            bookRepository.delete(book);
	            return true;
	        }).orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
		
	}

	@Override
	public boolean deleteBook(Long bookId) throws BookNotFoundException {
		return bookRepository.findById(bookId).map(book -> {
            bookRepository.delete(book);
            return true;
        }).orElseThrow(() -> new BookNotFoundException("Book not found with id " + bookId));
	}
	
	@Override
	public List<Book> getAllBooksFromConnectionPool() {
	Connection connection=dbConnectionPool.getDBConnection();
	ResultSet rs = null;
	PreparedStatement statement = null;
	List<Book> books=new ArrayList();
	if(connection!=null) {
		try {
		statement=connection.prepareStatement("Select * from books");
		rs= statement.executeQuery();
		while(rs.next()) {
			Book book=new Book();
			book.setBookName(rs.getString("book_name"));
			book.setBookDescription(rs.getString("book_description"));
			book.setId(rs.getLong("id"));
			books.add(book);
		}
		}catch(SQLException e)
		{
			System.out.print(e.getMessage());
			return new ArrayList();
		}
		finally {
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				System.out.print(e.getMessage());
			}
			dbConnectionPool.releaseDBConnection(connection);
		}
	}
	return books;
	}
		
		
	
}
