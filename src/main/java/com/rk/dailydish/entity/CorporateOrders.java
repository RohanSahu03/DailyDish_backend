package com.rk.dailydish.entity;

import java.util.List;

import com.rk.dailydish.payload.OrderItem;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "corporate_orders")
@Data
@Setter
public class CorporateOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_items_corporate", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems;

    @Column(nullable = false)
    private String apartmentName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String deliveryType;

    @Column(nullable = false)
    private String paymentMethods;

    @Column(nullable = false)
    private double deliveryCharge; // Fixed type (was String before)

    @Column(nullable = false)
    private double tax;

    @Column(nullable = false)
    private double totalAmount;
    
    @Column(nullable = false)
    private String slotTime;
    
    @Column(nullable = false)
    private String phone;
}
