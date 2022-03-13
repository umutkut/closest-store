package com.jumbo.closeststore.infrastructure;

import com.jumbo.closeststore.infrastructure.model.StoreModel;

import java.util.List;

public interface IStoreLoader {
    void cache(List<StoreModel> storeModels);
}
