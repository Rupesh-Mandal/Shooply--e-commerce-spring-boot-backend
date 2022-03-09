package com.firoz.shooply.addressbook.repository;

import com.firoz.shooply.addressbook.model.DefaultAddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DefaultAddressBookRepository extends JpaRepository<DefaultAddressBook,Long> {
    Optional<DefaultAddressBook> findByUserId(String userId);
    Optional<DefaultAddressBook> findByAddressId(String addressId);

    void deleteByAddressId(String addressId);
}
