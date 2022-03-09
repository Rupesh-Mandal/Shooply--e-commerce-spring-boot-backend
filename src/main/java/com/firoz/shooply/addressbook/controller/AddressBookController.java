package com.firoz.shooply.addressbook.controller;

import com.firoz.shooply.addressbook.model.AddressBookModel;
import com.firoz.shooply.addressbook.service.AddressBookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/addressBook")
@AllArgsConstructor
public class AddressBookController {
    private final AddressBookService addressBookService;

    @PostMapping(path = "addAddress")
    public Object addAddress(@RequestParam String productDeliverAddress, @RequestParam String userId, @RequestParam String userPhoneNumber) {
        return addressBookService.addAddress(productDeliverAddress, userId, userPhoneNumber);
    }

    @PostMapping(path = "deleteByAddressId")
    public Object deleteByAddressId(@RequestParam String addressId) {
        return addressBookService.deleteByAddressId(addressId);
    }

    @PostMapping(path = "setDefaultAddress")
    public Object deleteByAddressId(@RequestParam String addressId,String userId) {
        return addressBookService.setDefaultAddress(addressId,userId);
    }

    @PostMapping(path = "getAddressByUserId")
    public Object getAddressByUserId(@RequestParam String userId) {
        return addressBookService.getAddressByUserId(userId);
    }

 @PostMapping(path = "getDefaultAddressByUserId")
    public Object getDefaultAddressByUserId(@RequestParam String userId) {
        return addressBookService.getDefaultAddressByUserId(userId);
    }


 @PostMapping(path = "updateAddress")
    public Object UpdateAddress(@RequestBody AddressBookModel addressBookModel) {
        return addressBookService.updateAddress(addressBookModel);
    }


}
