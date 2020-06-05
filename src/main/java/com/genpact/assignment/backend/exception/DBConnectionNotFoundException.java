package com.genpact.assignment.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class DBConnectionNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

    public DBConnectionNotFoundException(String message){
        super(message);
    }
}
