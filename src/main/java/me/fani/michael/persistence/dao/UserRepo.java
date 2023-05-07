package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepo extends Repository<User, Long> {

    List<User> findAll(); // SELECT * FROM user

    User getById(Long id); // SELECT * FROM user WHERE id = ?

    User getByIdAndUsername(Long id, String username); // SELECT * FROM user WHERE id = ?
}
