package com.firoz.shooply.store.service;

import com.firoz.shooply.store.model.StoreModel;
import com.firoz.shooply.admin.repository.CategoryRepository;
import com.firoz.shooply.store.repository.StoreRepository;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    public Object getStoreByStoreId(String storeId) {
        JSONObject jsonObject=new JSONObject();

        Optional<StoreModel> optionalStoreModel=storeRepository.findByStoreId(storeId);
        if (optionalStoreModel.isPresent()){
            jsonObject.put("status",true);
            jsonObject.put("message","Successfull");
            jsonObject.put("StoreModel",optionalStoreModel.get());
            return jsonObject;
        }else {
            jsonObject.put("status",false);
            jsonObject.put("message","Something went wrong");
            return jsonObject;
        }

    }

    public Object getAllCategory() {
        return categoryRepository.findAll();
    }

    public Object updateStore(StoreModel storeModel) {
        JSONObject jsonObject=new JSONObject();

        storeRepository.save(storeModel);
        jsonObject.put("status",true);
        jsonObject.put("message","Successfull Udated");
        return jsonObject;
    }
}
