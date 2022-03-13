package com.jumbo.closeststore.controller;

import com.jumbo.closeststore.application.ClosestStoreService;
import com.jumbo.closeststore.dto.ClosestStoreRequest;
import com.jumbo.closeststore.dto.StoreDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = {"/api/v1/stores"})
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Stores Api documentation")
public class StoreController {

    private final ClosestStoreService closestStoreService;

    @GetMapping("/closest")
    @Operation(description = "Get closest stores.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request."),
            @ApiResponse(responseCode = "406", description = "Invalid latitude, longitude or location type.")
    })
    @Parameters({
            @Parameter(name = "latitude", required = true, description = "Current latitude value.", example = "55.5"),
            @Parameter(name = "longitude", required = true, description = "Current longitude value.", example = "55.5"),
            @Parameter(name = "numberOfStores", description = "Parameter to limit the number of stores to be listed.", example = "8"),
            @Parameter(name = "locationTypes", description = "Parameter to filter the types of stores to be listed. Requires set of numbers.", example = "0,1,2"),
    })
    public ResponseEntity<List<StoreDTO>> getClosestStores(@RequestParam
                                                                   Double latitude,
                                                           @RequestParam
                                                                   Double longitude,
                                                           @RequestParam
                                                                   Optional<Integer> numberOfStores,
                                                           @RequestParam
                                                                   Optional<Set<Integer>> locationTypes) {

        var closestStoreRequest = new ClosestStoreRequest(numberOfStores.orElse(null),
                latitude,
                longitude,
                locationTypes.orElse(null));

        log.debug("/api/v1/stores/closest requested with {}", closestStoreRequest);
        return ResponseEntity.ok(closestStoreService.findClosestStores(closestStoreRequest));

    }
}
