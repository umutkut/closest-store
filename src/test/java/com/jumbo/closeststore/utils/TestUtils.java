package com.jumbo.closeststore.utils;

import com.jumbo.closeststore.dto.StoreDTO;
import com.jumbo.closeststore.entity.valueobject.Location;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtils {

    public static void assertListIsCorrectOrder(List<StoreDTO> stores, Location location) {
        double prevDistance = 0;
        for (StoreDTO store : stores) {
            double currDistance = GISUtil.distance(store.getLocation().getLatitude(), store.getLocation().getLongitude(),
                    location.getLatitude(), location.getLongitude());
            assertTrue(prevDistance <= currDistance);
            prevDistance = currDistance;
        }
    }
}
