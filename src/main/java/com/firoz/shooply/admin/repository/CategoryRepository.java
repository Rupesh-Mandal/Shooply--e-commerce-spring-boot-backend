package com.firoz.shooply.admin.repository;

import com.firoz.shooply.admin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
    Optional<List<Category>> findByStorecategory(String storecategory);
}
