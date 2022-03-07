package com.firoz.shooply.order.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String orderId;
    private String productId;
    private String userId;

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
    private String quantity;

    private String productDeliverAddress;
    private String userPhoneNumber;
    private String userName;
    private String productTotalRate;

    private String status;
    private String statusMessage;
    private LocalDateTime createdTime;

    public OrderModel(@NonNull String orderId, String productId, String userId, String storeId, String storeName, String storeEmail, String productName, String productDescription, String productRate, String productImageLink, String mrp, String discount, String storeCategoryId, String storecategory, String sub_category, String quantity, String productDeliverAddress, String userPhoneNumber, String userName, String productTotalRate, String status, String statusMessage, LocalDateTime createdTime) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
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
        this.quantity = quantity;
        this.productDeliverAddress = productDeliverAddress;
        this.userPhoneNumber = userPhoneNumber;
        this.userName = userName;
        this.productTotalRate = productTotalRate;
        this.status = status;
        this.statusMessage = statusMessage;
        this.createdTime = createdTime;
    }
}
