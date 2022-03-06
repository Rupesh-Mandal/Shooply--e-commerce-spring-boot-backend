package com.firoz.shooply.store.controller;

import com.firoz.shooply.auth.model.AddUser;
import com.firoz.shooply.store.model.StoreModel;
import com.firoz.shooply.store.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/store")
@AllArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping(path = "getStoreByStoreId")
    public Object getStoreByStoreId(@RequestParam("storeId") String storeId){
        return storeService.getStoreByStoreId(storeId);
    }

    @PostMapping(path = "updateStore")
    public Object getStoreByStoreId(@RequestBody StoreModel storeModel){
        System.out.println(storeModel);
        return storeService.updateStore(storeModel);
    }

    @GetMapping(path = "getAllCategory")
    public Object getAllCategory(){
        return storeService.getAllCategory();
    }



}
