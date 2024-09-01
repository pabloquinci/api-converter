package com.possumus.apiconverter.controller;

import com.possumus.apiconverter.exception.NumeroInvalidoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NumeroInvalidoException.class)
    public ResponseEntity<String> handleNumeroInvalidoException(NumeroInvalidoException e) {
        return ResponseEntity.internalServerError().body(e.getMensajeError());
    }
}
