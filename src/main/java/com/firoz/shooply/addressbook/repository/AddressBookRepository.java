package com.firoz.shooply.addressbook.repository;

import com.firoz.shooply.addressbook.model.AddressBookModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepository extends JpaRepository<AddressBookModel,Long> {
    Optional<List<AddressBookModel>> findByUserId(String userId);
    AddressBookModel findByAddressId(String addressId);

    void deleteByAddressId(String addressId);

}
