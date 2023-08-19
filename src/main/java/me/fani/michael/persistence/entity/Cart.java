package me.fani.michael.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "PRODUCT_ID")
    public long productId;

    @Column(name = "USER_ID")
    private long userId;

//    @ManyToOne
//    @JoinColumn(name = "PRODUCT_ID")
//    private Product product;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    //    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

//    public User getUserId() {
//        return userId;
//    }
//
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
