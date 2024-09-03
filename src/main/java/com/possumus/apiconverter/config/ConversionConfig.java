package com.possumus.apiconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Map.entry;

@Configuration
public class ConversionConfig {

    @Bean
    public SortedMap<Integer, String> decimalRomanoValues() {


        SortedMap<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(0,"");
        sortedMap.put(1,"I");
        sortedMap.put(2,"II");
        sortedMap.put(3,"III");
        sortedMap.put(4,"IV");
        sortedMap.put(5,"V");
        sortedMap.put(6,"VI");
        sortedMap.put(7,"VII");
        sortedMap.put(8,"VIII");
        sortedMap.put(9,"IX");
        sortedMap.put(10,"X");
        sortedMap.put(50,"L");
        sortedMap.put(100,"C");
        sortedMap.put(500,"D");
        sortedMap.put(1000,"M");

        return sortedMap;

    }

    @Bean
    public Map<Character, Integer> romanoDecimalValues(){
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
