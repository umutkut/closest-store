package com.jumbo.closeststore.dto;

import com.jumbo.closeststore.entity.valueobject.LocationType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Store DTO", description = "Store Data Transfer Object")
public class StoreDTO {
    private long id;
    private AddressDTO address;
    private LocationDTO location;
    private LocationType locationType;
}
