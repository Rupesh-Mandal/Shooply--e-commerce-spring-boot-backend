package com.firoz.shooply.addressbook.service;

import com.firoz.shooply.addressbook.model.AddressBookModel;
import com.firoz.shooply.addressbook.repository.AddressBookRepository;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;

    public Object addAddress(String productDeliverAddress, String userId, String userPhoneNumber) {
        JSONObject jsonObject=new JSONObject();
        addressBookRepository.save(new AddressBookModel(productDeliverAddress,userId,userPhoneNumber));
        jsonObject.put("status",true);
        jsonObject.put("message","Successfull");
        return jsonObject;
    }

    public Object getAddressByUserId(String userId) {
        return addressBookRepository.findByUserId(userId).get();

    }

    public Object deleltAddress(Long id) {
        JSONObject jsonObject=new JSONObject();
        addressBookRepository.existsById(id);
        jsonObject.put("status",true);
        jsonObject.put("message","Successfull");
        return jsonObject;
    }
}
