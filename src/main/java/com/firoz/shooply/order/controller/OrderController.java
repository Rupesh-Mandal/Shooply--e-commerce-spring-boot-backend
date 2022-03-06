package com.firoz.shooply.order.controller;


import com.firoz.shooply.order.model.AddOrderModel;
import com.firoz.shooply.order.model.CartModel;
import com.firoz.shooply.order.model.OrderModel;
import com.firoz.shooply.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(path = "oderProduct")
    public Object oderProduct(@RequestBody List<AddOrderModel> addOrderModelList){
        return orderService.oderProduct(addOrderModelList);
    }

    @PostMapping(path = "addToCart")
    public Object addToCart(@RequestBody String jsonArray){
        return orderService.addToCart(jsonArray);
    }

    @PostMapping(path = "updateCart")
    public Object updateCart(@RequestBody CartModel cartModel){
        return orderService.updateCart(cartModel);
    }

    @PostMapping(path = "deleteCart")
    public Object deleteCart(@RequestBody CartModel cartModel){
        return orderService.deleteCart(cartModel);
    }

    @PostMapping(path = "getAllCartList")
    public Object getAllCartList(@RequestParam String userId){
        return orderService.getAllCartList(userId);
    }

    @PostMapping(path = "getOrderAsUser")
    public Object getProductUser(@RequestParam("userId") String userId){
        return orderService.getOrderAsUser(userId);
    }

    @PostMapping(path = "getOrderAsSeller")
    public Object getProductSeller(@RequestParam("storeId") String storeId){
        return orderService.getOrderAsSeller(storeId);
    }

    @PostMapping(path = "acceptOrder")
    public Object acceptOrder(@RequestBody OrderModel orderModel){
        return orderService.acceptOrder(orderModel);
    }

    @PostMapping(path = "cancelOrderBySeller")
    public Object cancelOrderBySeller(@RequestBody OrderModel orderModel){
        return orderService.cancelOrderBySeller(orderModel);
    }


    @PostMapping(path = "cancelOrderByUser")
    public Object cancelOrderByUser(@RequestBody OrderModel orderModel){
        return orderService.cancelOrderByUser(orderModel);
    }


    @PostMapping(path = "deliverdFaildOrder")
    public Object deliverdFaildOrder(@RequestBody OrderModel orderModel){
        return orderService.deliverdFaildOrder(orderModel);
    }

    @PostMapping(path = "deliveryStarted")
    public Object deliveryStarted(@RequestBody OrderModel orderModel){
        return orderService.deliveryStarted(orderModel);
    }


}
