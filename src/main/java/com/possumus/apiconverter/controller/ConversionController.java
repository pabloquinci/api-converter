package com.possumus.apiconverter.controller;

import com.possumus.apiconverter.dto.ConversionDTO;
import com.possumus.apiconverter.dto.ConversionResponseDTO;
import com.possumus.apiconverter.service.ConversionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("api/coversions")
public class ConversionController {

    @Autowired
    ConversionServiceImpl conversionService;


    @GetMapping("/getValueConverted/{value}")
    public ResponseEntity<ConversionResponseDTO> convert(@PathVariable Integer value){

        Optional<ConversionResponseDTO> responseConversion= this.conversionService.convertDecimalRomano(value);

        if(responseConversion.isPresent()){
            return ResponseEntity.ok(responseConversion.get());
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/romanoADecimal/{value}")
    public ResponseEntity<ConversionResponseDTO> convertRomanoADecimal(@PathVariable String value){

        Optional<ConversionResponseDTO> responseConversion= this.conversionService.convertRomanoDecimal(value);

        if(responseConversion.isPresent()){
            return ResponseEntity.ok(responseConversion.get());
        }
        return ResponseEntity.notFound().build();

    }
}
