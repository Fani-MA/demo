package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.entity.Product;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;


    @GetMapping
   public List<Product> allProduct(){
       var resp = new Resp();
        return productRepo.findAll();
   }

    @GetMapping("{id}")
    public Resp product(@PathVariable("id") Long id){
        var resp = new Resp();
        var products = productRepo.getById(id);
        resp.setStr(products.toString());
        return resp;
    }

}
