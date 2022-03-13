package com.jumbo.closeststore.entity.valueobject;

import com.jumbo.closeststore.constants.Constants;
import com.jumbo.closeststore.dto.LocationDTO;
import com.jumbo.closeststore.exceptions.InvalidLatitudeException;
import com.jumbo.closeststore.exceptions.InvalidLongitudeException;
import com.jumbo.closeststore.utils.GISUtil;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        if (latitude < Constants.LATITUDE_MIN_VALUE || latitude > Constants.LATITUDE_MAX_VALUE)
            throw new InvalidLatitudeException(latitude);
        if (longitude < Constants.LONGITUDE_MIN_VALUE || longitude > Constants.LONGITUDE_MAX_VALUE)
            throw new InvalidLongitudeException(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setLongitude(double longitude) {
        if (longitude < Constants.LONGITUDE_MIN_VALUE || longitude > Constants.LONGITUDE_MAX_VALUE)
            throw new InvalidLongitudeException(longitude);

        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        if (latitude < Constants.LATITUDE_MIN_VALUE || latitude > Constants.LATITUDE_MAX_VALUE)
            throw new InvalidLatitudeException(latitude);

        this.latitude = latitude;
    }

    public double distanceFrom(Location location) {
        return GISUtil.distance(this.latitude, this.longitude, location.getLatitude(), location.getLongitude());
    }

    public LocationDTO toDTO() {
        return new LocationDTO(longitude, latitude);
    }
}
