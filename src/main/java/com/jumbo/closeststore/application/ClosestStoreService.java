package com.jumbo.closeststore.application;

import com.jumbo.closeststore.dto.ClosestStoreRequest;
import com.jumbo.closeststore.dto.StoreDTO;
import com.jumbo.closeststore.entity.Store;
import com.jumbo.closeststore.infrastructure.IStoreReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DependsOn(value = "jsonReader")
@Slf4j
public class ClosestStoreService {


    private final List<Store> stores;

    public ClosestStoreService(IStoreReader storeCache) {
        this.stores = storeCache.getAll();
    }


    public List<StoreDTO> findClosestStores(ClosestStoreRequest request) {
        log.debug("Searching for closest stores...");

        var closestStores = stores.stream()
                .sorted(Comparator
                        .comparingDouble(store -> store.distanceFrom(request.getLocation())))   // Sorts by the distance to the given location
                .filter(store -> store.isCompatibleForRequestedSet(request.getLocationTypes())) // Filters the stores by their location type
                .limit(request.getNumberOfStores())                                             // Limits the output by given numberOfStores
                .map(Store::toDTO)                                                              // Converts entities to DTOs
                .collect(Collectors.toList());

        log.debug("Closest stores found: {}", closestStores);

        return closestStores;
    }
}
