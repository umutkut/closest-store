package com.jumbo.closeststore.exceptions;


import com.jumbo.closeststore.constants.Constants;
import lombok.Getter;

public class InvalidLatitudeException extends RuntimeException {
    @Getter
    private final double latitude;

    public InvalidLatitudeException(String message, double latitude) {
        super(message);
        this.latitude = latitude;
    }

    public InvalidLatitudeException(double latitude) {
        super(String.format(Constants.INVALID_LATITUDE_MESSAGE, latitude));
        this.latitude = latitude;
    }
}
