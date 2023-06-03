package me.fani.michael.web.controllers.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentsController {

    @GetMapping("/header")
    public String getHome() {
        return "header.html";
    }

}
