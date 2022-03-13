package com.jumbo.closeststore.entity.valueobject;

import com.jumbo.closeststore.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Address {
    private String name;
    private String city;
    private String postalCode;
    private String street;
    private int complexNumber;

    public AddressDTO toDTO() {
        return AddressDTO.builder()
                .name(name)
                .city(city)
                .postalCode(postalCode)
                .street(street)
                .complexNumber(complexNumber).build();
    }
}
