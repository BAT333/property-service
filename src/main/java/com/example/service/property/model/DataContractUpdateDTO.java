package com.example.service.property.model;

import java.math.BigDecimal;

public record DataContractUpdateDTO(
        BigDecimal price,

        String description
) {
}
