package com.genpact.assignment.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.repository.BooksRepository;
import com.genpact.assignment.backend.repository.LibraryRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BooksRepository bookRepository;
	
	@Autowired
	private LibraryRepository libraryRepository;

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
	

}
