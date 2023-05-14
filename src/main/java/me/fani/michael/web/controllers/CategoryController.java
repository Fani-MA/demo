package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CategoryReposirory;
import me.fani.michael.persistence.entity.Category;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryReposirory categoryReposirory;

    @GetMapping
    List<Category> findAll(){
        return categoryReposirory.findAll();
    }

    @GetMapping("{id}")
    public Resp category(@PathVariable("id") Long id){
        var resp = new Resp();
        var categoryes = categoryReposirory.getById(id);
        resp.setStr(categoryes.toString());
        return resp;
    }

}
