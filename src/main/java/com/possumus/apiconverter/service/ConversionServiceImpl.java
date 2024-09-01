package com.possumus.apiconverter.service;

import com.possumus.apiconverter.dto.ConversionDTO;
import com.possumus.apiconverter.dto.ConversionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    Map<Integer, String> decimalRomanoValues;

    @Autowired
    Map<String, Integer>precedenciaSimbolos ;

    @Override
    public Optional<ConversionResponseDTO> convertDecimalRomano(Integer valorDecimal) {
        ConversionResponseDTO responseDTO = ConversionResponseDTO.builder().build();
        int divisor=(int)Math.pow(Double.valueOf(10.0),Double.valueOf(valorDecimal.toString().length())-1);
        StringBuilder valorRomano = new StringBuilder();
        int valorEntero;
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

    @Override
    public Optional<ConversionResponseDTO> convertRomanoDecimal(String value) {
        //MMMCCCI OK
        //CCMMMCCI ERROR
        //IIMMMCCI ERROR
        //MMMCCIV OK
        //MMMCVIV
        StringBuilder valorDecimal = new StringBuilder();

        int contadorMaxSimbolosSuma=3;
        int contadorMaxSimbolosResta=1;
        String cadenaAux= String.valueOf(value.charAt(value.length()-1));
        for(int i=value.length()-1;i>0;i--){
            cadenaAux=value.substring(i-1,value.length());
            if(contadorMaxSimbolosSuma==0){
                throw new NumberFormatException("Error: maximo de simbolos iguales superado");
            }
            if(precedenciaSimbolos.containsKey(cadenaAux)){
                valorDecimal.append(precedenciaSimbolos.get(cadenaAux));
            }else if (precedenciaSimbolos.containsKey(cadenaAux.charAt(i)) && precedenciaSimbolos.containsKey(cadenaAux.charAt(i-1))){
                valorDecimal.append(precedenciaSimbolos.get(cadenaAux.charAt(i)).toString());
                valorDecimal.append(precedenciaSimbolos.get(cadenaAux.charAt(i-1)).toString());
            }
            cadenaAux="";
            i-=2;
        }


        return Optional.of(ConversionResponseDTO.builder().convertedValue(valorDecimal.toString()).build());
    }
}
