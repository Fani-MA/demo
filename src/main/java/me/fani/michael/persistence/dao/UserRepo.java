package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAll(); // SELECT * FROM user

    Optional<User> findByUsername(String username); //SELECT * FROM WHERE username = ?
    User getById(Long id); // SELECT * FROM user WHERE id = ?

    User getByIdAndUsername(Long id, String username); // SELECT * FROM user WHERE id = ?
}
