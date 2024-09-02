package com.possumus.apiconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static java.util.Map.entry;

@Configuration
public class ConversionConfig {

    @Bean
    public Map<Integer, String> decimalRomanoValues(){
        return Map.ofEntries(entry(0,""),
                entry(1,"I"),
                entry(2,"II"),
                entry(3,"III"),
                entry(4,"IV"),
                entry(5,"V"),
                entry(6,"VI"),
                entry(7,"VII"),
                entry(8,"VIII"),
                entry(9,"IX"),
                entry(10,"X"),
                entry(40,"XL"),
                entry(50,"L"),
                entry(90,"XC"),
                entry(100,"C"),
                entry(400,"CD"),
                entry(500,"D"),
                entry(900,"CM"),
                entry(1000,"M"));
    }

    @Bean
    public Map<Character, Integer> precedenciaSimbolos(){
        return Map.ofEntries(
                entry('I',1),
                entry('V',5),
                entry('X',10),
                entry('L',50),
                entry('C',100),
                entry('D',500),
                entry('M',1000));
    }
}
