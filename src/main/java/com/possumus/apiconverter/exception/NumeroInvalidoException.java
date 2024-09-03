package com.possumus.apiconverter.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NumeroInvalidoException extends RuntimeException{

    private ErrorResponse error;
    public NumeroInvalidoException(ErrorResponse error){
        super();
        this.error=error;
    }
}
