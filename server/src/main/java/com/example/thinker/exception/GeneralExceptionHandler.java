package com.example.thinker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    private ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({IOException.class})
    private ResponseEntity<String> handleIOException(IOException e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionAsString = sw.toString();
        return new ResponseEntity<>(exceptionAsString, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
