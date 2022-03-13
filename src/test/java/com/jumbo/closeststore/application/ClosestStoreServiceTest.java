package com.jumbo.closeststore.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.closeststore.constants.Constants;
import com.jumbo.closeststore.dto.ClosestStoreRequest;
import com.jumbo.closeststore.entity.Store;
import com.jumbo.closeststore.entity.valueobject.LocationType;
import com.jumbo.closeststore.infrastructure.IStoreReader;
import com.jumbo.closeststore.infrastructure.model.StoreModel;
import com.jumbo.closeststore.utils.TestUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ClosestStoreServiceTest {

    private static Stream<Arguments> latLongs() {
        return Stream.of(
                arguments(52.662767, 5.625297),
                arguments(52, 5),
                arguments(52.0, 5.0),
                arguments(51.874272, 6.25297),
                arguments(75.0, 57.625297),
                arguments(55.2, 15.625297),
                arguments(0.0, 0.0)
        );
    }

    @ParameterizedTest
    @MethodSource("latLongs")
    void findClosestStoreOnlyLatLongSpecified(double latitude, double longitude) throws IOException {
        //Given
        IStoreReader storeCache = Mockito.mock(IStoreReader.class);
        Mockito.when(storeCache.getAll()).thenReturn(prepareStores());
        var request = new ClosestStoreRequest(Constants.DEFAULT_NUMBER_OF_STORES, latitude, longitude, LocationType.ALL_VALUES);

        //Triggering Method
        ClosestStoreService closestStoreService = new ClosestStoreService(storeCache);
        var result = closestStoreService.findClosestStores(request);

        //Assertions
        TestUtils.assertListIsCorrectOrder(result, request.getLocation());
        assertEquals(Constants.DEFAULT_NUMBER_OF_STORES, result.size());
    }


    List<Store> prepareStores() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var storeModels = mapper.readValue(slurpResources("sampleStores.json"), new TypeReference<List<StoreModel>>() {
        });

        return storeModels.stream()
                .map(Store::fromStoreModel)
                .collect(Collectors.toList());
    }

    String slurpResources(String fileName) throws IOException {
        var classLoader = getClass().getClassLoader();
        return new String(Objects.requireNonNull(classLoader.getResourceAsStream(fileName)).readAllBytes());
    }

}