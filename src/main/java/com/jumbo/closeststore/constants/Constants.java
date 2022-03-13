package com.jumbo.closeststore.constants;

public class Constants {
    public static final Integer DEFAULT_NUMBER_OF_STORES = 5;

    public static final Double LATITUDE_MAX_VALUE = 90.0;
    public static final Double LATITUDE_MIN_VALUE = -90.0;
    public static final Double LONGITUDE_MAX_VALUE = 180.0;
    public static final Double LONGITUDE_MIN_VALUE = -180.0;

    public static final String INVALID_LATITUDE_MESSAGE = "Invalid latitude value : %f. Value should be in the interval of [-90, 90]";
    public static final String INVALID_LONGITUDE_MESSAGE = "Invalid longitude value : %f. Value should be in the interval of [-180, 180]";
    public static final String ADDRESS_STREET_FORMAT = "%s %s %s";

    public static final String UNSUPPORTED_LOCATION_TYPE_MESSAGE = "Unsupported location type %s";
    public static final String UNSUPPORTED_LOCATION_TYPE_VALUE_MESSAGE = "Unsupported location type with value %d";

    private Constants() {
        throw new IllegalStateException("Constant class");
    }
}
