package com.rk.dailydish.payload;

import java.util.List;

import com.rk.dailydish.dto.OrderItemRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {

    private int userId;
    private String slotTime;
    private String apartmentName;
    private String location;
    private String deliveryType;
    private String paymentMethod;
    private String paymentStatus;
    private long phone;
    private double deliveryCharge;
    private double tax;
    private double totalAmount;
    private String orderStatus;

//    private List<OrderItemRequest> orderedItems; // Changed to DTO instead of embedded entity
    private List<OrderItem> orderedItems;
}
