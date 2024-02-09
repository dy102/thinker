package com.example.thinker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IOException.class})
    private ResponseEntity<String> handleIOException(IOException e) {
        return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
