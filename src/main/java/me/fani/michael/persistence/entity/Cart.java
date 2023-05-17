package me.fani.michael.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    //@Column(name = "PRODUCT_ID")
    @ManyToOne
    private Product productId;

    //@Column(name = "USER_ID")
    @ManyToOne
    private User userId;
}
