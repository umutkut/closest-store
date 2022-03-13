package com.jumbo.closeststore.infrastructure;

import com.jumbo.closeststore.entity.Store;

import java.util.List;

public interface IStoreReader {
    List<Store> getAll();
}
