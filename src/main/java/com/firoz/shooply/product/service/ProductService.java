package com.firoz.shooply.product.service;

import com.firoz.shooply.product.model.AddProductModel;
import com.firoz.shooply.product.model.Product;
import com.firoz.shooply.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public Object addProduct(AddProductModel addProductModel) {
        JSONObject jsonObject = new JSONObject();

        String productId = UUID.randomUUID().toString();
        Product product = new Product(productId, addProductModel.getStoreId(), addProductModel.getStoreName(),
                addProductModel.getStoreEmail(), addProductModel.getProductName(),
                addProductModel.getProductDescription(), addProductModel.getProductRate(),
                addProductModel.getProductImageLink(), addProductModel.getMrp(), addProductModel.getDiscount(), addProductModel.getStoreCategoryId(),
                addProductModel.getStorecategory(),addProductModel.getSub_category(), LocalDateTime.now());

        productRepository.save(product);

        jsonObject.put("status", true);
        jsonObject.put("messag", "Successfully Uploaded");
        return jsonObject;

    }


    public Object getAllProductSeller(String storeId) {
        List<Product> productList = productRepository.findByStoreId(storeId).get();
        return productList;
    }

    public Object deletProduct(Product product) {
        JSONObject jsonObject = new JSONObject();
        productRepository.delete(product);

        jsonObject.put("status", true);
        jsonObject.put("messag", "Successfully Deleted");
        return jsonObject;
    }

    public Object updateProduct(Product product) {
        JSONObject jsonObject = new JSONObject();
        productRepository.save(product);

        jsonObject.put("status", true);
        jsonObject.put("messag", "Successfully Deleted");
        return jsonObject;
    }

    public Object getAllProductUser(Pageable pageable) {
        List<Product> productList = productRepository.findAll(pageable).toList();
        return productList;
    }

    public Object loadSearch(String key,Pageable pageable) {
        return productRepository.findAll(key,pageable);
    }

    public Object findByStorecategory(String productCategory) {
        return productRepository.findByStorecategory(productCategory).get();
    }

    public Object findByProductId(String productId) {
        return productRepository.findByProductId(productId);
    }
}
