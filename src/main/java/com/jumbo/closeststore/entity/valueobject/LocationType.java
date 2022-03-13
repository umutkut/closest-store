package com.jumbo.closeststore.entity.valueobject;

import com.jumbo.closeststore.constants.Constants;
import com.jumbo.closeststore.exceptions.InvalidLocationTypeException;

import java.util.Locale;
import java.util.Set;

public enum LocationType {
    SUPERMARKT(0), SUPERMARKT_PUP(1), PUP(2);

    public static final Set<Integer> ALL_VALUES = Set.of(LocationType.SUPERMARKT.value, LocationType.SUPERMARKT_PUP.value, LocationType.PUP.value);

    public final Integer value;

    LocationType(Integer value) {
        this.value = value;
    }

    public static LocationType fromValue(Integer value) {
        switch (value) {
            case 0:
                return SUPERMARKT;
            case 1:
                return SUPERMARKT_PUP;
            case 2:
                return PUP;
            default:
                throw new InvalidLocationTypeException(String.format(Constants.UNSUPPORTED_LOCATION_TYPE_VALUE_MESSAGE, value));
        }
    }

    public static LocationType fromString(String locationTypeStr) {
        switch (locationTypeStr.toLowerCase(Locale.ROOT)) {
            case "supermarkt":
                return SUPERMARKT;
            case "supermarktpup":
                return SUPERMARKT_PUP;
            case "pup":
                return PUP;
            default:
                throw new InvalidLocationTypeException(String.format(Constants.UNSUPPORTED_LOCATION_TYPE_MESSAGE, locationTypeStr));
        }
    }
}
