package com.genpact.assignment.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long> {

	Library findByLibraryName(String libraryName);
}
