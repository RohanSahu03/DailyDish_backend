package com.rk.dailydish.payload;

import java.util.List;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {

    private int userId;
    
    @NotEmpty
    private String slotTime;
    
    @NotEmpty
    private String apartmentName;
    
    @NotEmpty
    private String location;
    
    @NotEmpty
    private String deliveryType;
    
    @NotEmpty
    private String paymentMethod;
    
    @NotEmpty
    private String paymentStatus;
    @NotEmpty
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone number must start with 6, 7, 8, or 9 and be exactly 10 digits long.")
    private String phone;
    
    @NotNull
    private double deliveryCharge;
    
    @NotNull
    private double tax;
    private double totalAmount;
    private String orderStatus;

//    private List<OrderItemRequest> orderedItems; // Changed to DTO instead of embedded entity
    private List<OrderItem> orderedItems;
}
