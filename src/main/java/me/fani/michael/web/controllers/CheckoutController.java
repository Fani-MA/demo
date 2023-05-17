package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CheckoutRepo;
import me.fani.michael.persistence.entity.Checkout;
import me.fani.michael.web.dto.Resp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "checkout")
public class CheckoutController {

    private CheckoutRepo checkoutRepo;

    @GetMapping
    public List<Checkout> findAll(){
        return checkoutRepo.findAll();
    }

    @GetMapping("{id}")
    public Resp checkout(@PathVariable("id") Long id){
        var resp = new Resp();
        var checkout = checkoutRepo.getById(id);
        resp.setStr(checkout.toString());
        return resp;
    }


}
