package com.firoz.shooply.order.service;

import com.firoz.shooply.order.model.CartModel;
import com.firoz.shooply.order.model.OrderModel;
import com.firoz.shooply.order.repository.CartRepository;
import com.firoz.shooply.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final String serverKey = "AAAAShY29Gc:APA91bH0jagENjcKbsYnxLrXOqyFaJF2NlfHlAFfFtnBqC48-tFWGNOCu9fqg_vflJXUn3BVJlr0hkn3Noamg10Dxkm3t_pMRrLm2yzXk1vZExz0zxSW1VBE5LmiNWSkkHBgCqa6BLaM";

//    private final String serverKey = "AAAAIjDnh98:APA91bHs6ZqDHYHnrtFg4b_IwbUWlgfFC4KwJEb6CZxPlLhHPSff4cXiz6x46eAcXDRkmpFaTolc7TSHDp5zmfgYn8aYyE4ZYaZCA68UqHB6rZA-dq2IE1rjiaUqoeUBmDrAD4HOLQGc";

    public Object oderProduct(String array) {
        JSONObject jsonObject = new JSONObject();
        JSONArray addOrderModeArray=new JSONArray(array);
        String status = "1";
        String statusMassege = "Pending to Accept";

        for (int i = 0; i < addOrderModeArray.length(); i++) {
            org.json.JSONObject orderObject=addOrderModeArray.getJSONObject(i);

            String orderId = UUID.randomUUID().toString();
            OrderModel orderModel=new OrderModel(
                    orderId,
                    orderObject.getString("productId"),
                    orderObject.getString("userId"),
                    orderObject.getString("storeId"),
                    orderObject.getString("storeName"),
                    orderObject.getString("storeEmail"),
                    orderObject.getString("productName"),
                    orderObject.getString("productDescription"),
                    orderObject.getString("productRate"),
                    orderObject.getString("productImageLink"),
                    orderObject.getString("mrp"),
                    orderObject.getString("discount"),
                    orderObject.getString("storeCategoryId"),
                    orderObject.getString("storecategory"),
                    orderObject.getString("sub_category"),
                    orderObject.getString("quantity"),
                    orderObject.getString("productDeliverAddress"),
                    orderObject.getString("userPhoneNumber"),
                    orderObject.getString("userName"),
                    orderObject.getString("productTotalRate"),
                    status,
                    statusMassege,
                    orderObject.getString("deliveryType"),
                    orderObject.getString("productDeliverInstruction"),
                    LocalDateTime.now());

            orderRepository.save(orderModel);
            cartRepository.deleteById(orderObject.getLong("id"));
            sendNotification(orderModel.getProductName(),
                    orderModel.getProductDescription(),
                    "/topics/" + orderModel.getStoreId());
        }

        jsonObject.put("status", true);
        jsonObject.put("massage", "Successfully Ordered");
        return jsonObject;

    }


    @Async
    void sendNotification(String title, String body, String to) {
        JSONObject jsonObject = new JSONObject();
        JSONObject dataObject = new JSONObject();
        dataObject.put("title", title);
        dataObject.put("body", body);
        jsonObject.put("to", to);

        jsonObject.put("data", dataObject);

        String url = "https://fcm.googleapis.com/fcm/send";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "key="+serverKey);

// build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(jsonObject, headers);

