package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.entity.Product;
import me.fani.michael.web.dto.CreateProductRequest;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;


    @GetMapping
    public List<Product> allProduct(){
        return productRepo.findAll();
    }

    @GetMapping("{id}")
    public Resp product(@PathVariable("id") Long id){
        var resp = new Resp();
        var products = productRepo.getById(id);
        resp.setStr(products.toString());
        return resp;
    }

    @PostMapping()
    public Resp createProduct(@RequestBody CreateProductRequest request){
        var resp = new Resp();
        var newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setPrice(request.getPrice());
        Product saveProduct = productRepo.save(newProduct);
        resp.setStr(saveProduct.toString());

        return resp;
    }

}
