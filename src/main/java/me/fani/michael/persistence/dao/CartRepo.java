package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepo extends CrudRepository<Cart, Long> {

    @Override
    List<Cart> findAll();

    //List<Cart> findByUser(Long id);
}
