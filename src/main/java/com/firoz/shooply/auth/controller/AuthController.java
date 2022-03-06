package com.firoz.shooply.auth.controller;

import com.firoz.shooply.auth.model.AddUser;
import com.firoz.shooply.auth.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(path = "sign_up")
    public Object signUp(@RequestBody AddUser addUser){
        return userService.signUp(addUser);
    }


    @PostMapping(path = "sign_in")
    public Object signin(@RequestParam("phoneNumber") String phoneNumber,@RequestParam("password") String password){
        return userService.signin(phoneNumber,password);

    }


}
