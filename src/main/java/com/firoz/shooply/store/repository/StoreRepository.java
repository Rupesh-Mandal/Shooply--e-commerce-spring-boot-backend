package com.firoz.shooply.store.repository;

import com.firoz.shooply.store.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreModel,Long> {
    Optional<StoreModel> findByStoreId(String storeId);
}
