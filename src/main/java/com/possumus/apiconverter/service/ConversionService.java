package com.possumus.apiconverter.service;

import com.possumus.apiconverter.dto.ConversionDTO;
import com.possumus.apiconverter.dto.ConversionResponseDTO;

import java.util.Optional;

public interface ConversionService {

    public Optional<ConversionResponseDTO> convertDecimalRomano (Integer value);

    public Optional<ConversionResponseDTO> convertRomanoDecimal (String value);

}
