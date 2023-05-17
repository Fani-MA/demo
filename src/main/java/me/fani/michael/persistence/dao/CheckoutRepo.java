package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.Checkout;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CheckoutRepo extends CrudRepository<Checkout, Long> {

    List<Checkout> findAll();

    Checkout getById(Long id);
}
