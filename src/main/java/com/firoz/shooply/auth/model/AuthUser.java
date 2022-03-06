package com.firoz.shooply.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private UserType userType;
    private Plan plan;

    private LocalDateTime createdTime;


    public AuthUser(String userId, String name, String phoneNumber, String email, String password, UserType userType, Plan plan, LocalDateTime createdTime) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.plan = plan;
        this.createdTime = createdTime;
    }
}
