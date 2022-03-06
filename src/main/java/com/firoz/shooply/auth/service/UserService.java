package com.firoz.shooply.auth.service;

import com.firoz.shooply.admin.model.StoreCategory;
import com.firoz.shooply.auth.model.AddUser;
import com.firoz.shooply.auth.model.AuthUser;
import com.firoz.shooply.auth.model.UserType;
import com.firoz.shooply.auth.repository.UserRepository;
import com.firoz.shooply.store.model.StoreModel;
import com.firoz.shooply.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    private final String storeDefultDescription="Let?s pick a product, any product.";

    private final String storeDefultSlogan="Let's Shop with Shooply";

    public Object signUp(AddUser addUser) {
        JSONObject jsonObject=new JSONObject();
        boolean userExists = userRepository.findByPhoneNumber(addUser.getPhoneNumber()).isPresent();

        if (userExists){
            jsonObject.put("status",false);
            jsonObject.put("message","Phone Number already taken");
            return jsonObject;
        }

        String userId= UUID.randomUUID().toString();
        AuthUser authUser=new AuthUser(userId, addUser.getName(), addUser.getPhoneNumber(),
                addUser.getEmail(), addUser.getPassword(), addUser.getUserType(),addUser.getPlan(), LocalDateTime.now());

        Long id= userRepository.save(authUser).getId();

        if (id==null){

            jsonObject.put("status",false);
            jsonObject.put("message","Something went worng");
            return jsonObject;
        }else {
            jsonObject.put("status",true);
            jsonObject.put("message","Successfully SignUp");
            jsonObject.put("authUser",authUser);
            if (authUser.getUserType()== UserType.Seller){
                creatStore(authUser,addUser.getStoreName(),addUser.getStoreCategory());
            }
            return jsonObject;
        }
    }


    public Object signin(String phoneNumber, String password) {
        JSONObject jsonObject=new JSONObject();

        Optional<AuthUser> authUserOptional=userRepository.findByPhoneNumber(phoneNumber);
        if (authUserOptional.isPresent()){
            AuthUser authUser=authUserOptional.get();

            if (authUser.getPassword().trim().equals(password.trim())){
                jsonObject.put("status",true);
                jsonObject.put("message","Successfully SignUp");
                jsonObject.put("authUser",authUser);
                return jsonObject;
            }else {
                jsonObject.put("status",false);
                jsonObject.put("message","Please check password");
                return jsonObject;
            }
        }else {
            jsonObject.put("status",false);
            jsonObject.put("message","Please check you phone number");
            return jsonObject;
        }
    }

    @Async
    void creatStore(AuthUser authUser, String storeName, StoreCategory storeCategory) {
        StoreModel storeModel=new StoreModel(authUser.getUserId(),storeName,
                storeDefultDescription,storeDefultSlogan,storeCategory.getStoreCategoryId(),
                storeCategory.getCategory(),storeCategory.getBanner(),storeCategory.getLogo(),authUser.getEmail());
        storeRepository.save(storeModel);
    }

}
