package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CartRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Cart;
import me.fani.michael.persistence.entity.Product;
import me.fani.michael.persistence.entity.User;
import me.fani.michael.web.dto.CreateCartRequest;
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


    @GetMapping
    public String allCart(Model model){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if(user != null){
            List<Cart> cartList = user.getCartListUser();
            model.addAttribute("cartList", cartList);
        }
        return "cart/cart.html";
    }

    @GetMapping("/new")
    public void addCart(Model model){
        model.addAttribute("cart", new Cart());
    }


    //TODO изменить метод: возвращает представление, используем Model
    @PostMapping("/{id}")
    public String addCart(@PathVariable("id") Long id){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if(user != null){
            Cart addCart = new Cart();
            addCart.setProductId(productRepo.getById(id));
            addCart.setUserId(user);
            cartRepo.save(addCart);
        }
        return "redirect:/cart";
    }

    //TODO добавить контроллер на удаление из корзины

}
