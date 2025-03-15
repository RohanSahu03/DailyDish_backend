package com.rk.dailydish.controller;


import com.rk.dailydish.entity.ApartmentOrders;
import com.rk.dailydish.payload.OrderRequest;
import com.rk.dailydish.payload.OrderResponse;
import com.rk.dailydish.services.ApartmentOrderService;
import com.rk.dailydish.services.CorporateOrderService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporate-orders")
@AllArgsConstructor
public class CorporateOrderController {

    @Autowired
    private CorporateOrderService corporateOrderService;

    @PostMapping("/")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(corporateOrderService.createOrder(orderRequest));
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByUser(@PathVariable int userId) {
        return ResponseEntity.ok(corporateOrderService.getOrdersByUser(userId));
    }
    
    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(corporateOrderService.getAllOrders());
    }
    
//    @DeleteMapping("/cancel/{orderId}")
//    public ResponseEntity<String> cancelOrder(@PathVariable int orderId) {
//        apartmentOrderService.cancelOrder(orderId);
//        return ResponseEntity.ok("Order cancelled successfully.");
//    }

}
