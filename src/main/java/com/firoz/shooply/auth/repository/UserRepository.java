package com.firoz.shooply.auth.repository;

import com.firoz.shooply.auth.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AuthUser,Long> {
    Optional<AuthUser> findByPhoneNumber(String phoneNumber);
}
