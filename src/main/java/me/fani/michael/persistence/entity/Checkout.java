package me.fani.michael.persistence.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Timestamp createTime;

    private int amount;
    @ManyToOne
    private User userCheckout;

    @ManyToOne
    private Product productCheckout;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUserCheckout() {
        return userCheckout;
    }

    public void setUserCheckout(User userCheckout) {
        this.userCheckout = userCheckout;
    }

    public Product getProductCheckout() {
        return productCheckout;
    }

    public void setProductCheckout(Product productCheckout) {
        this.productCheckout = productCheckout;
    }



}
