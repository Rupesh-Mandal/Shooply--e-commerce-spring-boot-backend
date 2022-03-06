package com.firoz.shooply.order.repository;

import com.firoz.shooply.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    Optional<List<OrderModel>> findByUserId(String userId);
    Optional<List<OrderModel>> findByStoreId(String storeId);

}
