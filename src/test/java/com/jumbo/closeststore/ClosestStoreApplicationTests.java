package com.jumbo.closeststore;

import com.jumbo.closeststore.infrastructure.IStoreReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ClosestStoreApplicationTests {
    @Autowired
    IStoreReader storesCache;

    @Test
    void contextLoads() {
        assertFalse(storesCache.getAll().isEmpty());
    }

}
