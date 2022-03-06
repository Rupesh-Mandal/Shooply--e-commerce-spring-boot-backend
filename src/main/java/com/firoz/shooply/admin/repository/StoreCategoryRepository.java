package com.firoz.shooply.admin.repository;

import com.firoz.shooply.admin.model.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory,Long> {

    Optional<StoreCategory> findByCategory(String category);
}
