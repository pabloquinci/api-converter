package com.possumus.apiconverter;

import com.possumus.apiconverter.config.ConversionConfig;
import com.possumus.apiconverter.exception.NumeroInvalidoException;
import com.possumus.apiconverter.service.ConversionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.context.ContextConfiguration;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ContextConfiguration(classes = {ConversionConfig.class})
@ExtendWith(MockitoExtension.class)

public class ConversionServiceTest {


    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    private static Map<Character, Integer> romanoDecimalValues;

    ConversionServiceImpl conversionService;

    @BeforeAll
    public static void setUp() {

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

        romanoDecimalValues=Map.ofEntries(
                entry('I',1),
                entry('V',5),
                entry('X',10),
                entry('L',50),
                entry('C',100),
                entry('D',500),
                entry('M',1000));

        }

    @Test()
    void whenConversionADecimalOK() {
        conversionService=new ConversionServiceImpl(sortedMap,romanoDecimalValues);

        int[] numbers = {10, 340, 2250,5998};

        assertAll("numeros",
                () -> assertEquals(numbers[0], conversionService.convertRomanoDecimal("X").get().getConvertedValue()),
                () -> assertEquals(numbers[1],  conversionService.convertRomanoDecimal("CCCXL").get().getConvertedValue()),
                () -> assertEquals(numbers[2],  conversionService.convertRomanoDecimal("MMCCL").get().getConvertedValue()),
                () -> assertEquals(numbers[3],  conversionService.convertRomanoDecimal("MMMMMCMXCVIII").get().getConvertedValue())
        );

    }

    @Test()
    void whenConversionARomanoOK() {
        conversionService=new ConversionServiceImpl(sortedMap,romanoDecimalValues);

        String[] numbers = {"XXVI", "MMC", "MMCMLX"};

        assertAll("numeros",
                () -> assertEquals(numbers[0], conversionService.convertDecimalRomano(26).get().getConvertedValue()),
                () -> assertEquals(numbers[1],  conversionService.convertDecimalRomano(2100).get().getConvertedValue()),
                () -> assertEquals(numbers[2],  conversionService.convertDecimalRomano(2960).get().getConvertedValue()));

    }

    @Test()
    void whenConvertionThrowsException() {
        conversionService=new ConversionServiceImpl(sortedMap,romanoDecimalValues);

        NumeroInvalidoException exception = assertThrows(NumeroInvalidoException.class, () -> {
            conversionService.convertRomanoDecimal("MMMMMCMXCVIITI");
        });
        assertTrue(exception.getError()!=null);
    }
}
