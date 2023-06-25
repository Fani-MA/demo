package me.fani.michael.persistence.dao;

import me.fani.michael.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long > {


    @Override
    List<Category> findAll();

    List<Category> findAllByParentId(Long parentId);


    Category getById(Long id);
}
