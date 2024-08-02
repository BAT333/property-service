package com.example.service.property.repository;

import com.example.service.property.domain.ContractStatus;
import com.example.service.property.domain.ContractType;
import com.example.service.property.domain.Propertie;
import com.example.service.property.domain.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PropertieRepository extends JpaRepository<Propertie,Long> {

    Page<Propertie> findByActiveTrue(Pageable pageable);
    Optional<Propertie> findByIdAndActiveTrue(Long id);
    boolean existsByIdAndActiveTrue(Long id);

    Page<Propertie> findByActiveTrueAndProprietor(Pageable pageable, Long proprietor);
    Page<Propertie> findByActiveTrueAndTypeAndStatus(ContractType type, ContractStatus available, Pageable pageable);
    Page<Propertie> findByActiveTrueAndTypeAndPropertyTypeInAndPriceGreaterThanEqualAndStatus(ContractType type, List<PropertyType> propertyType, BigDecimal first, ContractStatus available, Pageable pageable);
    Page<Propertie> findByActiveTrueAndTypeAndPropertyTypeInAndPriceBetweenAndStatus(ContractType type, List<PropertyType> propertyType, BigDecimal first, BigDecimal last, ContractStatus available, Pageable pageable);
    Page<Propertie>  findByActiveTrueAndTypeAndPropertyTypeInAndStatus(ContractType type, List<PropertyType> propertyType, ContractStatus available, Pageable pageable);
    Page<Propertie> findByActiveTrueAndTypeAndPriceGreaterThanEqualAndStatus(ContractType type, BigDecimal first, ContractStatus available, Pageable pageable);
    Page<Propertie> findByActiveTrueAndTypeAndPriceBetweenAndStatus(ContractType type, BigDecimal first, BigDecimal last, ContractStatus available, Pageable pageable);
    Optional<Propertie> findByIdAndActiveTrueAndStatus(Long purchase,ContractStatus available);


}
