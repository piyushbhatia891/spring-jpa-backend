package com.genpact.assignment.backend.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genpact.assignment.backend.model.Book;
import com.genpact.assignment.backend.model.Library;
import com.genpact.assignment.backend.repository.BooksRepository;
import com.genpact.assignment.backend.repository.LibraryRepository;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {
	
	@InjectMocks
	private LibraryService libraryService;
	
	@Mock
	private LibraryRepository libraryRepository;
	
	
	@Test
	@Ignore
	public void getLibraries() {
		Mockito.when(libraryRepository.findAll()).thenReturn((Iterable<Library>) any(Iterable.class));
		List<Library> returnedLibraries=libraryService.getAllLibraries();
		assertThat(returnedLibraries.size(),is(1));
		
	}
}
