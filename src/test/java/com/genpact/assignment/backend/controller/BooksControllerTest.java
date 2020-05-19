package com.genpact.assignment.backend.controller;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import com.genpact.assignment.backend.BackendApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,
classes = BackendApplication.class)
public class BooksControllerTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Sql({ "schema.sql", "data.sql" })
    @Test
    public void testAllBooks() 
    {
        assertTrue(
                this.restTemplate
                    .getForObject("http://localhost:" + port + "api/v1/books", ArrayList.class)
                    .size() == 1);
    }
	
}
