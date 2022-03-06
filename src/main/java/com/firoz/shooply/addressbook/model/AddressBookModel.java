package com.firoz.shooply.addressbook.model;

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
public class AddressBookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productDeliverAddress;
    private String userId;
    private String userPhoneNumber;


    public AddressBookModel(String productDeliverAddress, String userId, String userPhoneNumber) {
        this.productDeliverAddress = productDeliverAddress;
        this.userId = userId;
        this.userPhoneNumber = userPhoneNumber;
    }
}
