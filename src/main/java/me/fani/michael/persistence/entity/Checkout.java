package me.fani.michael.persistence.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "SALE")
public class Checkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Column(name = "AMOUNT")
    private int amount;

//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User userCheckout;

//    @ManyToOne
    @Column(name = "USER_ID")
    private long userId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
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

//    public User getUserCheckout() {
//        return userCheckout;
//    }
//
//    public void setUserCheckout(User userCheckout) {
//        this.userCheckout = userCheckout;
//    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Product getProductCheckout() {
        return productCheckout;
    }

    public void setProductCheckout(Product productCheckout) {
        this.productCheckout = productCheckout;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", amount=" + amount +
                ", userId=" + userId +
                ", productCheckout=" + productCheckout +
                '}';
    }
}
