package com.possumus.apiconverter.controller;

import com.possumus.apiconverter.exception.ErrorResponse;
import com.possumus.apiconverter.exception.NumeroInvalidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NumeroInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleNumeroInvalidoException(NumeroInvalidoException ex) {
        return ResponseEntity.internalServerError().body(ErrorResponse.builder().message(ex.getError().getMessage()).build());
    }
}
