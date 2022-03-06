package com.firoz.shooply.admin.controller;


import com.firoz.shooply.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping(path = "getAllCategory")
    public Object getAllCategory(){
        return adminService.getAllCategory();
    }

    @PostMapping(path = "getSubCategory")
    public Object getSubCategory(@RequestParam String storecategory){
        return adminService.getSubCategory(storecategory);
    }

    @PostMapping(path = "addCategory")
    public Object addCategory(@RequestBody String jsonObject){
        return adminService.addCategory(jsonObject);
    }

}
