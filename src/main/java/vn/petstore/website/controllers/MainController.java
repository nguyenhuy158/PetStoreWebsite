package vn.petstore.website.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String index() {
        return "login";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
