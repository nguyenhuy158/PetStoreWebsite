package vn.petstore.website.controllers;

import static vn.petstore.website.constances.Const.PRODUCT_LIMIT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import vn.petstore.website.model.Product;
import vn.petstore.website.services.ProductService;
import vn.petstore.website.services.UserService;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping(value = { "/", "/home" })
    public String index(Model model) {
        List<Product> allProducts = productService.getAllProducts(PRODUCT_LIMIT);
        model.addAttribute("products", allProducts);
        model.addAttribute("isLogout", userService.getCurrentUser());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "userInfo";
    }
}
