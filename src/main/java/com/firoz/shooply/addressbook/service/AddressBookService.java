package com.firoz.shooply.addressbook.service;

import com.firoz.shooply.addressbook.model.AddressBookModel;
import com.firoz.shooply.addressbook.model.DefaultAddressBook;
import com.firoz.shooply.addressbook.repository.AddressBookRepository;
import com.firoz.shooply.addressbook.repository.DefaultAddressBookRepository;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AddressBookService {
    private final AddressBookRepository addressBookRepository;
    private final DefaultAddressBookRepository defaultAddressBookRepository;

    public Object addAddress(String productDeliverAddress,String productDeliverInstruction, String userId, String userPhoneNumber) {
        JSONObject jsonObject=new JSONObject();
        String AddressId= UUID.randomUUID().toString();

        AddressBookModel addressBookModel=addressBookRepository.save(new AddressBookModel(userId,AddressId,productDeliverAddress,productDeliverInstruction,userPhoneNumber));
        jsonObject.put("status",true);
        jsonObject.put("message","Successfull");
        jsonObject.put("AddressBookModel",addressBookModel);
        return jsonObject;
    }

    public Object getAddressByUserId(String userId) {
        return addressBookRepository.findByUserId(userId).get();

    }

    public Object getDefaultAddressByUserId(String userId) {
        JSONObject jsonObject=new JSONObject();
        Optional<DefaultAddressBook> defaultAddressBook=defaultAddressBookRepository.findByUserId(userId);
        if (defaultAddressBook.isPresent()==false){
            jsonObject.put("status",false);
            jsonObject.put("message","No DefaultAddress Found");
            return jsonObject;
        }
        AddressBookModel addressBookModel=addressBookRepository.findByAddressId(defaultAddressBook.get().getAddressId());

        jsonObject.put("status",true);
        jsonObject.put("message","Successfull");
        jsonObject.put("AddressBookModel",addressBookModel);
        return jsonObject;
    }

    @Transactional
    public Object deleteByAddressId(String addressId) {
        System.out.println(addressId);
        addressBookRepository.deleteByAddressId(addressId);
        Optional<DefaultAddressBook> defaultAddressBook=defaultAddressBookRepository.findByUserId(addressId);
        if (defaultAddressBook.isPresent()){
            defaultAddressBookRepository.deleteByAddressId(addressId);
        }
        return true;
    }

    public Object setDefaultAddress(String addressId, String userId) {
        Optional<DefaultAddressBook> defaultAddressBookOptional=defaultAddressBookRepository.findByUserId(userId);
        if (defaultAddressBookOptional.isPresent()==true){
            DefaultAddressBook defaultAddressBook=defaultAddressBookOptional.get();
            defaultAddressBook.setAddressId(addressId);
            defaultAddressBookRepository.save(defaultAddressBook);
            return true;
        }
        DefaultAddressBook defaultAddressBook=new DefaultAddressBook();
        defaultAddressBook.setUserId(userId);
        defaultAddressBook.setAddressId(addressId);
        defaultAddressBookRepository.save(defaultAddressBook);
        return true;
    }

    public Object updateAddress(AddressBookModel addressBookModel) {
        addressBookRepository.save(addressBookModel);
        return true;
    }
}
