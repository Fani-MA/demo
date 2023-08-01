package me.fani.michael.service;

import me.fani.michael.persistence.dao.CartRepo;
import me.fani.michael.persistence.dao.CheckoutRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Cart;
import me.fani.michael.persistence.entity.Checkout;
import me.fani.michael.persistence.entity.Product;
import me.fani.michael.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private AuthService authService;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CheckoutRepo checkoutRepo;


    public List<Cart> allCart(){
        User user =  userRepo.findByUsername(authService.getAuthenticatedUserName())
                .orElse(null);
        if(user==null) return null;
        return user.getCartListUser();
    }

    @Transactional
    public void buyProduct(Cart cart) throws RuntimeException{
        Product buyProduct = cart.getProductId();
        if(buyProduct.getQuantity()<cart.getAmount() || buyProduct.getQuantity()==0) {
            throw new RuntimeException("Could not commit JDBC transaction");
        }
        buyProduct.setQuantity(buyProduct.getQuantity()-cart.getAmount());
        productRepo.save(buyProduct);
        cartRepo.delete(cart);
        Checkout addCheckout = new Checkout();
        addCheckout.setAmount(cart.getAmount());
        addCheckout.setProductCheckout(cart.getProductId());
        addCheckout.setUserCheckout(cart.getUserId());
        addCheckout.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        checkoutRepo.save(addCheckout);
    }

    @Transactional
    public void buyAll() throws RuntimeException{
        var cartList = allCart();
        for(Cart cart : cartList){
            buyProduct(cart);
        }
    }

    public boolean productInCart(Product product){
        List<Cart> cartList = allCart();
        Cart res = cartList.stream().filter(s -> s.getProductId().getId()==product.getId()).findAny().orElse(null);
        return res==null? false : true;
    }

}
