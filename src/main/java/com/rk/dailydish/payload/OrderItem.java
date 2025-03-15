package com.rk.dailydish.payload;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Embeddable
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private int productId; // Store only product ID
    private String name;
    private String description;
    private String category;
    private String image;
    private double price;
    private String unit;
    private int quantity;
}
