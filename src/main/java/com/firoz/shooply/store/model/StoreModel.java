package com.firoz.shooply.store.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class StoreModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String storeId;
    private String storeName;
    private String storeDescription;
    private String storeSlogan;

    private String storeCategoryId;
    private String category;
    private String banner;
    private String logo;

    private String storeEmail;

    public StoreModel(String storeId, String storeName, String storeDescription, String storeSlogan, String storeCategoryId, String category, String banner, String logo, String storeEmail) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeDescription = storeDescription;
        this.storeSlogan = storeSlogan;
        this.storeCategoryId = storeCategoryId;
        this.category = category;
        this.banner = banner;
        this.logo = logo;
        this.storeEmail = storeEmail;
    }
}
