package com.example.service.property.model;
import com.example.service.property.domain.ContractType;
import com.example.service.property.domain.Propertie;
import com.example.service.property.domain.PropertyType;

import java.io.Serializable;
import java.math.BigDecimal;

public record DataPropertie(
        Long id,
        int quantityRoom,
        BigDecimal price,
        String description,
        ContractType type,
        PropertyType propertyType,
        DataAddressDTO address
) implements Serializable {
    public DataPropertie(Propertie propertie) {
        this(propertie.getId(), propertie.getQuantityRoom(), propertie.getPrice(),
                propertie.getDescription(), propertie.getType(),propertie.getPropertyType(),new DataAddressDTO(propertie.getAddress()));
    }
}
