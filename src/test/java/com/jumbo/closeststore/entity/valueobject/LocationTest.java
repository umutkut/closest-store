package com.jumbo.closeststore.entity.valueobject;

import com.jumbo.closeststore.exceptions.InvalidLatitudeException;
import com.jumbo.closeststore.exceptions.InvalidLongitudeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocationTest {

    @Test
    void create() {
        assertDoesNotThrow(() -> new Location(50.0, 50.0));
        assertThrows(InvalidLatitudeException.class, () -> new Location(5000.0, 5.0));
        assertThrows(InvalidLongitudeException.class, () -> new Location(5.0, 5000.0));
    }

    @Test
    void setLongitude() {
        Location location = new Location(50.5, 5.5);
        assertDoesNotThrow(() -> location.setLongitude(70.0));
        assertThrows(InvalidLongitudeException.class, () -> location.setLongitude(70000.0));
    }

    @Test
    void setLatitude() {
        Location location = new Location(50.5, 5.5);
        assertDoesNotThrow(() -> location.setLatitude(70.0));
        assertThrows(InvalidLatitudeException.class, () -> location.setLatitude(70000.0));
    }
}