package com.jumbo.closeststore.controller;

import com.jumbo.closeststore.ClosestStoreApplication;
import com.jumbo.closeststore.constants.Constants;
import com.jumbo.closeststore.dto.StoreDTO;
import com.jumbo.closeststore.entity.valueobject.Location;
import com.jumbo.closeststore.entity.valueobject.LocationType;
import com.jumbo.closeststore.utils.TestUtils;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClosestStoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StoreControllerTest {

    @Autowired
    StoreController storeController;


    @LocalServerPort
    private int port;


    @Test
    void testClosestStoreEndPoint() throws IOException, InterruptedException, URISyntaxException {
        //Call endpoint
        HttpClient client = HttpClient.newHttpClient();
        var uri = new URIBuilder(createURL("/api/v1/stores/closest"))
                .addParameter("latitude", "55")
                .addParameter("longitude", "5")
                .build();
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        var response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //Assert
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }

    @Test
    void testClosestStoreEndPointInvalidLatitude() throws IOException, InterruptedException, URISyntaxException {
        //Call endpoint
        HttpClient client = HttpClient.newHttpClient();
        var uri = new URIBuilder(createURL("/api/v1/stores/closest"))
                .addParameter("latitude", "5500")
                .addParameter("longitude", "5")
                .build();
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        var response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.statusCode());
    }

    @Test
    void testClosestStoreEndPointInvalidLongitude() throws IOException, InterruptedException, URISyntaxException {
        //Call endpoint
        HttpClient client = HttpClient.newHttpClient();
        var uri = new URIBuilder(createURL("/api/v1/stores/closest"))
                .addParameter("latitude", "55")
                .addParameter("longitude", "5000")
                .build();
        var request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        var response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        //Assert
        assertEquals(HttpStatus.NOT_ACCEPTABLE.value(), response.statusCode());
    }

    @Test
    void testClosestStoreFunctionWithOnlyLatLong() {
        //Given
        double latitude = 50.5;
        double longitude = 5.5;

        //Call method
        var response = storeController.getClosestStores(latitude, longitude, Optional.empty(), Optional.empty());
        var stores = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(stores);
        assertEquals(Constants.DEFAULT_NUMBER_OF_STORES, stores.size());
        TestUtils.assertListIsCorrectOrder(stores, new Location(latitude, longitude));

    }

    @Test
    void testClosestStoreFunctionOnlyNumberOfStoresExist() {
        //Given
        double latitude = 50.5;
        double longitude = 5.5;
        int numberOfStores = 10;

        //Call method
        var response = storeController.getClosestStores(latitude, longitude, Optional.of(numberOfStores), Optional.empty());
        var stores = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(stores);
        assertEquals(numberOfStores, stores.size());
        TestUtils.assertListIsCorrectOrder(stores, new Location(latitude, longitude));

    }

    @Test
    void testClosestStoreFunctionOnlyLocationTypesExist() {
        //Given
        double latitude = 50.5;
        double longitude = 5.5;
        Set<Integer> locationTypes = Set.of(LocationType.PUP.value);

        //Call method
        var response = storeController.getClosestStores(latitude, longitude, Optional.empty(), Optional.of(locationTypes));
        var stores = response.getBody();

        //Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(stores);
        assertEquals(Constants.DEFAULT_NUMBER_OF_STORES, stores.size());
        TestUtils.assertListIsCorrectOrder(stores, new Location(latitude, longitude));
        for (StoreDTO store : stores) {
            assertEquals(LocationType.PUP, store.getLocationType());
        }
    }

    public String createURL(String uri) {
        return "http://localhost:" + port + uri;
    }

}