package com.genpact.assignment.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.genpact.assignment.backend.model.Book;

@Repository
public interface BooksRepository extends CrudRepository<Book, Long>{

	Book findByBookName(String bookName);
	List<Book> findByLibraryId(Long libraryId);
	Optional<Book> findByIdAndLibraryId(Long bookId,Long libraryId);
}
