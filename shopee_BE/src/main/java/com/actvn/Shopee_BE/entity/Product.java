package com.actvn.Shopee_BE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name ="product_name")
    private String productName;
    private String description;
    private double discount;
    private double price;
    private String image;
    private int quantity;
    @Column(name = "special_price")
    private double specialPrice;
    @Column(name = "Create_at")
    private final LocalDateTime createAt = LocalDateTime.now();
    @Column(name = "update_at")
    private LocalDateTime updateAt = LocalDateTime.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
