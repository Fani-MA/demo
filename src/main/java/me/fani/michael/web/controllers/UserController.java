package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.Role;
import me.fani.michael.persistence.entity.User;
import me.fani.michael.util.PasswordUtil;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Controller
@RequestMapping("user")

public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;


    //TODO переделать
    @GetMapping
    @PreAuthorize("hasAuthority('user:write')")
    public String allUsers(Model model) {
        model.addAttribute("users",userRepo.findAll());
        return "users/users.html";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        if(SecurityContextHolder.getContext().getAuthentication().getName()==null) return "redirect:/user";

        model.addAttribute("user", new User());
        return "/users/new.html";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        if (!PasswordUtil.isGoodPassword(user.getPassword())) {
            throw new IllegalArgumentException("Password is too short");
        }

        user.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepo.save(user);
        return "redirect:/category";

    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public String user(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user",userRepo.getById(id));
        return "users/user.html";
    }


    //TODO дописать методы и доступ @PreAuthorize
    @GetMapping("{id}/address")
    @PreAuthorize("hasAuthority('user:write')")
    public String allUserAddress(@PathVariable("id") Long id) {
        //userRepo.getById(id).getInfo().get();
        throw new NotYetImplementedException("allUserAddress");
        // return null;
    }
}
