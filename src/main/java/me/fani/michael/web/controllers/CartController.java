package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CartRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Cart;
import me.fani.michael.persistence.entity.User;
import me.fani.michael.service.CartService;
import me.fani.michael.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    CartService cartService;



    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public String allCart(Model model){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if(user != null){
            List<Cart> cartList = user.getCartListUser();
            model.addAttribute("cartList", cartList);
        }
        return "cart/cart.html";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('user:write')")
    public void addCart(Model model){
        model.addAttribute("cart", new Cart());
    }


    @PostMapping("/new/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String addCart(@PathVariable("id") long id,@ModelAttribute("amount") int amount , Model model){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if(user != null){
            if(cartService.productInCart(productRepo.getById(id))){
                return "redirect:/cart";
            }
            Cart addCart = new Cart();
            addCart.setProductId(productRepo.getById(id));
            addCart.setUserId(user);
            addCart.setAmount(amount);

            cartRepo.save(addCart);
        }
        return "redirect:/cart";
    }

    @GetMapping("/buy")
    public String buy(Model model){
        try {
            cartService.buyAll();
        } catch (RuntimeException e){
            model.addAttribute("ex",e.toString());
            return "redirect:/cart";
        }


        return "redirect:/";
    }
}
