package com.genpact.assignment.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;
import com.genpact.assignment.backend.repository.BooksRepository;
import com.genpact.assignment.backend.repository.LibraryRepository;

@Component
public class InitialDataPopulator  implements CommandLineRunner{

	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	LibraryRepository libraryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		//Deleting all previous data
		booksRepository.deleteAll();
		libraryRepository.deleteAll();
		
		//Adding new data in table
		
		Library library=new Library("first library");
		libraryRepository.save(library);
		libraryRepository.save(new Library("second library"));
		booksRepository.save(new Book("first title", "First book description",library));
		booksRepository.save(new Book("second title", "Second book description",library));
		
		
	}

}
