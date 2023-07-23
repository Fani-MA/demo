package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CartRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Cart;
import me.fani.michael.persistence.entity.User;
import me.fani.michael.web.dto.CreateCartRequest;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;


    //TODO добавить нормальное представление
    @GetMapping
    public String allCart(Model model){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if(user != null){
            List<Cart> cartList = user.getCartListUser();
            model.addAttribute("cartList", cartList);
        }
        return "cart/cart.html";
    }


    //TODO изменить метод: возвращает представление, используем Model
    @PostMapping()
    public Resp createCart(@RequestBody CreateCartRequest req){
        var resp = new Resp();
        Cart newCart = new Cart();
        if(userRepo.existsById(req.getUserId()) && productRepo.existsById(req.getProductId())){
            newCart.setUserId(userRepo.getById(req.getUserId()));
            newCart.setProductId(productRepo.getById(req.getProductId()));
            cartRepo.save(newCart);
            resp.setStr("Success");
        }else{
            resp.setStr("Check userId and productId");
        }
        return resp;
    }

    //TODO добавить контроллер на удаление из корзины

}
