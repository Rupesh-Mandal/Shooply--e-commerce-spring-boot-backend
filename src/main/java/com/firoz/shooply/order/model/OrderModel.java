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

    private String storeId;
    private String storeName;
    private String storeEmail;

    private String productName;
    private String productDescription;
    private String productQuantity;
    private String productRate;
    private String productTotalRate;
    private String productDeliverAddress;


    @Column(columnDefinition="TEXT")
    private String productImageLink;
    private String productCategory;

    private String userPhoneNumber;
    private String userName;
    private String userId;


    private String status;
    private String statusMessage;

    private LocalDateTime createdTime;

    public OrderModel(@NonNull String orderId, String productId, String storeId, String storeName, String storeEmail, String productName, String productDescription, String productQuantity, String productRate, String productTotalRate, String productDeliverAddress, String productImageLink, String productCategory, String userPhoneNumber, String userName, String userId, String status, String statusMessage, LocalDateTime createdTime) {
        this.orderId = orderId;
        this.productId = productId;
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeEmail = storeEmail;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productQuantity = productQuantity;
        this.productRate = productRate;
        this.productTotalRate = productTotalRate;
        this.productDeliverAddress = productDeliverAddress;
        this.productImageLink = productImageLink;
        this.productCategory = productCategory;
        this.userPhoneNumber = userPhoneNumber;
        this.userName = userName;
        this.userId = userId;
        this.status = status;
        this.statusMessage = statusMessage;
        this.createdTime = createdTime;
    }
}
