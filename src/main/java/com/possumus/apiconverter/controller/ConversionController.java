package com.possumus.apiconverter.controller;

import com.possumus.apiconverter.dto.ConversionADecimalResponseDTO;
import com.possumus.apiconverter.dto.ConversionDTO;
import com.possumus.apiconverter.dto.ConversionResponseDTO;
import com.possumus.apiconverter.service.ConversionServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("api/conversions")
@Log
public class ConversionController {

    @Autowired
    ConversionServiceImpl conversionService;

    @GetMapping("/decimalARomano/{value}")
    public ResponseEntity<ConversionResponseDTO> convertDecimalARomano(@PathVariable Integer value) {
        log.info("/decimalARomano/" + value);
        Optional<ConversionResponseDTO> responseConversion = this.conversionService.convertDecimalRomano(value);

        if (responseConversion.isPresent()) {
            return ResponseEntity.ok(responseConversion.get());
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/romanoADecimal/{value}")
    public ResponseEntity<ConversionADecimalResponseDTO> convertRomanoADecimal(@PathVariable String value) {
        log.info("/romanoADecimal/" + value);

        Optional<ConversionADecimalResponseDTO> responseConversion = this.conversionService.convertRomanoDecimal(value);

        if (responseConversion.isPresent()) {
            return ResponseEntity.ok(responseConversion.get());
        }
        return ResponseEntity.notFound().build();

    }

}
