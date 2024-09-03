package com.possumus.apiconverter.controller;

import com.possumus.apiconverter.exception.ErrorResponse;
import com.possumus.apiconverter.exception.NumeroInvalidoException;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NumeroInvalidoException.class)
    public ResponseEntity<ErrorResponse> handleNumeroInvalidoException(NumeroInvalidoException ex) {
        log.info("Error en conversión" + ex.getError().getMessage());
        return ResponseEntity.internalServerError().body(ErrorResponse.builder().message(ex.getError().getMessage()).build());
    }
}
