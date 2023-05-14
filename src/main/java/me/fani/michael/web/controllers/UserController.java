package me.fani.michael.web.controllers;

import me.fani.michael.persistence.dao.UserRepo;
import me.fani.michael.persistence.entity.User;
import me.fani.michael.web.dto.CreateUserRequest;
import me.fani.michael.web.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepo userRepo;



    @GetMapping
    public List<User> allUsers() {
        return userRepo.findAll();
    }

    @PostMapping()
    public Resp createUser(@RequestBody CreateUserRequest req) {
        var resp = new Resp();
        User newUser = new User();
        newUser.setUsername(req.getName());
        newUser.setEmail(req.getEmail());
        newUser.setPassword("1111");
        newUser.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        User savedUser = userRepo.save(newUser);
        resp.setStr("Successfully save with ID=" + savedUser.getId());
        return resp;
    }

    @GetMapping("{id}")
    public Resp user(@PathVariable("id") Long id) {
        var resp = new Resp();
        var users = userRepo.getById(id);
        resp.setStr(users.toString());
        return resp;
    }

    @GetMapping("{id}/address")
    public Resp allUserAddress(@PathVariable("id") Long id) {
        var resp = new Resp();
        var users = userRepo.getById(id);
        resp.setStr(users.getInfo()
                .map(i -> i.getAddress())
                .orElse("No address specified")
        );
        return resp;
    }
}
