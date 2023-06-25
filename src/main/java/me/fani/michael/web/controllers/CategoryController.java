package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CategoryRepository;
import me.fani.michael.persistence.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryReposirory;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("categoryes", categoryReposirory.findAll());
        return "category/categoryes.html";
    }

    @GetMapping("/new")
    public String createCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/newCategory.html";
    }

    @GetMapping("/parent/{parentId}")
    public String subCategory(Long parentId, Model model){
        model.addAttribute("subCategory",categoryReposirory.findAllByParentId(parentId));

        return null;
    }


    @PostMapping()
    public String createCategory(@ModelAttribute("category") Category category, Model model){
        categoryReposirory.save(category);
        //TODO create html
        return "redirect:category";
    }


}
