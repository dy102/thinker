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
    private ResponseEntity<Throwable> handleIOException(IOException e) {
        Throwable cause = e.getCause();
        if (cause != null) {
            return new ResponseEntity<>(cause, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
