package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.CategoryRepository;
import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepository categoryRepository;

    //TODO: переделать методы с использованием модели и выводом странички на отображение
    //TODO: добавить методы для изменения и удаления продукта


    @GetMapping
    @PreAuthorize("hasAuthority('user:read')")
    public String allProduct(Model model){
        //получаем из БД все товары для отображения на странице
        model.addAttribute("productList", productRepo.findAll());
        return "product/allProduct.html";
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public String product(@PathVariable("id") Long id, Model model){
        //получаем нужный продукт по id, добавляем в представление для отображения
        model.addAttribute("product", productRepo.getById(id));
        return "product/product.html";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('user:write')")
    public String addProduct(Model model){
        //создаем объект класса продукт для заполнения в форме на странице(new.html)
        model.addAttribute("product",new Product());
        return "product/new.html";
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('user:write')")
    public String  createProduct(@ModelAttribute("product") Product product){
        //получаем продукт из формы и сохраняем его в БД, перенаправляем на страницу товара(продуктов)
        productRepo.save(product);
        return "redirect:product";
    }

    //страница редактирования продукта
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model){
        model.addAttribute("product", productRepo.getById(id));
        return "product/edit.html";
    }

    //todo: настроить конфигурационный файл для обработки скрытых полей(_method)
    //todo: пример(урок№23):конфигурационный класс - расширяет AbstractAnnotationConfigDispatcherServletInitializer
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public String update(@ModelAttribute("product") Product product, @PathVariable("id") long id){
        Product updateProduct = productRepo.getById(id);
        updateProduct.setName(product.getName());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setCategory(product.getCategory());
        productRepo.save(updateProduct);
        return "redirect:/product";
    }

}
