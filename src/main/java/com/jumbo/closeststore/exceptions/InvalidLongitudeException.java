package com.jumbo.closeststore.exceptions;


import com.jumbo.closeststore.constants.Constants;
import lombok.Getter;

public class InvalidLongitudeException extends RuntimeException {
    @Getter
    private final double longitude;

    public InvalidLongitudeException(String message, double longitude) {
        super(message);
        this.longitude = longitude;
    }

    public InvalidLongitudeException(double longitude) {
        super(String.format(Constants.INVALID_LONGITUDE_MESSAGE, longitude));
        this.longitude = longitude;
    }
}
