package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryReposirory extends CrudRepository<Category, Long > {


    @Override
    List<Category> findAll();


    Category getById(Long id);
}
