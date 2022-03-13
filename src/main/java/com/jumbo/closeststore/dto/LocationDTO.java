package com.jumbo.closeststore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class LocationDTO {
    private double longitude;
    private double latitude;
}
