package com.jumbo.closeststore.infrastructure.jsonreader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.closeststore.infrastructure.IStoreLoader;
import com.jumbo.closeststore.infrastructure.model.StoreModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class JsonReader {

    private final IStoreLoader storeLoader;
    @Value("classpath:data/stores.json")
    Resource storesResourceFile;

    @PostConstruct
    public void loadStoresFromJson() throws IOException {
        var storesFile = storesResourceFile.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        var storeModels = mapper.readValue(storesFile, new TypeReference<List<StoreModel>>() {
        });

        storeLoader.cache(storeModels);
    }


}
