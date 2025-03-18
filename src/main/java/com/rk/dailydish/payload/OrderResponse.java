package com.rk.dailydish.payload;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private int id;
 	
 	private int userId;
    
    private LocalDateTime date;
    

    private String slotTime;

    private String apartmentName;
    
    private String location;
    
    private String deliveryType;
    
    
    private String paymentStatus;
    
    private String phone;

    private double deliveryCharge;
    
    private double tax;
    
    private double totalAmount;
    
    private String orderStatus;

    private List<OrderItem> orderedItems;
}
