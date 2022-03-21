package com.firoz.shooply.order.repository;

import com.firoz.shooply.order.model.CartModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartModel,Long> {
    List<CartModel> findByUserId(String userId);
}
