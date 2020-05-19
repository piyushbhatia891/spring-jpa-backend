package com.genpact.assignment.backend.service;

import java.util.List;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;

public interface LibraryService {
	List<Library> getAllLibraries();
	Library getLibraryById(Long id) throws LibraryNotFoundException;
	void updateLibraryById(Library library);
	Library saveLibrary(Library library);
}
