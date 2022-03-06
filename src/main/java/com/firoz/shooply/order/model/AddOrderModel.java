package com.firoz.shooply.order.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddOrderModel {
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
    private String productImageLink;
    private String productCategory;
    private String userPhoneNumber;
    private String userName;
    private String userId;

}
