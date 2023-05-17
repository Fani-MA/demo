package me.fani.michael.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;
}
