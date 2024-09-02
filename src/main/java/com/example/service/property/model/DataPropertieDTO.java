package com.example.service.property.model;

import com.example.service.property.domain.ContractType;
import com.example.service.property.domain.PropertyType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataPropertieDTO(
        @NotNull
        @Min(1)
        Integer quantityRoom,
        @DecimalMin(value = "0.0",inclusive = false)
        @Digits(integer=3, fraction=12)
        @NotNull
        BigDecimal price,
        String description,
        @NotNull
        ContractType type,
        @NotNull
        PropertyType propertyType,
        @Valid
        DataAddressDTO address
) {
}
