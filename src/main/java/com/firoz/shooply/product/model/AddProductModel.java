package com.firoz.shooply.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.JSONArray;

import javax.persistence.Column;


@Getter
@Setter
@NoArgsConstructor
public class AddProductModel {

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

}
