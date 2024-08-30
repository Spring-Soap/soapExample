package com.discovering.soap_producing_example.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;
}