// send POST request
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        System.out.println(response.toString());
    }

    public Object getOrderAsUser(String userId) {
        List<OrderModel> orderModelList = orderRepository.findByUserId(userId).get();
        Collections.sort(orderModelList, new Comparator<OrderModel>() {
            @Override
            public int compare(OrderModel u1, OrderModel u2) {
                return u2.getId().compareTo(u1.getId());
            }
        });

        return orderModelList;
    }

    public Object getOrderAsSeller(String storeId) {
        List<OrderModel> orderModelList = orderRepository.findByStoreId(storeId).get();
        Collections.sort(orderModelList, new Comparator<OrderModel>() {
            @Override
            public int compare(OrderModel u1, OrderModel u2) {
                return u2.getId().compareTo(u1.getId());
            }
        });

        return orderModelList;
    }

    public Object acceptOrder(OrderModel orderModel) {
        String status = "2";
        String statusMassege = "Accepted";

        JSONObject jsonObject = new JSONObject();

        orderModel.setStatus(status);
        orderModel.setStatusMessage(statusMassege);
        orderRepository.save(orderModel);
        if (orderRepository.save(orderModel).getStatus().equals("2")) {
            jsonObject.put("status", true);
            jsonObject.put("messag", "Order Accepted by "+orderModel.getStoreName());
            sendNotification("Order Started", orderModel.getProductName(), "/topics/" + orderModel.getUserId());
            return jsonObject;
        }

        jsonObject.put("status", false);
        jsonObject.put("messag", "something went wrong");
        return jsonObject;
    }

    public Object cancelOrderBySeller(OrderModel orderModel) {
        String status = "0";
        String statusMassege = "Cancel by Seller";

        JSONObject jsonObject = new JSONObject();

        orderModel.setStatus(status);
        orderModel.setStatusMessage(statusMassege);
        orderRepository.save(orderModel);
        if (orderRepository.save(orderModel).getStatus().equals("0")) {
            jsonObject.put("status", true);
            jsonObject.put("messag", "Cancel Successfully");
            sendNotification("Order Cancel " + orderModel.getProductName(), orderModel.getStatusMessage(), "/topics/" + orderModel.getUserId());

            return jsonObject;
        }

        jsonObject.put("status", false);
        jsonObject.put("messag", "something went wrong");
        return jsonObject;
    }


    public Object cancelOrderByUser(OrderModel orderModel) {
        String status = "0";
        String statusMassege = "Cancel by User";

        JSONObject jsonObject = new JSONObject();

        orderModel.setStatus(status);
        orderModel.setStatusMessage(statusMassege);
        orderRepository.save(orderModel);
        if (orderRepository.save(orderModel).getStatus().equals("0")) {
            jsonObject.put("status", true);
            jsonObject.put("messag", "Cancel Successfully");
            sendNotification("Order Cancel " + orderModel.getProductName(), orderModel.getStatusMessage(), "/topics/" + orderModel.getUserId());
            sendNotification("Cancel by User",
                    orderModel.getProductName(),
                    "/topics/" + orderModel.getStoreId());
            return jsonObject;
        }

        jsonObject.put("status", false);
        jsonObject.put("messag", "something went wrong");
        return jsonObject;
    }


    public Object deliverdFaildOrder(OrderModel orderModel) {
        String status = "0";
        String statusMassege = "Delivery Faild";

        JSONObject jsonObject = new JSONObject();

        orderModel.setStatus(status);
        orderModel.setStatusMessage(statusMassege);
        orderRepository.save(orderModel);
        if (orderRepository.save(orderModel).getStatus().equals("0")) {
            jsonObject.put("status", true);
            jsonObject.put("messag", "Cancel Successfully");
            sendNotification("Order Cancel " + orderModel.getProductName(), orderModel.getStatusMessage(), "/topics/" + orderModel.getUserId());
            sendNotification("Cancel by User",
                    orderModel.getProductName(),
                    "/topics/" + orderModel.getStoreId());
            return jsonObject;
        }

        jsonObject.put("status", false);
        jsonObject.put("messag", "something went wrong");
        return jsonObject;
    }

    public Object deliveryStarted(OrderModel orderModel) {
        String status = "2";
        String statusMassege = "Delivery Started";

        JSONObject jsonObject = new JSONObject();

        orderModel.setStatus(status);
        orderModel.setStatusMessage(statusMassege);
        orderRepository.save(orderModel);
        if (orderRepository.save(orderModel).getStatus().equals("0")) {
            jsonObject.put("status", true);
            jsonObject.put("messag", "Cancel Successfully");
            sendNotification("Order Cancel " + orderModel.getProductName(), orderModel.getStatusMessage(), "/topics/" + orderModel.getUserId());
            sendNotification("Cancel by User",
                    orderModel.getProductName(),
                    "/topics/" + orderModel.getStoreId());
            return jsonObject;
        }

        jsonObject.put("status", false);
        jsonObject.put("messag", "something went wrong");
        return jsonObject;
    }

    public Object addToCart(String jsonArray) {
        List<CartModel> cartModelList=new ArrayList<>();
        JSONArray jsonArray1=new JSONArray(jsonArray);
        JSONObject jsonObject = new JSONObject();

        for (int i=0;i< jsonArray1.length();i++){
            org.json.JSONObject cartObject=jsonArray1.getJSONObject(i);
            CartModel cartModel=new CartModel(
                    cartObject.getString("productId"),
                    cartObject.getString("userId"),
                    cartObject.getString("storeId"),
                    cartObject.getString("storeName"),
                    cartObject.getString("storeEmail"),
                    cartObject.getString("productName"),
                    cartObject.getString("productDescription"),
                    cartObject.getString("productRate"),
                    cartObject.getString("productImageLink"),
                    cartObject.getString("mrp"),
                    cartObject.getString("discount"),
                    cartObject.getString("storeCategoryId"),
                    cartObject.getString("storecategory"),
                    cartObject.getString("sub_category"),
                    cartObject.getString("quantity"),
                    LocalDateTime.now());
            cartModelList.add(cartModel);
        }
        cartRepository.saveAll(cartModelList);
        jsonObject.put("status", true);
        jsonObject.put("massage", "Successfully Added to Cart");
        return jsonObject;
    }

    public Object getAllCartList(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public Object deleteCart(CartModel cartModel) {
        cartRepository.delete(cartModel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("massage", "Successfully Added to Cart");
        return jsonObject;
    }

    public Object updateCart(CartModel cartModel) {
        cartRepository.save(cartModel);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", true);
        jsonObject.put("massage", "Successfully Added to Cart");
        return jsonObject;
    }

    public Object getOrderHistory(String userId,Pageable pageable) {
        return orderRepository.findAllByUserIdAndStatus(userId,"0",pageable);
    }

    public Object getStartedOrder(String userId,Pageable pageable) {
        return orderRepository.findAllByUserIdAndStatus(userId,"2",pageable);
    }

    public Object getPendingOrder(String userId,Pageable pageable) {
        return orderRepository.findAllByUserIdAndStatus(userId,"1",pageable);
    }
}
