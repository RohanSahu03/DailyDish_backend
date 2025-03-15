package com.rk.dailydish.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int remainingStock;

    @Column(nullable = false)
    private boolean status;
}
