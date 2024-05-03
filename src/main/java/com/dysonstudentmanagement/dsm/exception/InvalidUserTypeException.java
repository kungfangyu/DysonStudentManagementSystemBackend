package com.dysonstudentmanagement.dsm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
InvalidUserTypeException

A custom exception class that allows a response status to be passed to the api client when thrown.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidUserTypeException extends RuntimeException{
    public InvalidUserTypeException(String message) { super(message); }
}
