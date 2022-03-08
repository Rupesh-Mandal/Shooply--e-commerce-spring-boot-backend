package com.firoz.shooply.product.controller;

import com.firoz.shooply.product.model.AddProductModel;
import com.firoz.shooply.product.model.Product;
import com.firoz.shooply.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(path = "addProduct")
    public Object addProduct(@RequestBody AddProductModel addProductModel){
        return productService.addProduct(addProductModel);
    }

    @PostMapping(path = "updateProduct")
    public Object updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @PostMapping(path = "getAllProductSeller")
    public Object getAllProoductSeller(@RequestParam("storeId") String storeId){
        return productService.getAllProductSeller(storeId);
    }

    @PostMapping(path = "findByStorecategory")
    public Object findByStorecategory(@RequestParam("storecategory") String storecategory){
        return productService.findByStorecategory(storecategory);
    }

    @PostMapping(path = "loadSearch")
    public Object loadSearch(@RequestParam("key") String key){
        return productService.loadSearch(key);
    }

    @PostMapping(path = "deletProduct")
    public Object deletProoduct(@RequestBody Product product){
        return productService.deletProduct(product);
    }


    @PostMapping(path = "getAllProductUser")
    public Object getAllProoductUser(){
        return productService.getAllProductUser();
    }

    @PostMapping(path = "findByProductId")
    public Object findByProductId(@RequestParam String productId){
        return productService.findByProductId(productId);
    }

}
