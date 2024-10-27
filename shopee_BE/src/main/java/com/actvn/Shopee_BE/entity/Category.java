package com.actvn.Shopee_BE.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    @Size(min = 5, message = "Name must be least 5 char")
    @Column(unique = true)
    private String name;
    @Column(name = "Create_at")
    private final LocalDateTime createAt = LocalDateTime.now();

//    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "category_id")
//    private List<Product> products;
}
