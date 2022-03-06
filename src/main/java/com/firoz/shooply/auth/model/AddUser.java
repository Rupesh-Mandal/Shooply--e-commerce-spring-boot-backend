package com.firoz.shooply.auth.model;

import com.firoz.shooply.admin.model.StoreCategory;
import lombok.Data;

@Data
public class AddUser {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String storeName;
    private UserType userType;

    private Plan plan;
    private StoreCategory storeCategory;
}
