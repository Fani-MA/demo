package me.fani.michael.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "USER")
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME")
    @NotEmpty(message = "Name should be not empty")
    @Size(min = 5, max = 50, message = "Name should be between 5 and 50 characters ")
    private String username;

    @Column(name = "PASSWORD")
    @NotEmpty(message = "Password should be not empty")
    @Size(min = 8, max = 100, message = "Name should be between 8 and 100 characters ")
    private String password;

    @Column(name = "EMAIL")
    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Email is not valid")
    private String email;

    @Column(name = "CREATE_TIME")
    private Timestamp createTime;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserInfo info;

    @OneToMany(mappedBy = "userId",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cart> cartListUser;


    @OneToMany(mappedBy = "userCheckout",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Checkout> checkouts;

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }

    public long getId() {
        return id;
    }

    public List<Cart> getCartListUser() {
        return cartListUser;
    }

    public void setCartListUser(List<Cart> cartListUser) {
        this.cartListUser = cartListUser;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Optional<UserInfo> getInfo() {
        return Optional.ofNullable(info);
    }

    public void setInfo(UserInfo info) {
        this.info = info;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(createTime, user.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, createTime);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", timestamp=" + createTime +
                '}';
    }
}
