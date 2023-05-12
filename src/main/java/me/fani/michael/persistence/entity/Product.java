package me.fani.michael.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CATEGORY")
    @MapsId
    @JoinColumn(name ="CATEGORY_ID")
    @JsonIgnore
    private Category category;
}
