package com.example.service.property.domain;

import com.example.service.property.modal.DataPropertieDTO;
import com.example.service.property.modal.DataUpdatePropertieDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Entity
@Table(name = "properties")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Propertie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantitys_rooms",nullable = false)
    private Integer quantityRoom;
    @Column(name = "prices",nullable = false)
    private BigDecimal price;
    @Column(name = "descriptions")
    private String description;
    @Column(name = "types",nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractType type;
    @Column(name = "properties_types",nullable = false)
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;
    @Column(name ="actives",nullable = false)
    private Boolean active = true;
    @Column(name = "proprietors",nullable = false)
    private Long proprietor;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address",nullable = false,unique = true)
    private Address address;
    @Column(name ="contract_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private ContractStatus status;



    public Propertie(DataPropertieDTO dto, Long proprietor) {
        this.active =true;
        this.quantityRoom = dto.quantityRoom();
        this.price = dto.price();
        this.description= dto.description();
        this.type = dto.type();
        this.propertyType = dto.propertyType();
        this.proprietor = proprietor;
        this.address = new Address(dto.address());
        this.status = ContractStatus.AVAILABLE;
    }

    public void update(DataUpdatePropertieDTO dto) {
        if(dto.quantityRoom() != null){this.quantityRoom = dto.quantityRoom();}
        if(dto.propertyType() != null){this.propertyType = dto.propertyType();}
        if(dto.price() != null){this.price = dto.price();}
        if(dto.description() != null){this.description = dto.description();}
        if(dto.type() != null){this.type = dto.type();}
        if(dto.address() != null){ this.address.update(dto.address());}
    }

    public void delete() {
        this.active = false;
        this.address.delete();
    }

    public void contract(Long proprietor, ContractType contractType) {
        if(contractType == ContractType.SELL){
            this.proprietor = proprietor;
            this.status = ContractStatus.SOLD;
        }else{
            this.status = ContractStatus.LEASED;
        }
    }
}
