package com.genpact.assignment.backend;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.genpact.assignment.backend.service.BookService;

@Profile("Test")
@Configuration
public class BookServiceConf {
	
	@Bean
	@Primary
	public BookService bookService() {
		return Mockito.mock(BookService.class);
	}

}
