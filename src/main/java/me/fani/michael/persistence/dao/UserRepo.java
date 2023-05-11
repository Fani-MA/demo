package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAll(); // SELECT * FROM user

    User getById(Long id); // SELECT * FROM user WHERE id = ?

    User getByIdAndUsername(Long id, String username); // SELECT * FROM user WHERE id = ?
}
