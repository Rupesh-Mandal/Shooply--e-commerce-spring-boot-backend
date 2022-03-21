package com.firoz.shooply.product.controller;

import com.firoz.shooply.product.model.AddProductModel;
import com.firoz.shooply.product.model.Product;
import com.firoz.shooply.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public Object getAllProoductSeller(@RequestParam("storeId") String storeId,
                                       @RequestParam Optional<Integer> page,
                                       @RequestParam Optional<Integer> pageSize,
                                       @RequestParam Optional<Sort.Direction> sort,
                                       @RequestParam Optional<String> sortBy){


        return productService.getAllProductSeller(storeId,PageRequest.of( page.orElse(0),pageSize.orElse(10),sort.orElse(Sort.Direction.ASC), sortBy.orElse("createdTime")) );
    }

    @PostMapping(path = "findByStorecategory")
    public Object findByStorecategory(@RequestParam String storecategory){
        return productService.findByStorecategory(storecategory);
    }

    @PostMapping(path = "loadSearch")
    public Object loadSearch(@RequestParam("key") String key,
                             @RequestParam Optional<Integer> page,
                             @RequestParam Optional<Integer> pageSize,
                             @RequestParam Optional<Sort.Direction> sort,
                             @RequestParam Optional<String> sortBy){

        return productService.loadSearch(key,PageRequest.of( page.orElse(0),pageSize.orElse(10),sort.orElse(Sort.Direction.ASC), sortBy.orElse("productName")) );
    }

    @PostMapping(path = "deletProduct")
    public Object deletProoduct(@RequestBody Product product){
        return productService.deletProduct(product);
    }


    @PostMapping(path = "getAllProductUser")
    public Object getAllProoductUser( @RequestParam Optional<Integer> page,
                                      @RequestParam Optional<Integer> pageSize,
                                      @RequestParam Optional<Sort.Direction> sort,
                                      @RequestParam Optional<String> sortBy){

        return productService.getAllProductUser(PageRequest.of( page.orElse(0),pageSize.orElse(10),sort.orElse(Sort.Direction.ASC), sortBy.orElse("createdTime")) );
    }

    @PostMapping(path = "findByProductId")
    public Object findByProductId(@RequestParam String productId){
        return productService.findByProductId(productId);
    }

}
