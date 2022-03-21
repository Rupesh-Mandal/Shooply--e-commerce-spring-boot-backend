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

    private String userId;
    private String addressId;
    private String productDeliverAddress;
    private String productDeliverInstruction;
    private String userPhoneNumber;

    public AddressBookModel(String userId, String addressId, String productDeliverAddress, String productDeliverInstruction, String userPhoneNumber) {
        this.userId = userId;
        this.addressId = addressId;
        this.productDeliverAddress = productDeliverAddress;
        this.productDeliverInstruction = productDeliverInstruction;
        this.userPhoneNumber = userPhoneNumber;
    }
}
