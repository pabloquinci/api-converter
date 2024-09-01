package com.possumus.apiconverter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ConversionResponseDTO {

    private String convertedValue;
}
