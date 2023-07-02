package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CategoryRepository;
import me.fani.michael.persistence.entity.Category;
import me.fani.michael.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryReposirory;

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("allCategory", categoryReposirory.findAll());
        return "category/categoryes.html";
    }

    @GetMapping("/new")
    public String createCategory(Model model){
        model.addAttribute("category", new Category());
        return "category/newCategory.html";
    }

    @GetMapping("/{id}")
    public String subCategory(@PathVariable("id") Long id, Model model){
        List<Product> productList = new  ArrayList<>();

        //создаем массив для отображения вложенности классов(хлебные крошки) и передаем в модель
        // n - ограничения вложенности
        List<Category> list = new ArrayList<>();
        int n=0;
        var category = categoryReposirory.getById(id);
        while (category.getParentId()!=0 && n<8){
            n++;
            list.add(category);
            category=categoryReposirory.getById(category.getParentId());
        }
        list.add(category);
        int size = list.size();
        var reverce = new Category[size];
        for (int i=0; i<size; i++ ){
            reverce[i] = list.get(size-1-i);
        }
        model.addAttribute("list", reverce);

        //создаем список подкатегорий для текущей категории(для навигации) и добавляем в модель если он существует
        List<Category> subCategory = categoryReposirory.findAllByParentId(id);
        boolean view= !subCategory.isEmpty();
        model.addAttribute("view", view);
        if(view) {
            model.addAttribute("subCategory", subCategory);
        }


        //создаю массив для поиска всех подклассов текущего класса для получения всех продуктов данной категории
        //после чего получаем массив продуктов(для текущей категории) для последующего отображения на странице
        //todo: доделать представление(привести в нормальный вид)=> ХК сверху, сбоку подкатегории, остальная часть вывод продуктов для данной категории
        if(!categoryReposirory.getById(id).getProducts().isEmpty()) productList.addAll(categoryReposirory.getById(id).getProducts());
        if(view) {
            for (Category sub : subCategory) {
                if (categoryReposirory.findAllByParentId(sub.getId()).isEmpty()) {
                    productList.addAll(sub.getProducts());
                } else {
                    list.addAll(categoryReposirory.findAllByParentId(sub.getId()));
                }
            }
            model.addAttribute("productList", productList);
        }else {
            model.addAttribute("productList", categoryReposirory.getById(id).getProducts());
        }
        boolean viewProduct = !productList.isEmpty();
        model.addAttribute("viewProduct",viewProduct);
        return "category/subCategory.html";
    }


    @PostMapping()
    public String createCategory(@ModelAttribute("category") Category category, Model model){
        categoryReposirory.save(category);
        return "redirect:category";
    }


}
