package com.genpact.assignment.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.assignment.backend.exception.BookNotFoundException;
import com.genpact.assignment.backend.exception.LibraryNotFoundException;
import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;
import com.genpact.assignment.backend.repository.LibraryRepository;

@Service
public class LibraryServiceImpl implements LibraryService{

	@Autowired
	private LibraryRepository libraryRepository;
	@Override
	public List<Library> getAllLibraries() {
		return (List<Library>) libraryRepository.findAll();
	}

	@Override
	public Library getLibraryById(Long id) throws LibraryNotFoundException {
		Optional<Library> library=libraryRepository.findById(id);
		if(!library.isPresent())
			throw new LibraryNotFoundException("Book Not Found for Id:"+id);
		return library.get();
	}

	@Override
	public void updateLibraryById(Library library) {
		libraryRepository.save(library);
		
	}

	@Override
	public Library saveLibrary(Library library) {
		return libraryRepository.save(library);
		
	}

	@Override
	public boolean deleteLibrary(Long libraryId) throws LibraryNotFoundException {
		return libraryRepository.findById(libraryId).map(library -> {
            libraryRepository.delete(library);
            return true;
        }).orElseThrow(() -> new LibraryNotFoundException("Library not found with id " + libraryId));
	}

}
