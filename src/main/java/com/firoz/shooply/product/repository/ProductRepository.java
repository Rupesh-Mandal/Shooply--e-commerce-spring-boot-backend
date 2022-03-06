package com.firoz.shooply.product.repository;


import com.firoz.shooply.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<List<Product>> findByStoreId(String storeId);
    Optional<List<Product>> findByStorecategory(String storecategory);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %?1% OR p.productDescription LIKE %?1% OR p.storecategory LIKE %?1% OR p.sub_category LIKE %?1% OR p.storeName LIKE %?1%")
    Optional<List<Product>> findAll(String key);
}
