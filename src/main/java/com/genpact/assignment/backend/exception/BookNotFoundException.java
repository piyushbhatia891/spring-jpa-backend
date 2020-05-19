package com.genpact.assignment.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message){
        super(message);
    }
}
