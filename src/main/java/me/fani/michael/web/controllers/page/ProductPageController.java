package me.fani.michael.web.controllers.page;

import me.fani.michael.persistence.dao.ProductRepo;
import me.fani.michael.persistence.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductPageController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/product-page/{id}")
    public String greeting(@PathVariable(name="id") long id, Model model) {
        Product product = productRepo.getById(id);
        model.addAttribute("name", product.getName());
        model.addAttribute("price", product.getPrice());
        return "product";
    }
}
