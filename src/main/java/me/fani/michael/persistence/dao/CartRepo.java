package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.Cart;
import me.fani.michael.persistence.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepo extends CrudRepository<Cart, Long> {

    @Override
    List<Cart> findAll();


}
