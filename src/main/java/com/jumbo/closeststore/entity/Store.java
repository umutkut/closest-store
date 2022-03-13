package com.jumbo.closeststore.entity;

import com.jumbo.closeststore.constants.Constants;
import com.jumbo.closeststore.dto.StoreDTO;
import com.jumbo.closeststore.entity.valueobject.Address;
import com.jumbo.closeststore.entity.valueobject.Location;
import com.jumbo.closeststore.entity.valueobject.LocationType;
import com.jumbo.closeststore.infrastructure.model.StoreModel;
import lombok.Data;

import java.util.Set;

@Data
public class Store {

    private long id;

    private Address address;

    private Location location;

    private LocationType locationType;

    private Store(long id, Address address, Location location, LocationType locationType) {
        this.id = id;
        this.address = address;
        this.location = location;
        this.locationType = locationType;
    }

    public static Store fromStoreModel(StoreModel storeModel) {
        long id = storeModel.getSapStoreID();
        var address = new Address(storeModel.getAddressName(),
                storeModel.getCity(),
                storeModel.getPostalCode(),
                String.format(Constants.ADDRESS_STREET_FORMAT, storeModel.getStreet(), storeModel.getStreet2(), storeModel.getStreet3()),
                storeModel.getComplexNumber());
        var location = new Location(storeModel.getLatitude(),
                storeModel.getLongitude());

        return new Store(id, address, location, LocationType.fromString(storeModel.getLocationType()));
    }

    public StoreDTO toDTO() {
        return new StoreDTO(id, address.toDTO(), location.toDTO(), this.locationType);
    }

    public double distanceFrom(Location location) {
        return this.location.distanceFrom(location);
    }

    public boolean isCompatibleForRequestedSet(Set<LocationType> locationTypeSet) {
        return locationTypeSet.contains(locationType);
    }
}
