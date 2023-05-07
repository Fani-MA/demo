package me.fani.michael.web.controllers;


import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public Resp index() {
        var resp = new Resp();
        resp.setStr("Greetings from Spring Boot!");
        return resp;
    }

    @GetMapping("/users")
    public Resp allUsers() {
        var resp = new Resp();
        var users = userRepo.findAll().stream()
            .map(u -> u.getId() + ":" + u.getUsername())
            .collect(Collectors.joining(", "));
        resp.setStr(users);
        return resp;
    }

    @GetMapping("/users/{id}")
    public Resp allUsers(@PathVariable("id") Long id) {
        var resp = new Resp();
        var users = userRepo.getById(id);
        resp.setStr(users.toString());
        return resp;
    }
}
