package com.dysonstudentmanagement.dsm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidUserTypeException extends RuntimeException{
    public InvalidUserTypeException(String message) { super(message); }
}
