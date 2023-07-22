package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.User;
import me.fani.michael.web.dto.CreateUserRequest;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepo userRepo;



    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users",userRepo.findAll());
        return "users/users.html";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "/users/new.html";
    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user,Model model) {
        user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        userRepo.save(user);
        return "redirect:users";
    }


    @GetMapping("/{id}")
    public String user(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",userRepo.getById(id));
        return "users/user.html";
    }


    //TODO дописать метод
    @GetMapping("{id}/address")
    public String allUserAddress(@PathVariable("id") Long id) {
        userRepo.getById(id).getInfo().get();
        return null;
    }
}
