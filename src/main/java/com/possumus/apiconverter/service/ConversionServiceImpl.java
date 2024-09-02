package com.possumus.apiconverter.service;

import com.possumus.apiconverter.dto.ConversionADecimalResponseDTO;
import com.possumus.apiconverter.dto.ConversionDTO;
import com.possumus.apiconverter.dto.ConversionResponseDTO;
import com.possumus.apiconverter.exception.ErrorResponse;
import com.possumus.apiconverter.exception.NumeroInvalidoException;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.expression.Lists;

import java.util.*;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    Map<Integer, String> decimalRomanoValues;

    @Autowired
    Map<Character, Integer>precedenciaSimbolos ;

    @Override
    public Optional<ConversionResponseDTO> convertDecimalRomano(Integer valorDecimal) {
        int divisor=(int)Math.pow(Double.valueOf(10.0),Double.valueOf(valorDecimal.toString().length())-1);
        StringBuilder valorRomano = new StringBuilder();
        int valorEntero=0;
        if(valorDecimal < 0) throw new NumeroInvalidoException(ErrorResponse.builder().message("ERROR: se ha ingresado un numero negativo").build());
        if(decimalRomanoValues.containsKey(valorDecimal)){
            return Optional.of(ConversionResponseDTO.builder().convertedValue(decimalRomanoValues.get(valorDecimal)).build());

        }
        while (valorDecimal > divisor){
                if ((valorDecimal / divisor) != 0) {
                    valorEntero=valorDecimal-(valorDecimal%divisor);
                    if((valorEntero / divisor!=9) && (valorEntero / divisor!=4) &&(valorEntero / divisor!=5)){
                        String concat= divisor==1000 ?"M" :decimalRomanoValues.get(valorEntero/(valorDecimal/divisor));
                        concat=concat.repeat(valorDecimal/divisor);
                        valorRomano.append(concat);
                    }else valorRomano.append(decimalRomanoValues.get(valorEntero));
                }
            valorDecimal = valorDecimal % divisor;
            divisor = divisor / 10;
        }
        valorRomano.append(decimalRomanoValues.get(valorDecimal));
        return Optional.of(ConversionResponseDTO.builder().convertedValue(valorRomano.toString()).build());
    }


    public Boolean cadenaValida(String value){
        return value.chars()
                .mapToObj(c -> (char) c)
                .toList().stream().allMatch(character -> precedenciaSimbolos.containsKey(character));
    }

    @Override
    public Optional<ConversionADecimalResponseDTO> convertRomanoDecimal(String value) {
        //MMMCCCI OK
        //CCMMMCCI ERROR
        //IIMMMCCI ERROR
        //MMMCCIV OK
        //MMMCVIV
        if(!cadenaValida(value)) throw new NumeroInvalidoException(ErrorResponse.builder().message("ERROR: se ha ingresado un numero con caracteres invalidos").build());
        Integer valorDecimal =0;
        Integer length = value.length();

        for (int i = 0; i < length; i++) {
            Integer valorActual = precedenciaSimbolos.get(value.charAt(i));
            Integer valorSiguiente = (i + 1 < length) ? precedenciaSimbolos.get(value.charAt(i + 1)) : 0;

            if (valorActual < valorSiguiente) {
                valorDecimal -= valorActual;
            } else {
                valorDecimal += valorActual;
            }

        }

        return Optional.of(ConversionADecimalResponseDTO.builder().convertedValue(valorDecimal).build());
    }
}
