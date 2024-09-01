package com.possumus.apiconverter.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NumeroInvalidoException extends RuntimeException{

    private String mensajeError;
    public NumeroInvalidoException(String mensajeError){
        super();
        this.mensajeError=mensajeError;
    }
}
