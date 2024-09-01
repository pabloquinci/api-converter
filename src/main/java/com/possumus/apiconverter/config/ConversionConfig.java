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
    public Map<String, Integer> precedenciaSimbolos(){
        return Map.ofEntries(entry("",0),
                entry("I",1),
                entry("II",2),
                entry("III",3),
                entry("IV",4),
                entry("V",5),
                entry("VI",6),
                entry("VII",7),
                entry("VIII",8),
                entry("IX",9),
                entry("X",10),
                entry("XL",40),
                entry("L",50),
                entry("XC",90),
                entry("C",100),
                entry("CD",400),
                entry("D",500),
                entry("CM",900),
                entry("M",1000));
    }
}
