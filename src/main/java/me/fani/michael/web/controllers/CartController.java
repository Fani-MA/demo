package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CartRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Cart;
import me.fani.michael.web.dto.CreateCartRequest;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<Cart> allCart(){
        return cartRepo.findAll();
    }

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

}
