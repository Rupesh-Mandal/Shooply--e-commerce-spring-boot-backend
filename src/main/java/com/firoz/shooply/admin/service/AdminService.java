package com.firoz.shooply.admin.service;

import com.firoz.shooply.admin.model.Category;
import com.firoz.shooply.admin.model.StoreCategory;
import com.firoz.shooply.admin.repository.CategoryRepository;
import com.firoz.shooply.admin.repository.StoreCategoryRepository;
import lombok.AllArgsConstructor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AdminService {
    private final CategoryRepository categoryRepository;
    private final StoreCategoryRepository storeCategoryRepository;


    public Object getAllCategory() {
        return storeCategoryRepository.findAll();
    }

    public Object addCategory(String obj) {
        net.minidev.json.JSONObject jsonObject = new net.minidev.json.JSONObject();

        JSONObject categoryObject=new JSONObject(obj);

        System.out.println(categoryObject.toString());

        String categoryName=categoryObject.getString("category");
        if (storeCategoryRepository.findByCategory(categoryName).isPresent()){
            jsonObject.put("status", false);
            jsonObject.put("messag", "Category Already Present");
            return jsonObject;
        }
        String storeCategoryId = UUID.randomUUID().toString();
        StoreCategory storeCategory=new StoreCategory();
        storeCategory.setStoreCategoryId(storeCategoryId);
        storeCategory.setCategory(categoryName);
        storeCategory.setBanner(categoryObject.getString("banner"));
        storeCategory.setLogo(categoryObject.getString("logo"));

        storeCategoryRepository.save(storeCategory);

        JSONArray jsonArray=categoryObject.getJSONArray("sub_category");

        List<Category> categoryList=new ArrayList<>();
        for (int i=0;i<jsonArray.length();i++){
            JSONObject subCategoryObject=jsonArray.getJSONObject(i);

            Category category=new Category();
            category.setStoreCategoryId(storeCategoryId);
            category.setStorecategory(categoryName);
            category.setName(subCategoryObject.getString("name"));
            categoryList.add(category);
        }

        categoryRepository.saveAll(categoryList);

        jsonObject.put("status", true);
        jsonObject.put("messag", "Successfully Uploaded");
        return jsonObject;
    }

    public Object getSubCategory(String storecategory) {
        return categoryRepository.findByStorecategory(storecategory).get();
    }
}
