package com.possumus.apiconverter.service;

import com.possumus.apiconverter.dto.ConversionADecimalResponseDTO;
import com.possumus.apiconverter.dto.ConversionResponseDTO;
import com.possumus.apiconverter.exception.ErrorResponse;
import com.possumus.apiconverter.exception.NumeroInvalidoException;
import lombok.extern.java.Log;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Log
public class ConversionServiceImpl implements ConversionService {

   // @Autowired
    Map<Integer, String> decimalRomanoValues;

   // @Autowired
    Map<Character, Integer> romanoDecimalValues;

    @Autowired
    public ConversionServiceImpl(Map<Integer, String> decimalRomanoValues, Map<Character, Integer> romanoDecimalValues) {
        this.decimalRomanoValues = decimalRomanoValues;
        this.romanoDecimalValues = romanoDecimalValues;
    }

    public static Integer DIVISOR=1000;
    public static Integer OFFSET_DIVISOR=10;


    @Override
    public Optional<ConversionResponseDTO> convertDecimalRomano(Integer valorDecimal) {
        log.info("Servicio Conversion de valor Decimal a Romano");
        StringBuilder valorRomano = new StringBuilder();
        Integer divisor = DIVISOR;
        List<Map.Entry<Integer, String>> entradas = decimalRomanoValues.entrySet().stream().toList();
        Integer i = decimalRomanoValues.size() - 1;
        Integer divisorActual = divisor;
        Integer valorSuperior = 0;
        Integer valorInferior = 0;

        if (String.valueOf(valorDecimal).length() >= 4) {
            valorRomano.append(Strings.repeat("M", valorDecimal / 1000));
            valorDecimal = valorDecimal % 1000;
        }

        while (valorDecimal >= OFFSET_DIVISOR) {
            valorSuperior = entradas.get(i).getKey();
            valorInferior = entradas.get(i - 1).getKey();
            divisorActual = String.valueOf(valorSuperior).length() > String.valueOf(valorInferior).length() ? divisorActual / 10 : divisorActual;
            Integer valorActual = (valorDecimal / divisorActual) * divisorActual;
            if (valorActual < valorInferior) {
                i--;
                continue;
            }

            String valorRomanoActual = ((valorActual - valorInferior) / divisorActual) > 3
                    ? decimalRomanoValues.get(divisorActual) + decimalRomanoValues.get(valorSuperior)
                    : decimalRomanoValues.get(valorInferior) + Strings.repeat(decimalRomanoValues.get(divisorActual), (valorActual - valorInferior) / divisorActual);
            valorRomano.append(valorRomanoActual);
            divisor = divisor / OFFSET_DIVISOR;
            valorDecimal = valorDecimal % divisorActual;
            i--;
        }
        valorRomano.append(decimalRomanoValues.get(valorDecimal));
        return Optional.of(ConversionResponseDTO.builder().convertedValue(valorRomano.toString()).build());

    }

    public Boolean cadenaValida(String value) {
        return value.chars()
                .mapToObj(c -> (char) c)
                .toList().stream().allMatch(character -> romanoDecimalValues.containsKey(character));
    }

    @Override
    public Optional<ConversionADecimalResponseDTO> convertRomanoDecimal(String value) {
        //MMMCCCI OK
        //CCMMMCCI ERROR
        //IIMMMCCI ERROR
        //MMMCCIV OK
        //MMMCVIV
        log.info("Servicio Conversion de valor Romano a Decimal");

        if (!cadenaValida(value))
            throw new NumeroInvalidoException(ErrorResponse.builder().message("ERROR: se ha ingresado un numero con caracteres invalidos").build());
        Integer valorDecimal = 0;
        Integer length = value.length();

        for (int i = 0; i < length; i++) {
            Integer valorActual = romanoDecimalValues.get(value.charAt(i));
            Integer valorSiguiente = (i + 1 < length) ? romanoDecimalValues.get(value.charAt(i + 1)) : 0;

            if (valorActual < valorSiguiente) {
                valorDecimal -= valorActual;
            } else {
                valorDecimal += valorActual;
            }

        }

        return Optional.of(ConversionADecimalResponseDTO.builder().convertedValue(valorDecimal).build());
    }
}
