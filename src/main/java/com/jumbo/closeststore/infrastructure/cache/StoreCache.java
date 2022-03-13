package com.jumbo.closeststore.infrastructure.cache;

import com.jumbo.closeststore.entity.Store;
import com.jumbo.closeststore.infrastructure.IStoreLoader;
import com.jumbo.closeststore.infrastructure.IStoreReader;
import com.jumbo.closeststore.infrastructure.model.StoreModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class StoreCache implements IStoreReader, IStoreLoader {
    private final Map<Long, Store> stores = new ConcurrentHashMap<>();

    @Override
    public void cache(List<StoreModel> storeModels) {
        for (StoreModel storeModel : storeModels) {
            stores.put(storeModel.getSapStoreID(), Store.fromStoreModel(storeModel));
        }
    }

    @Override
    public List<Store> getAll() {
        return new ArrayList<>(stores.values());
    }
}
