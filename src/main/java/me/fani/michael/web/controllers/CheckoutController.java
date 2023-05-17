package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CheckoutRepo;
import me.fani.michael.persistence.entity.Checkout;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("checkout")
public class CheckoutController {

    @Autowired
    private CheckoutRepo checkoutRepo;

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


}
