package vn.petstore.website.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.petstore.website.model.Product;
import vn.petstore.website.model.User;
import vn.petstore.website.repository.UserRepository;
import vn.petstore.website.services.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static vn.petstore.website.constances.Const.PRODUCT_LIMIT;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    ProductService productService;

    @GetMapping(value = { "/", "/home" })
    public String index(Model model) {
        List<Product> allProducts = productService.getAllProducts(PRODUCT_LIMIT);
        model.addAttribute("products", allProducts);
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
