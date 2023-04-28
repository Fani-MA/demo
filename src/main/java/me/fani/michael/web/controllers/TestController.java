package me.fani.michael.web.controllers;


import me.fani.michael.web.dto.Resp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public Resp index() {
        var resp = new Resp();
        resp.setStr("Greetings from Spring Boot!");
        return resp;
    }

}
