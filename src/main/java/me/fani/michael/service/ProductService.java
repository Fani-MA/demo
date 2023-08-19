package me.fani.michael.service;

import jakarta.transaction.Transactional;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Transactional
    public Product getById(long id) {
        var product = productRepo.getById(id);
        product.getName();
        return product;
    }

    @Transactional
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Transactional
    public void setQuantity(long productId, int newQuantity) {
        var product = productRepo.getById(productId);
        product.setQuantity(newQuantity);
        productRepo.save(product);

    }

}
