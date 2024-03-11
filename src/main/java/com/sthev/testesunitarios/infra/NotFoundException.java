package com.sthev.testesunitarios.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class NotFoundException {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionDetails> responseStatusException (ResponseStatusException exception){
        ExceptionDetails exceptionDetails = new ExceptionDetails(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDetails);
    }
}
