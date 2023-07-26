package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product,Long> {

    List<Product> findAll();

    Product getById(Long id);




}
