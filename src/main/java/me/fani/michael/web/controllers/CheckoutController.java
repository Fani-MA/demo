package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CheckoutRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Checkout;
import me.fani.michael.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("checkout")
public class CheckoutController {

    @Autowired
    private CheckoutRepo checkoutRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;



    @GetMapping
    public String allCheckouts( Model model){
        User user = userRepo.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
        if(user!=null) {
            List<Checkout> checkoutList = user.getCheckouts();
            model.addAttribute("checkoutList", checkoutList);
        }
        return "checkout/checkout.html";
    }

    @GetMapping("{id}")
    public String checkout(@PathVariable("id") Long id, Model model){
        model.addAttribute("checkoutUser", checkoutRepo.getById(id));
        return "checkout/checkout.html";
    }

    @PostMapping()
    public String createCheckout(@ModelAttribute("checkout") Checkout checkout, Model model){
        checkoutRepo.save(checkout);
        return "redirect:/checkout";
    }



}
