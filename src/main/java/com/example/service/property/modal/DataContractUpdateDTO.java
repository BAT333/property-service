package com.example.service.property.modal;

import java.math.BigDecimal;

public record DataContractUpdateDTO(
        BigDecimal price,

        String description
) {
}
