package me.fani.michael.persistence.entity;

import jakarta.persistence.*;

import java.util.Collection;

public class Category {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Collection<Product> products;




}
