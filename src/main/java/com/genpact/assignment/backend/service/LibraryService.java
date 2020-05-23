package com.genpact.assignment.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Library;

@Service
public interface LibraryService {
	List<Library> getAllLibraries();
	Library getLibraryById(Long id) throws LibraryNotFoundException;
	void updateLibraryById(Library library);
	Library saveLibrary(Library library);
	
	boolean deleteLibrary(Long libraryId) throws LibraryNotFoundException;
}
