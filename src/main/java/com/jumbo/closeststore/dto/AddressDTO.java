package com.jumbo.closeststore.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {
    private String name;
    private String city;
    private String postalCode;
    private String street;
    private int complexNumber;

}
