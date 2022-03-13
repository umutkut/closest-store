package com.jumbo.closeststore.dto;

import com.jumbo.closeststore.constants.Constants;
import com.jumbo.closeststore.entity.valueobject.Location;
import com.jumbo.closeststore.entity.valueobject.LocationType;
import lombok.Getter;
import lombok.ToString;
import org.springframework.lang.Nullable;

import java.util.EnumSet;
import java.util.Set;

@ToString
@Getter
public class ClosestStoreRequest {

    private final Integer numberOfStores;

    private final Location location;

    private final Set<LocationType> locationTypes;

    public ClosestStoreRequest(@Nullable Integer numberOfStores, Double latitude, Double longitude, @Nullable Set<Integer> locationTypeValues) {
        this.numberOfStores = numberOfStores == null ? Constants.DEFAULT_NUMBER_OF_STORES : numberOfStores;
        this.location = new Location(latitude, longitude);
        this.locationTypes = EnumSet.noneOf(LocationType.class);
        locationTypeValues = locationTypeValues == null ? LocationType.ALL_VALUES : locationTypeValues;

        for (Integer value : locationTypeValues) {
            locationTypes.add(LocationType.fromValue(value));
        }
    }

}
