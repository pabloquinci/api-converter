package com.possumus.apiconverter;

import com.possumus.apiconverter.exception.NumeroInvalidoException;
import com.possumus.apiconverter.service.ConversionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class ConversionServiceTest {
    @Autowired
    ConversionServiceImpl conversionService;

    String conversionOk;


    @Test()
    void whenConversionARomanoOK() {
        int[] numbers = {10, 340, 2250};

        assertAll("numeros",
                () -> assertEquals(numbers[0], conversionService.convertRomanoDecimal("X").get().getConvertedValue()),
                () -> assertEquals(numbers[1],  conversionService.convertRomanoDecimal("CCCXL").get().getConvertedValue()),
                () -> assertEquals(numbers[2],  conversionService.convertRomanoDecimal("MMCCL").get().getConvertedValue())
        );

    }

    @Test()
    void whenConvertionThrowsException() {
        NumeroInvalidoException exception = assertThrows(NumeroInvalidoException.class, () -> {
            conversionService.convertRomanoDecimal("CCLT");
        });


        assertTrue(exception.getError()!=null);
    }
}
