package com.firoz.shooply.product.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import net.minidev.json.JSONArray;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String productId;

    private String storeId;
    private String storeName;
    private String storeEmail;

    private String productName;
    private String productDescription;
    private String productRate;


    @Column(columnDefinition="TEXT")
    private String productImageLink;

    private String mrp;
    private String discount;
    private String storeCategoryId;
    private String storecategory;
    private String sub_category;

    private LocalDateTime createdTime;


    public Product(@NonNull String productId, String storeId, String storeName, String storeEmail, String productName, String productDescription, String productRate, String productImageLink, String mrp, String discount, String storeCategoryId, String storecategory, String sub_category, LocalDateTime createdTime) {
        this.productId = productId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeEmail = storeEmail;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productRate = productRate;
        this.productImageLink = productImageLink;
        this.mrp = mrp;
        this.discount = discount;
        this.storeCategoryId = storeCategoryId;
        this.storecategory = storecategory;
        this.sub_category = sub_category;
        this.createdTime = createdTime;
    }
}
