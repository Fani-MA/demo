package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CheckoutRepo;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Checkout;
import me.fani.michael.web.dto.CreateCheckoutRequest;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("checkout")
public class CheckoutController {

    @Autowired
    private CheckoutRepo checkoutRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public List<Checkout>  allCheckouts(){
        return checkoutRepo.findAll();
    }

    @GetMapping("{id}")
    public Resp checkout(@PathVariable("id") Long id){
        var response = new Resp();
        var checkout = checkoutRepo.getById(id);
        response.setStr(checkout.toString());
        return response;
    }

    @PostMapping()
    public Resp createCheckout(@RequestBody CreateCheckoutRequest req){
        var resp = new Resp();
        Checkout newCheckout = new Checkout();
        newCheckout.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        newCheckout.setAmount(req.getAmount());
        if (productRepo.existsById(req.getProductId()) && userRepo.existsById(req.getUserId())) {
            newCheckout.setProductCheckout(productRepo.getById(req.getProductId()));
            newCheckout.setUserCheckout(userRepo.getById(req.getUserId()));
            Checkout saveCheckout = checkoutRepo.save(newCheckout);
            resp.setStr("Success create new sale id=" + saveCheckout.getId());
        }else {
            resp.setStr("Check userId and productId");
        }
        return resp;
    }



}
